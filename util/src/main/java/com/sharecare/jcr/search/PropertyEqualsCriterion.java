package com.sharecare.jcr.search;

public class PropertyEqualsCriterion implements NodeCriterion {
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
