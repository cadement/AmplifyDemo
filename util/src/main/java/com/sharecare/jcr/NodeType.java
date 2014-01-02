package com.sharecare.jcr;

public enum NodeType {

    BASE("nt:base"),
    PAGE("mgnl:page");

    private final String id;

    private NodeType(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }
}
