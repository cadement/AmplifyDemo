package com.sharecare.sample.model.article;

import org.springframework.stereotype.Component;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import java.sql.Timestamp;

@Component
public class ArticleAssembler {

    public Article assembleFrom(String name, Node articleNode) throws RepositoryException {
        return new Article(
                name,
                articleNode.getProperty("title").getValue().getString(),
                articleNode.getProperty("category").getValue().getString(),
                new Timestamp(System.currentTimeMillis())
        );

    }
}
