package com.sharecare.jcr;

import com.sharecare.jcr.search.JCRSearch;
import com.sharecare.jcr.search.NodeOrderBy;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

/**
 * Created by caseydement on 12/31/13.
 */
public interface SearchBuilder {
    SearchBuilder nodeType(NodeType nodeType);

    SearchBuilder addPropertyEquals(String name, String value);

    SearchBuilder addPropertyLike(String name, String value);

    SearchBuilder addPropertyExists(String name);

    SearchBuilder addIsChildOf(String path);

    SearchBuilder addIsChildOf(Node parent) throws RepositoryException;

    SearchBuilder addIsDescendantOf(String path);

    SearchBuilder addIsDescendantOf(Node parent) throws RepositoryException;

    SearchBuilder addOrderBy(String name, NodeOrderBy.Ordering ordering);

    JCRSearch build();
}
