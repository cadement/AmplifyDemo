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

import static java.util.Collections.singletonMap;

@Repository
public class UserRepository {

    @Value("${user.create}") private String createQuery;
    @Value("${user.read}") private   String readQuery;
    @Value("${user.update}") private String updateQuery;
    @Value("${user.delete}") private String deleteQuery;
    @Value("${user.byName}") private String byNameQuery;

    private final NamedParameterJdbcTemplate db;

    @Autowired
    public UserRepository(DataSource dataSource) throws SQLException {
        db = new NamedParameterJdbcTemplate(dataSource);
    }

    public void createUser(User user) {
        db.update(createQuery, new BeanPropertySqlParameterSource(user));
    }

    public User readUser(String url) {
        return db.queryForObject(readQuery, singletonMap("url", url), new UserAssembler());
    }

    public void updateUser(User user) {
        db.update(updateQuery, new BeanPropertySqlParameterSource(user));
    }

    public void deleteUser(User user) {
        db.update(deleteQuery, new BeanPropertySqlParameterSource(user));
    }

    public User findByName(String name) {
        return db.queryForObject(byNameQuery, singletonMap("name", name), new UserAssembler());
    }

    public static class UserAssembler implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(
                    rs.getString("url"),
                    rs.getString("password"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("address"),
                    rs.getTimestamp("lastLogin")
            );
        }
    }
}
