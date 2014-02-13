package com.sharecare.sample.model.generic.templates;

import info.magnolia.module.blossom.annotation.DialogFactory;
import info.magnolia.module.blossom.annotation.Template;
import info.magnolia.ui.dialog.config.DialogBuilder;
import info.magnolia.ui.framework.config.UiConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Template(id = "app:components/generic", title = "Generic Component", dialog = "generic-component-dialog")
@StandardComponent
@Controller
public class GenericComponent {

    @RequestMapping("/generic")
    public String render() {
        return "app/component/generic.jsp";
    }

    @DialogFactory("generic-component-dialog")
    public void genericComponentDialog(UiConfig cfg, DialogBuilder dialog) {
        dialog
                .form()
                .tabs(
                        cfg.forms.tab("Settings")
                                .fields(
                                        cfg.fields.text("title").label("Title")
                                )
                );
    }
}