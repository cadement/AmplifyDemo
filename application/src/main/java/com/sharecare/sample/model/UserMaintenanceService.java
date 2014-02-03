package com.sharecare.sample.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class UserMaintenanceService {

    private static final String FIND_USER_BY_URL = "SELECT * FROM User WHERE url=?";

    private final DataSource dataSource;

    @Autowired
    public UserMaintenanceService(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
    }

    public User getUser(String url) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_URL);
        statement.setString(1, url);
        ResultSet resultSet = statement.executeQuery();

        if (!resultSet.next()) {
            throw new IllegalArgumentException("Unknown User URL " + url);
        } else {
            return new User(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("password"),
                    resultSet.getString("url"),
                    resultSet.getDate("last_login")
            );
        }
    }
}
