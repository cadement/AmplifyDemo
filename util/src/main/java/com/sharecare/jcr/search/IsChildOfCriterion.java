package com.sharecare.jcr.search;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

public class IsChildOfCriterion implements NodeCriterion {
    private final String parentPath;

    public IsChildOfCriterion(String parentPath) {
        this.parentPath = parentPath;
    }

    public IsChildOfCriterion(Node parentNode) throws RepositoryException {
        this.parentPath = parentNode.getPath();
    }

    @Override
    public String getQuery() {
        return "ISCHILDNODE([" + parentPath + "])";
    }
}
