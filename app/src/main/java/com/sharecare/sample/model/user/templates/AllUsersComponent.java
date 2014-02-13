package com.sharecare.sample.model.user.templates;

import com.sharecare.sample.model.article.Article;
import com.sharecare.sample.model.article.ArticleRepository;
import com.sharecare.sample.model.generic.templates.StandardComponent;
import com.sharecare.sample.model.user.User;
import com.sharecare.sample.model.user.UserRepository;
import info.magnolia.module.blossom.annotation.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Template(id = "app:components/all-users", title = "All Users", dialog = "generic-title-dialog")
@StandardComponent
@Controller
public class AllUsersComponent {

    @Autowired
    private UserRepository userRepository;

    @ModelAttribute("users")
    public List<User> resolveUsers() {
        return userRepository.findAllUsers(20);
    }

    @RequestMapping("/all-users")
    public String render(@ModelAttribute("users") List<User> users, Model model) {
        model.addAttribute("users", users);
        return "app/component/user/all.jsp";
    }
}
