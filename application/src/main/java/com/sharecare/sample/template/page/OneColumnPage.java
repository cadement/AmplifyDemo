package com.sharecare.sample.template.page;

import com.sharecare.sample.template.StandardComponent;
import com.sharecare.sample.config.ApplicationModule;
import info.magnolia.module.blossom.annotation.Area;
import info.magnolia.module.blossom.annotation.AvailableComponentClasses;
import info.magnolia.module.blossom.annotation.Template;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Template(id = ApplicationModule.NAME + ":pages/one-column", title = "One-Column Template")
@Controller
public class OneColumnPage {

    @RequestMapping("/one-column")
    public String render() {
        return "page/" + ApplicationModule.NAME + "/one-column.jsp";
    }

    @Area(value = "main-column", title = "Main Column")
    @AvailableComponentClasses({StandardComponent.class})
    @Controller
    public static class MainColumnArea {

        @RequestMapping("/one-column/main-column")
        public String render() {
            return "area/" + ApplicationModule.NAME + "/generic.jsp";
        }
    }
}