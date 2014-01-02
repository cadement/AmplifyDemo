package com.sharecare.jcr.impl;

class PropertyEqualsCriterion implements NodeCriterion {

    private final String name;
    private final String value;

    public PropertyEqualsCriterion(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getQuery() {
        return name + " = '" + value + "'";
    }
}
