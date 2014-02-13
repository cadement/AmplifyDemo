package com.sharecare.sample.model.article;

import java.sql.Timestamp;

public class Article {

    public static Article DUMMY_ARTICLE() {
        return new Article("dummy", "Dummy Article", "Politics", null);
    }

    private String    url;
    private String    title;
    private String    category;
    private Timestamp published;

    public Article(String url, String title, String category, Timestamp published) {
        this.url = url;
        this.title = title;
        this.category = category;
        this.published = published;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Timestamp getPublished() {
        return published;
    }

    public void setPublished(Timestamp published) {
        this.published = published;
    }

    @Override
    public String toString() {
        return "Article{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", published=" + published +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;

        Article article = (Article) o;

        if (!url.equals(article.url)) return false;
        if (title != null ? !title.equals(article.title) : article.title != null) return false;
        if (category != null ? !category.equals(article.category) : article.category != null) return false;
        if (published != null ? !published.equals(article.published) : article.published != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = url.hashCode();
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (published != null ? published.hashCode() : 0);
        return result;
    }
}
