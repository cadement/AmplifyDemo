package com.sharecare.sample.model.generic.templates;

import info.magnolia.module.blossom.annotation.Area;
import info.magnolia.module.blossom.annotation.AvailableComponentClasses;
import info.magnolia.module.blossom.annotation.Template;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Template(id = "app:pages/one-column", title = "One-Column Template")
@Controller
public class OneColumnPage {

    @RequestMapping("/one-column")
    public String render() {
        return "app/page/one-column.jsp";
    }

    @Area(value = "header", title = "Header")
    @Controller
    public static class HeaderArea {

        @RequestMapping("/one-column/header")
        public String render() {
            return "app/area/header.jsp";
        }
    }

    @Area(value = "main-column", title = "Main Column")
    @AvailableComponentClasses({StandardComponent.class})
    @Controller
    public static class MainColumnArea {

        @RequestMapping("/one-column/main-column")
        public String render() {
            return "app/area/generic.jsp";
        }
    }
}