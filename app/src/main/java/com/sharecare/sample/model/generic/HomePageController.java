package com.sharecare.sample.model.generic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomePageController {

    @RequestMapping("/")
    public ModelAndView getHomePage() {
        ModelAndView modelAndView = new ModelAndView("/home.html");
        modelAndView.addObject("title", "Welcome to the Sharecare Demo Application");
        return modelAndView;
    }
}
