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
public class ReadArticleRepository {

    @Value("${readarticle.create}") private        String createQuery;
    @Value("${readarticle.read}") private          String readQuery;
    @Value("${readarticle.update}") private        String updateQuery;
    @Value("${readarticle.delete}") private        String deleteQuery;
    @Value("${readarticle.readBy}") private        String readByQuery;
    @Value("${readarticle.deleteArticle}") private String deleteArticleQuery;

    private final NamedParameterJdbcTemplate db;
    private final RowMapper<ReadArticle> readArticleRowMapper = new RowMapper<ReadArticle>() {
        @Override
        public ReadArticle mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new ReadArticle(rs.getString("userUrl"), rs.getString("articleUrl"), rs.getTimestamp("readOn"));
        }
    };

    @Autowired
    public ReadArticleRepository(DataSource dataSource) throws SQLException {
        db = new NamedParameterJdbcTemplate(dataSource);
    }

    public void createReadArticle(ReadArticle readArticle) {
        db.update(createQuery, new BeanPropertySqlParameterSource(readArticle));
    }

    public ReadArticle readReadArticle(User user, Article article) {
        Map<String, Object> queryParams = new HashMap<String, Object>(2);
        queryParams.put("userUrl", user.getUrl());
        queryParams.put("articleUrl", article.getUrl());
        return db.queryForObject(readQuery, queryParams, readArticleRowMapper);
    }

    public void updateReadArticle(ReadArticle readArticle) {
        db.update(updateQuery, new BeanPropertySqlParameterSource(readArticle));
    }

    public void deleteReadArticle(ReadArticle readArticle) {
        db.update(deleteQuery, new BeanPropertySqlParameterSource(readArticle));
    }

    public List<ReadArticle> findArticlesReadBy(User readBy, Integer limit) {
        Map<String, Object> queryParams = new HashMap<String, Object>(2);
        queryParams.put("userUrl", readBy.getUrl());
        queryParams.put("limit", limit);
        return db.query(readByQuery, queryParams, readArticleRowMapper);
    }

    public void deleteReferencesTo(Article article) {
        db.update(deleteArticleQuery, singletonMap("articleUrl", article.getUrl()));
    }
}
