package com.sharecare.sample.model.generic.templates;

import info.magnolia.module.blossom.annotation.DialogFactory;
import info.magnolia.module.blossom.annotation.Template;
import info.magnolia.ui.dialog.config.DialogBuilder;
import info.magnolia.ui.framework.config.UiConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Template(id = "app:components/formattedText", title = "Formatted Text", dialog = "formatted-text-dialog")
@StandardComponent
@Controller
public class FormattedTextComponent {

    @RequestMapping("/formattedText")
    public String render() {
        return "app/component/formattedText.jsp";
    }

    @DialogFactory("formatted-text-dialog")
    public void genericComponentDialog(UiConfig cfg, DialogBuilder dialog) {
        dialog
                .form()
                .tabs(
                        cfg.forms.tab("Details")
                                .fields(
                                        cfg.fields.text("title").label("Title"),
                                        cfg.fields.richText("body").label("Body")
                                )
                );
    }
}
