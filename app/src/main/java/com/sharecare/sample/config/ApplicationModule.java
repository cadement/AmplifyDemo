package com.sharecare.sample.config;

import info.magnolia.module.ModuleLifecycle;
import info.magnolia.module.ModuleLifecycleContext;

public class ApplicationModule implements ModuleLifecycle { //extends BlossomModuleSupport

//    public static final String NAME = "application";

    public void start(ModuleLifecycleContext moduleLifecycleContext) {
        if (moduleLifecycleContext.getPhase() == ModuleLifecycleContext.PHASE_SYSTEM_STARTUP) {
//            super.initBlossomDispatcherServlet("blossom", "classpath:/blossom-servlet.xml");
        }
    }

    public void stop(ModuleLifecycleContext moduleLifecycleContext) {
        if (moduleLifecycleContext.getPhase() == ModuleLifecycleContext.PHASE_SYSTEM_SHUTDOWN) {
//            super.destroyDispatcherServlets();
//            super.closeRootWebApplicationContext();
        }
    }
}
