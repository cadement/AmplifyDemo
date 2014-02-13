package com.sharecare.sample.model.article.activation;

import com.sharecare.sample.model.article.Article;
import com.sharecare.sample.model.article.ArticleRepository;
import com.sharecare.sample.model.article.ReadArticleRepository;
import info.magnolia.context.Context;
import info.magnolia.module.activation.commands.BaseActivationCommand;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import javax.jcr.Node;
import javax.jcr.NodeIterator;

@Component
public class ArticleDeactivationCommand extends BaseActivationCommand implements ApplicationContextAware {

    private static ArticleRepository     ARTICLE_REPOSITORY;
    private static ReadArticleRepository READ_ARTICLE_REPOSITORY;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ARTICLE_REPOSITORY = applicationContext.getBean(ArticleRepository.class);
        READ_ARTICLE_REPOSITORY = applicationContext.getBean(ReadArticleRepository.class);
    }

    @Override
    public boolean execute(Context context) throws Exception {
        if (getPath().startsWith("/articles/")) {
            unpublishArticle(getJCRNode(context));
        } else if (getPath().startsWith("/articles")) {
            unpublishArticles(getJCRNode(context));
        }

        return true;
    }

    private void unpublishArticles(Node articlesNode) throws Exception {
        Node articleNode;
        NodeIterator iterator = articlesNode.getNodes();
        while (iterator.hasNext()) {
            articleNode = iterator.nextNode();
            if ("mgnl:page".equals(articleNode.getPrimaryNodeType().getName())) {
                unpublishArticle(articleNode);
            }
        }
    }

    private void unpublishArticle(Node node) throws Exception {
        try {
            Article article = ARTICLE_REPOSITORY.readArticle(node.getName());
            ARTICLE_REPOSITORY.deleteArticle(article);
            READ_ARTICLE_REPOSITORY.deleteReferencesTo(article);
        } catch (DataAccessException ignore) {
        }
    }
}
