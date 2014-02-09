package com.sharecare.sample.model.generic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(ESIController.PATH)
public class ESIController {

    public static final String PATH = "/esi";

    @RequestMapping("/{version}/**")
    public String renderESI(@PathVariable("version") String version, HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        String magnoliaUrl = pathInfo.split(version)[1] + ".html?" + request.getQueryString();
        return magnoliaUrl;
    }
}
