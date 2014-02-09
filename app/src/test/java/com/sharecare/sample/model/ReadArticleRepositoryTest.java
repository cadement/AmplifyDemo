package com.sharecare.sample.model;

import com.sharecare.sample.model.article.ReadArticle;
import com.sharecare.sample.model.article.ReadArticleRepository;
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
public class ReadArticleRepositoryTest {

    @Autowired private ReadArticleRepository readArticleRepository;

    @Test
    public void shouldReadArticleCRUD() {
        // can create article
        readArticleRepository.createReadArticle(READ_ARTICLE3);
        assertEquals(READ_ARTICLE3, readArticleRepository.readReadArticle(USER2, ARTICLE1));

        // can read article
        assertEquals(READ_ARTICLE1, readArticleRepository.readReadArticle(USER1, ARTICLE1));
        assertEquals(READ_ARTICLE2, readArticleRepository.readReadArticle(USER1, ARTICLE2));

        // can update article
        ReadArticle readArticle1 = readArticleRepository.readReadArticle(USER1, ARTICLE1);
        readArticle1.setReadOn(new Timestamp(System.currentTimeMillis()));
        readArticleRepository.updateReadArticle(readArticle1);
        assertEquals(readArticle1, readArticleRepository.readReadArticle(USER1, ARTICLE1));

        readArticle1.setReadOn(READ_ARTICLE1.getReadOn());
        readArticleRepository.updateReadArticle(readArticle1);
        assertEquals(READ_ARTICLE1, readArticleRepository.readReadArticle(USER1, ARTICLE1));

        // can delete article
        readArticleRepository.deleteReadArticle(READ_ARTICLE3);
        ReadArticle article3 = null;
        try {
            article3 = readArticleRepository.readReadArticle(USER2, ARTICLE1);
        } catch (EmptyResultDataAccessException ignore) {
        }
        assertNull(article3);
    }

    @Test
    public void shouldFindUnreadArticles() {
        readArticleRepository.createReadArticle(READ_ARTICLE3);

        assertEquals(asList(READ_ARTICLE1, READ_ARTICLE2), readArticleRepository.findArticlesReadBy(USER1, 10));
        assertEquals(asList(READ_ARTICLE3), readArticleRepository.findArticlesReadBy(USER2, 10));

        readArticleRepository.deleteReadArticle(READ_ARTICLE3);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void shouldDeleteReferencesToArticle() {
        readArticleRepository.createReadArticle(READ_ARTICLE4);
        readArticleRepository.deleteReferencesTo(ARTICLE3);
        readArticleRepository.readReadArticle(USER1, ARTICLE3);
    }

}
