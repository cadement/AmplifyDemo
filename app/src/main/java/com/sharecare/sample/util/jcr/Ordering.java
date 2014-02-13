package com.sharecare.sample.util.jcr;

public enum Ordering {

    ASCENDING("ASC"),
    DESCENDING("DESC");

    private final String value;

    Ordering(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
