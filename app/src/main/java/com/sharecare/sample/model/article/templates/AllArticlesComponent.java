package com.sharecare.sample.model.article.templates;

import com.sharecare.sample.model.article.Article;
import com.sharecare.sample.model.article.ArticleRepository;
import com.sharecare.sample.model.generic.templates.StandardComponent;
import info.magnolia.module.blossom.annotation.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Template(id = "app:components/all-articles", title = "All Articles", dialog = "generic-title-dialog")
@StandardComponent
@Controller
public class AllArticlesComponent {

    @Autowired
    private ArticleRepository articleRepository;

    @ModelAttribute("articles")
    public List<Article> resolveUser() {
        return articleRepository.findArticles(20);
    }

    @RequestMapping("/all-articles")
    public String render(@ModelAttribute("articles") List<Article> articles, Model model) {
        model.addAttribute("articles", articles);
        return "app/component/articles.jsp";
    }
}
