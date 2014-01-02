package com.sharecare.jcr.impl;

import com.sharecare.jcr.Search;
import com.sharecare.jcr.NodeType;
import com.sharecare.jcr.Ordering;
import com.sharecare.jcr.SearchBuilder;
import org.springframework.stereotype.Component;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

@Component
class SearchBuilderImpl implements SearchBuilder {

    private final NodeType            nodeType;
    private final List<NodeCriterion> nodeCriteria;
    private final List<NodeOrderBy>   nodeOrderBys;

    public SearchBuilderImpl() {
        this.nodeType = NodeType.BASE;
        this.nodeCriteria = emptyList();
        this.nodeOrderBys = emptyList();
    }

    private SearchBuilderImpl(NodeType nodeType, List<NodeCriterion> nodeCriteria, List<NodeOrderBy> nodeOrderBys) {
        this.nodeType = nodeType;
        this.nodeCriteria = nodeCriteria;
        this.nodeOrderBys = nodeOrderBys;
    }

    @Override
    public SearchBuilder nodeType(NodeType nodeType) {
        return new SearchBuilderImpl(nodeType, nodeCriteria, nodeOrderBys);
    }

    @Override public SearchBuilder addPropertyEquals(String name, String value) {
        List<NodeCriterion> nodeCriteria = new ArrayList<NodeCriterion>(this.nodeCriteria.size() + 1);
        nodeCriteria.addAll(this.nodeCriteria);
        nodeCriteria.add(new PropertyEqualsCriterion(name, value));
        return new SearchBuilderImpl(nodeType, nodeCriteria, nodeOrderBys);
    }

    @Override public SearchBuilder addPropertyLike(String name, String value) {
        List<NodeCriterion> nodeCriteria = new ArrayList<NodeCriterion>(this.nodeCriteria.size() + 1);
        nodeCriteria.addAll(this.nodeCriteria);
        nodeCriteria.add(new PropertyLikeCriterion(name, value));
        return new SearchBuilderImpl(nodeType, nodeCriteria, nodeOrderBys);
    }

    @Override public SearchBuilder addPropertyExists(String name) {
        List<NodeCriterion> nodeCriteria = new ArrayList<NodeCriterion>(this.nodeCriteria.size() + 1);
        nodeCriteria.addAll(this.nodeCriteria);
        nodeCriteria.add(new PropertyExistsCriterion(name));
        return new SearchBuilderImpl(nodeType, nodeCriteria, nodeOrderBys);
    }

    @Override public SearchBuilder addIsChildOf(String path) {
        List<NodeCriterion> nodeCriteria = new ArrayList<NodeCriterion>(this.nodeCriteria.size() + 1);
        nodeCriteria.addAll(this.nodeCriteria);
        nodeCriteria.add(new IsChildOfCriterion(path));
        return new SearchBuilderImpl(nodeType, nodeCriteria, nodeOrderBys);
    }

    @Override public SearchBuilder addIsChildOf(Node parent) throws RepositoryException {
        List<NodeCriterion> nodeCriteria = new ArrayList<NodeCriterion>(this.nodeCriteria.size() + 1);
        nodeCriteria.addAll(this.nodeCriteria);
        nodeCriteria.add(new IsChildOfCriterion(parent));
        return new SearchBuilderImpl(nodeType, nodeCriteria, nodeOrderBys);
    }

    @Override public SearchBuilder addIsDescendantOf(String path) {
        List<NodeCriterion> nodeCriteria = new ArrayList<NodeCriterion>(this.nodeCriteria.size() + 1);
        nodeCriteria.addAll(this.nodeCriteria);
        nodeCriteria.add(new IsDescendantOfCriterion(path));
        return new SearchBuilderImpl(nodeType, nodeCriteria, nodeOrderBys);
    }

    @Override public SearchBuilder addIsDescendantOf(Node parent) throws RepositoryException {
        List<NodeCriterion> nodeCriteria = new ArrayList<NodeCriterion>(this.nodeCriteria.size() + 1);
        nodeCriteria.addAll(this.nodeCriteria);
        nodeCriteria.add(new IsDescendantOfCriterion(parent));
        return new SearchBuilderImpl(nodeType, nodeCriteria, nodeOrderBys);
    }

    @Override public SearchBuilder addOrderBy(String name, Ordering ordering) {
        List<NodeOrderBy> nodeOrderBys = new ArrayList<NodeOrderBy>(this.nodeOrderBys.size() + 1);
        nodeOrderBys.addAll(this.nodeOrderBys);
        nodeOrderBys.add(new NodeOrderBy(name, ordering));
        return new SearchBuilderImpl(nodeType, nodeCriteria, nodeOrderBys);
    }

    @Override public Search build() {
        return new SearchImpl(nodeType, nodeCriteria, nodeOrderBys);
    }
}