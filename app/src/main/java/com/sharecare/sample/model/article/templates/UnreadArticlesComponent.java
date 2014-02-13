package com.sharecare.sample.model.article.templates;

import com.sharecare.sample.model.article.Article;
import com.sharecare.sample.model.article.ArticleRepository;
import com.sharecare.sample.model.user.User;
import com.sharecare.sample.model.generic.templates.StandardComponent;
import info.magnolia.module.blossom.annotation.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Template(id = "app:components/unread-articles", title = "Unread Articles", dialog = "generic-title-dialog")
@StandardComponent
@Controller
public class UnreadArticlesComponent {

    @Autowired
    private ArticleRepository articleRepository;

    @ModelAttribute("articles")
    public List<Article> resolveUser(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        if (null != user) {
            return articleRepository.findUnreadArticles(user, 5);
        } else {
            return articleRepository.findArticles(5);

        }
    }

    @RequestMapping("/unread-articles")
    public String render(@ModelAttribute("articles") List<Article> articles, Model model) {
        model.addAttribute("articles", articles);
        return "app/component/articles.jsp";
    }
}
