package com.sharecare.sample.model.article;

import java.sql.Timestamp;

public class ReadArticle {

    private String    userUrl;
    private String    articleUrl;
    private Timestamp readOn;

    public ReadArticle(String userUrl, String articleUrl, Timestamp readOn) {
        this.userUrl = userUrl;
        this.articleUrl = articleUrl;
        this.readOn = readOn;
    }

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public Timestamp getReadOn() {
        return readOn;
    }

    public void setReadOn(Timestamp readOn) {
        this.readOn = readOn;
    }

    @Override
    public String toString() {
        return "ReadArticle{" +
                "userUrl='" + userUrl + '\'' +
                ", articleUrl='" + articleUrl + '\'' +
                ", readOn=" + readOn +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReadArticle)) return false;

        ReadArticle that = (ReadArticle) o;

        if (articleUrl != null ? !articleUrl.equals(that.articleUrl) : that.articleUrl != null) return false;
        if (readOn != null ? !readOn.equals(that.readOn) : that.readOn != null) return false;
        if (userUrl != null ? !userUrl.equals(that.userUrl) : that.userUrl != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userUrl != null ? userUrl.hashCode() : 0;
        result = 31 * result + (articleUrl != null ? articleUrl.hashCode() : 0);
        result = 31 * result + (readOn != null ? readOn.hashCode() : 0);
        return result;
    }
}
