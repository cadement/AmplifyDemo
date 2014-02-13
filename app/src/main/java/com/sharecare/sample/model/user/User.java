package com.sharecare.sample.model.user;

import java.sql.Timestamp;

public class User {

    public static User DUMMY_USER() {
        return new User("anonymous", "", "Anonymous", "anonymous@sharecare.com", "Atlanta, GA", false, null, null);
    }

    private String    url;
    private String    password;
    private String    name;
    private String    email;
    private String    address;
    private Boolean   admin;
    private Timestamp lastUpdated;
    private Timestamp lastLogin;

    public User(String url, String password, String name, String email, String address, Boolean admin, Timestamp lastUpdated, Timestamp lastLogin) {
        this.name = name;
        this.password = password;
        this.url = url;
        this.email = email;
        this.address = address;
        this.admin = admin;
        this.lastUpdated = lastUpdated;
        this.lastLogin = lastLogin;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    @Override
    public String toString() {
        return "User{" +
                ", url='" + url + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", admin='" + admin + '\'' +
                ", lastUpdated='" + lastUpdated + '\'' +
                ", lastLogin=" + lastLogin +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!url.equals(user.url)) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (address != null ? !address.equals(user.address) : user.address != null) return false;
        if (admin != null ? !admin.equals(user.admin) : user.admin != null) return false;
        if (lastUpdated != null ? !lastUpdated.equals(user.lastUpdated) : user.lastUpdated != null) return false;
        if (lastLogin != null ? !lastLogin.equals(user.lastLogin) : user.lastLogin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = url.hashCode();
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (admin != null ? admin.hashCode() : 0);
        result = 31 * result + (lastUpdated != null ? lastUpdated.hashCode() : 0);
        result = 31 * result + (lastLogin != null ? lastLogin.hashCode() : 0);
        return result;
    }
}
