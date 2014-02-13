package com.sharecare.sample.model.user.templates;

import com.sharecare.sample.auth.spring.SpringAuthentication;
import com.sharecare.sample.model.generic.templates.StandardComponent;
import com.sharecare.sample.model.user.User;
import info.magnolia.module.blossom.annotation.Template;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Template(id = "app:components/create-user-profile", title = "Create User Profile")
@StandardComponent
@Controller
public class CreateUserProfileComponent {

    @ModelAttribute("user")
    public User resolveUser(HttpServletRequest request) {
        Object userObj = request.getAttribute("user");
        return (null != userObj) ? (User) userObj : User.DUMMY_USER();
    }

    @RequestMapping("/create-user-profile")
    public String render(
            @ModelAttribute("user") User user,
            Model model,
            SpringAuthentication authentication
    ) {
        model.addAttribute("user", user);
        return "app/component/user/create.jsp";
    }
}