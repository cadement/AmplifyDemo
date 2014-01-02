package com.sharecare.jcr.impl;

class PropertyExistsCriterion implements NodeCriterion {

    private final String name;

    public PropertyExistsCriterion(String name) {
        this.name = name;
    }

    @Override
    public String getQuery() {
        return name + " IS NOT NULL";
    }
}
