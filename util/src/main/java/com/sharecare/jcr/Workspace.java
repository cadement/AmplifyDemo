package com.sharecare.jcr;

public enum Workspace {

    WEBSITE("website"),
    CONFIG("config");

    private String workspaceName;

    Workspace(String workspaceName) {
        this.workspaceName = workspaceName;
    }

    public String getWorkspaceName() {
        return workspaceName;
    }
}
