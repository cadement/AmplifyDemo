package com.sharecare.jcr.impl;

import com.sharecare.jcr.Ordering;

class NodeOrderBy {

    private final String   name;
    private final Ordering ordering;

    public NodeOrderBy(String name, Ordering ordering) {
        this.name = name;
        this.ordering = ordering;
    }

    public String getName() {
        return name;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public String getQuery() {
        return name + " " + ordering.value();
    }
}
