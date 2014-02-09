package com.sharecare.sample.model.article.templates;

import com.sharecare.sample.model.article.Article;
import com.sharecare.sample.model.article.ArticleRepository;
import com.sharecare.sample.model.article.ReadArticle;
import com.sharecare.sample.model.article.ReadArticleRepository;
import com.sharecare.sample.model.generic.templates.StandardComponent;
import com.sharecare.sample.model.user.User;
import info.magnolia.module.blossom.annotation.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Template(id = "app:components/read-articles", title = "Read Articles", dialog = "generic-title-dialog")
@StandardComponent
@Controller
public class ReadArticlesComponent {

    @Autowired
    private ReadArticleRepository readArticleRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @ModelAttribute("articles")
    public List<Article> resolveUser(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        List<Article> articles;
        if (null != user) {
            List<ReadArticle> readArticles = readArticleRepository.findArticlesReadBy(user, 5);
            articles = new ArrayList<Article>(readArticles.size());
            for (ReadArticle readArticle : readArticles) {
                articles.add(articleRepository.readArticle(readArticle.getArticleUrl()));
            }
        } else {
            articles = Collections.emptyList();
        }

        return articles;
    }

    @RequestMapping("/read-articles")
    public String render(@ModelAttribute("articles") List<Article> articles, Model model) {
        model.addAttribute("articles", articles);
        return "app/component/articles.jsp";
    }
}
