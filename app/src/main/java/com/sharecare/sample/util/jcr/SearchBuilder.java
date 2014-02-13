package com.sharecare.sample.util.jcr;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

public interface SearchBuilder {

    SearchBuilder nodeType(NodeType nodeType);

    SearchBuilder addPropertyEquals(String name, String value);

    SearchBuilder addPropertyLike(String name, String value);

    SearchBuilder addPropertyExists(String name);

    SearchBuilder addIsChildOf(String path);

    SearchBuilder addIsChildOf(Node parent) throws RepositoryException;

    SearchBuilder addIsDescendantOf(String path);

    SearchBuilder addIsDescendantOf(Node parent) throws RepositoryException;

    SearchBuilder addOrderBy(String name, Ordering ordering);

    Search build();
}
