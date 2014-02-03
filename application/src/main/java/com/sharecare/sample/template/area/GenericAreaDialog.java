package com.sharecare.sample.template.area;

import info.magnolia.module.blossom.annotation.DialogFactory;
import info.magnolia.module.blossom.annotation.TabFactory;
import info.magnolia.ui.form.config.TabBuilder;
import info.magnolia.ui.framework.config.UiConfig;

@DialogFactory("generic-area-dialog")
public class GenericAreaDialog {

    @TabFactory("Settings")
    public void settingsTab(UiConfig cfg, TabBuilder tab) {
        tab.fields(
                cfg.fields.text("title").label("Title")
        );
    }

}
