package com.sharecare.sample.template.page;

import com.sharecare.sample.config.ApplicationModule;
import com.sharecare.sample.template.StandardComponent;
import info.magnolia.module.blossom.annotation.Area;
import info.magnolia.module.blossom.annotation.AvailableComponentClasses;
import info.magnolia.module.blossom.annotation.Template;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Template(id = ApplicationModule.NAME + ":pages/two-column", title = "Two-Column Template")
@Controller
public class TwoColumnPage {

    @RequestMapping("/two-column")
    public String render() {
        return "page/" + ApplicationModule.NAME + "/two-column.jsp";
    }

    @Area(value = "main-column", dialog = "generic-area-dialog")
    @AvailableComponentClasses({StandardComponent.class})
    @Controller
    public static class MainColumnArea {

        @RequestMapping("/two-column/main-column")
        public String render() {
            return "area/" + ApplicationModule.NAME + "/generic.jsp";
        }
    }

    @Area(value = "side-column", dialog = "generic-area-dialog")
    @AvailableComponentClasses({StandardComponent.class})
    @Controller
    public static class SideColumnArea {

        @RequestMapping("/two-column/side-column")
        public String render() {
            return "area/" + ApplicationModule.NAME + "/generic.jsp";
        }
    }
}