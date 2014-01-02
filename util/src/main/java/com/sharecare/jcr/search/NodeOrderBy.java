package com.sharecare.jcr.search;

public class NodeOrderBy {

    private final String   name;
    private final Ordering ordering;



    public static enum Ordering {
        ASCENDING("ASC"), DESCENDING("DESC");

        private final String value;

        Ordering(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }

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
