package com.sharecare.sample.model;

import com.sharecare.category.model.Category;
import com.sharecare.category.model.CategoryRepository;
import com.sharecare.sample.auth.spring.SpringAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getArticles() {
        ModelAndView modelAndView = new ModelAndView("/categories.html");
        modelAndView.addObject("title", "Categories");
        modelAndView.addObject("breadcrumbs", Collections.<String, String>singletonMap("/", "Home"));
        return modelAndView;
    }

    @RequestMapping(value = "/{url}", method = RequestMethod.GET)
    public ModelAndView getUserProfile(@PathVariable("url") String url,
                                       SpringAuthentication authorization) {
        ModelAndView modelAndView = new ModelAndView("/categories/category.html");

        Category category = categoryRepository.findByUrl(url);
        modelAndView.addObject("title", category.getTitle());
        modelAndView.addObject("category", category);

        Map<String, String> breadcrumbs = new LinkedHashMap<String, String>();
        breadcrumbs.put("/", "Home");
        breadcrumbs.put("/categories", "Categories");
        modelAndView.addObject("breadcrumbs", breadcrumbs);

        return modelAndView;
    }
}
