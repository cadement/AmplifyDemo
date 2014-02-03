package com.sharecare.sample.model;

import java.util.Date;

public class User {

    private final int           id;
    private final String        name;
    private final String        password;
    private final String        url;
    private final Date lastLogin;

    public User(int id, String name, String password, String url, Date lastLogin) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.url = url;
        this.lastLogin = lastLogin;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }

    public java.util.Date getLastLogin() {
        return lastLogin;
    }
}
