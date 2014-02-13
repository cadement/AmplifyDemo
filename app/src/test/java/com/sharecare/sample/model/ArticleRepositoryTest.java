package com.sharecare.sample.model;

import com.sharecare.sample.model.article.Article;
import com.sharecare.sample.model.article.ArticleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;

import static com.sharecare.sample.model.ModelFixture.*;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/test-context.xml","/test-security.xml"})
public class ArticleRepositoryTest {

    @Autowired private ArticleRepository articleRepository;

    @Test
    public void shouldArticleCRUD() {
        // can create article
        articleRepository.createArticle(ARTICLE3);
        assertEquals(ARTICLE3, articleRepository.readArticle(ARTICLE3.getUrl()));

        // can read article
        assertEquals(ARTICLE1, articleRepository.readArticle(ARTICLE1.getUrl()));
        assertEquals(ARTICLE2, articleRepository.readArticle(ARTICLE2.getUrl()));

        // can update article
        Article article1 = articleRepository.readArticle(ARTICLE1.getUrl());
        article1.setPublished(new Timestamp(System.currentTimeMillis()));
        articleRepository.updateArticle(article1);
        assertEquals(article1, articleRepository.readArticle(ARTICLE1.getUrl()));

        Article article2 = articleRepository.readArticle(ARTICLE2.getUrl());
        article2.setPublished(new Timestamp(System.currentTimeMillis()));
        articleRepository.updateArticle(article2);
        assertEquals(article2, articleRepository.readArticle(ARTICLE2.getUrl()));

        // can delete article
        articleRepository.deleteArticle(ARTICLE3);
        Article article3 = null;
        try {
            article3 = articleRepository.readArticle(ARTICLE3.getUrl());
        } catch (EmptyResultDataAccessException ignore) {
        }
        assertNull(article3);
    }

    @Test
    public void shouldFindUnreadArticles() {
        articleRepository.createArticle(ARTICLE3);

        assertEquals(asList(ARTICLE3), articleRepository.findUnreadArticles(USER1, 10));
        assertEquals(asList(ARTICLE3, ARTICLE2, ARTICLE1), articleRepository.findUnreadArticles(USER2, 10));

        articleRepository.deleteArticle(ARTICLE3);
    }

    @Test
    public void shouldFindArticlesInCategory() {
        articleRepository.createArticle(ARTICLE3);

        assertEquals(asList(ARTICLE2, ARTICLE1), articleRepository.findArticlesInCategory("Category 1", 10));
        assertEquals(asList(ARTICLE3), articleRepository.findArticlesInCategory("Category 2", 10));

        articleRepository.deleteArticle(ARTICLE3);
    }

    @Test
    public void shouldFindAllArticles() {
        assertEquals(asList(ARTICLE2, ARTICLE1), articleRepository.findArticles(10));
    }

}
