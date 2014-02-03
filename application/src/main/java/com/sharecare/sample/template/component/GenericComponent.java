package com.sharecare.sample.template.component;

import com.sharecare.sample.config.ApplicationModule;
import com.sharecare.sample.template.StandardComponent;
import info.magnolia.module.blossom.annotation.DialogFactory;
import info.magnolia.module.blossom.annotation.Template;
import info.magnolia.ui.dialog.config.DialogBuilder;
import info.magnolia.ui.framework.config.UiConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Template(
        id = ApplicationModule.NAME + ":components/generic",
        title = "Generic Component",
        dialog = "generic-component-dialog"
)
@StandardComponent
@Controller
public class GenericComponent {

    @RequestMapping("/generic")
    public String render() {
        return "component/" + ApplicationModule.NAME + "/generic.jsp";
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