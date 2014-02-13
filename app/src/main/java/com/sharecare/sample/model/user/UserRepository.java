package com.sharecare.sample.model.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static java.util.Collections.singletonMap;

@Repository
public class UserRepository {

    private static final RowMapper<User> USER_MAPPER = new UserMapper();

    private final NamedParameterJdbcTemplate db;

    @Value("${user.create}") private String createQuery;
    @Value("${user.read}") private   String readQuery;
    @Value("${user.update}") private String updateQuery;
    @Value("${user.delete}") private String deleteQuery;
    @Value("${user.byName}") private String byNameQuery;
    @Value("${user.all}") private    String byAllQuery;

    @Autowired
    public UserRepository(DataSource dataSource) throws SQLException {
        db = new NamedParameterJdbcTemplate(dataSource);
    }

    public void createUser(User user) {
        db.update(createQuery, new BeanPropertySqlParameterSource(user));
    }

    public User readUser(String url) {
        return db.queryForObject(readQuery, singletonMap("url", url), USER_MAPPER);
    }

    public void updateUser(User user) {
        db.update(updateQuery, new BeanPropertySqlParameterSource(user));
    }

    public void deleteUser(User user) {
        db.update(deleteQuery, new BeanPropertySqlParameterSource(user));
    }

    public User findByName(String name) {
        return db.queryForObject(byNameQuery, singletonMap("name", name), USER_MAPPER);
    }

    public List<User> findAllUsers(int limit) {
        return db.query(byAllQuery, singletonMap("limit", limit), USER_MAPPER);
    }

    public static class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(
                    rs.getString("url"),
                    rs.getString("password"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("address"),
                    rs.getBoolean("admin"),
                    rs.getTimestamp("lastUpdated"),
                    rs.getTimestamp("lastLogin")
            );
        }
    }
}
