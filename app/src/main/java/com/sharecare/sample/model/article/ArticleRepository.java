package com.sharecare.sample.model.article;

import com.sharecare.sample.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonMap;

@Repository
public class ArticleRepository {

    private static final RowMapper<Article> ARTICLE_MAPPER = new ArticleMapper();

    private final NamedParameterJdbcTemplate db;

    @Value("${article.create}") private     String createQuery;
    @Value("${article.read}") private       String readQuery;
    @Value("${article.update}") private     String updateQuery;
    @Value("${article.delete}") private     String deleteQuery;
    @Value("${article.unread}") private     String byPublishedQuery;
    @Value("${article.byCategory}") private String byCategoryQuery;
    @Value("${article.all}") private        String byAllQuery;

    @Autowired
    public ArticleRepository(DataSource dataSource) throws SQLException {
        db = new NamedParameterJdbcTemplate(dataSource);
    }

    public void createArticle(Article article) {
        db.update(createQuery, new BeanPropertySqlParameterSource(article));
    }

    public Article readArticle(String url) {
        return db.queryForObject(readQuery, singletonMap("url", url), ARTICLE_MAPPER);
    }

    public void updateArticle(Article article) {
        db.update(updateQuery, new BeanPropertySqlParameterSource(article));
    }

    public void deleteArticle(Article article) {
        db.update(deleteQuery, new BeanPropertySqlParameterSource(article));
    }

    public List<Article> findUnreadArticles(User unreadBy, Integer limit) {
        Map<String, Object> queryParams = new HashMap<String, Object>(2);
        queryParams.put("userUrl", unreadBy.getUrl());
        queryParams.put("limit", limit);
        return db.query(byPublishedQuery, queryParams, ARTICLE_MAPPER);
    }

    public List<Article> findArticlesInCategory(String category, Integer limit) {
        Map<String, Object> queryParams = new HashMap<String, Object>(2);
        queryParams.put("category", category);
        queryParams.put("limit", limit);
        return db.query(byCategoryQuery, queryParams, ARTICLE_MAPPER);
    }

    public List<Article> findArticles(int limit) {
        return db.query(byAllQuery, singletonMap("limit", limit), ARTICLE_MAPPER);
    }

    private static class ArticleMapper implements RowMapper<Article> {
        @Override
        public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Article(
                    rs.getString("url"),
                    rs.getString("title"),
                    rs.getString("category"),
                    rs.getTimestamp("published")
            );
        }
    }
}
