package com.sharecare.jcr;

/**
* Created by caseydement on 12/31/13.
*/
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
