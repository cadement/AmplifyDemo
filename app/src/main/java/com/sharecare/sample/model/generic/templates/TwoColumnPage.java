package com.sharecare.sample.model.generic.templates;

import info.magnolia.module.blossom.annotation.Area;
import info.magnolia.module.blossom.annotation.AvailableComponentClasses;
import info.magnolia.module.blossom.annotation.Template;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Template(id = "app:pages/two-column", title = "Two-Column Template")
@Controller
public class TwoColumnPage {

    @RequestMapping("/two-column")
    public String render() {
        return "app/page/two-column.jsp";
    }

    @Area(value = "header", title = "Header")
    @Controller
    public static class HeaderArea {

        @RequestMapping("/two-column/header")
        public String render() {
            return "app/area/header.jsp";
        }
    }

    @Area(value = "main-column", dialog = "generic-title-dialog")
    @AvailableComponentClasses({StandardComponent.class})
    @Controller
    public static class MainColumnArea {

        @RequestMapping("/two-column/main-column")
        public String render() {
            return "app/area/generic.jsp";
        }
    }

    @Area(value = "side-column", dialog = "generic-title-dialog")
    @AvailableComponentClasses({StandardComponent.class})
    @Controller
    public static class SideColumnArea {

        @RequestMapping("/two-column/side-column")
        public String render() {
            return "app/area/generic.jsp";
        }
    }
}