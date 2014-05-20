package com.sharecare.sample.model.article.activation;

import com.sharecare.sample.model.article.Article;
import com.sharecare.sample.model.article.ArticleAssembler;
import com.sharecare.sample.model.article.ArticleRepository;
import info.magnolia.context.Context;
import info.magnolia.module.activation.commands.BaseActivationCommand;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Component;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import java.sql.Timestamp;

@Component
public class ArticleActivationCommand extends BaseActivationCommand implements ApplicationContextAware {

    private static ArticleRepository ARTICLE_REPOSITORY;
    private static ArticleAssembler  ARTICLE_ASSEMBLER;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ARTICLE_REPOSITORY = applicationContext.getBean(ArticleRepository.class);
        ARTICLE_ASSEMBLER = applicationContext.getBean(ArticleAssembler.class);
    }

    @Override
    public boolean execute(Context context) throws Exception {
        if (getPath().startsWith("/articles/")) {
            publishArticle(getJCRNode(context));
        } else if (getPath().startsWith("/articles") && ((Boolean) context.get("recursive"))) {
            publishArticles(getJCRNode(context));
        }

        return true;
    }

    private void publishArticle(Node articleNode) throws Exception {
        Article article = null;
        try {
            article = ARTICLE_REPOSITORY.readArticle(articleNode.getName());
            article.setPublished(new Timestamp(System.currentTimeMillis()));
            ARTICLE_REPOSITORY.updateArticle(article);
        } catch (IncorrectResultSizeDataAccessException ignore) {
            article = ARTICLE_ASSEMBLER.assembleFrom(articleNode.getName(), articleNode.getNode("main-column").getNode("0"));
            ARTICLE_REPOSITORY.createArticle(article);
        }
    }

    private void publishArticles(Node articlesNode) throws Exception {
        Node articleNode;
        NodeIterator iterator = articlesNode.getNodes();
        while (iterator.hasNext()) {
            articleNode = iterator.nextNode();
            if ("mgnl:page".equals(articleNode.getPrimaryNodeType().getName())) {
                publishArticle(articleNode);
            }
        }
    }
}
