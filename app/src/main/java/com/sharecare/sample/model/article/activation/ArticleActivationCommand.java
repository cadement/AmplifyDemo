package com.sharecare.sample.model.article.activation;

import com.sharecare.sample.model.article.Article;
import com.sharecare.sample.model.article.ArticleRepository;
import info.magnolia.context.Context;
import info.magnolia.module.activation.commands.BaseActivationCommand;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import javax.jcr.Node;
import java.sql.Timestamp;

@Component
public class ArticleActivationCommand extends BaseActivationCommand implements ApplicationContextAware {

    private static ArticleRepository ARTICLE_REPOSITORY;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ARTICLE_REPOSITORY = applicationContext.getBean(ArticleRepository.class);
    }

    @Override
    public boolean execute(Context context) throws Exception {
        if (getPath().startsWith("/articles/")) {
            Node node = getJCRNode(context);

            Article article = null;
            try {
                article = ARTICLE_REPOSITORY.readArticle(node.getName());
                article.setPublished(new Timestamp(System.currentTimeMillis()));
                try {
                    ARTICLE_REPOSITORY.updateArticle(article);
                } catch (DataAccessException e) {
                    e.printStackTrace();
                    return false;
                }
            } catch (DataAccessException ignore) {
                article = new Article(
                        node.getName(),
                        node.getNode("main-column").getNode("0").getProperty("title").getValue().getString(),
                        node.getNode("main-column").getNode("0").getProperty("category").getValue().getString(),
                        new Timestamp(System.currentTimeMillis())
                );
                try {
                    ARTICLE_REPOSITORY.createArticle(article);
                } catch (DataAccessException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }

        return true;
    }
}
