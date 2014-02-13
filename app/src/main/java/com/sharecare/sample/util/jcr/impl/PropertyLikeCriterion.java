package com.sharecare.sample.util.jcr.impl;

class PropertyLikeCriterion implements NodeCriterion {

    private final String name;
    private final String value;

    public PropertyLikeCriterion(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getQuery() {
        return name + " LIKE '" + value + "'";
    }
}
