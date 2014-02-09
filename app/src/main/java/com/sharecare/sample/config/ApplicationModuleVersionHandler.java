package com.sharecare.sample.config;

import info.magnolia.module.DefaultModuleVersionHandler;
import info.magnolia.module.InstallContext;
import info.magnolia.module.delta.Task;

import java.util.List;

public class ApplicationModuleVersionHandler extends DefaultModuleVersionHandler {

    @Override
    protected List<Task> getBasicInstallTasks(InstallContext installContext) {
        return super.getBasicInstallTasks(installContext);
    }
}
