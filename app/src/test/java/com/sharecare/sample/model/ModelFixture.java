package com.sharecare.sample.model;

import com.sharecare.sample.model.article.Article;
import com.sharecare.sample.model.article.ReadArticle;
import com.sharecare.sample.model.user.User;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ModelFixture {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");

    private static Timestamp GET_TIMESTAMP(String dateString) {
        try {
            return new Timestamp(DATE_FORMAT.parse(dateString).getTime());
        } catch (ParseException e) {
            throw new RuntimeException("Cannot part date string " + dateString);
        }
    }

    static final User USER1 = new User("user-1", "pw1", "User 1", "user1@sharecare.com", "Atlanta, GA", false, null, null);
    static final User USER2 = new User("user-2", "pw2", "User 2", "user2@sharecare.com", "Atlanta, GA", false, null, null);
    static final User USER3 = new User("user-3", "pw3", "User 3", "user3@sharecare.com", "Atlanta, GA", false, null, null);

    static final Article ARTICLE1 = new Article("article-1", "Title 1", "Category 1", GET_TIMESTAMP("2001-01-01 00:00:00"));
    static final Article ARTICLE2 = new Article("article-2", "Title 2", "Category 1", GET_TIMESTAMP("2002-01-02 00:00:00"));
    static final Article ARTICLE3 = new Article("article-3", "Title 3", "Category 2", GET_TIMESTAMP("2003-01-03 00:00:00"));

    static final ReadArticle READ_ARTICLE1 = new ReadArticle("user-1", "article-1", GET_TIMESTAMP("2014-01-02 00:00:00"));
    static final ReadArticle READ_ARTICLE2 = new ReadArticle("user-1", "article-2", GET_TIMESTAMP("2014-01-01 00:00:00"));
    static final ReadArticle READ_ARTICLE3 = new ReadArticle("user-2", "article-1", GET_TIMESTAMP("2014-02-01 00:00:00"));
    static final ReadArticle READ_ARTICLE4 = new ReadArticle("user-1", "article-3", GET_TIMESTAMP("2014-02-01 00:00:00"));
}