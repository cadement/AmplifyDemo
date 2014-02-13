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

@Template(id = "app:components/edit-user-profile", title = "Edit User Profile")
@StandardComponent
@Controller
public class EditUserProfileComponent {

    @ModelAttribute("user")
    public User resolveUser(HttpServletRequest request) {
        Object userObj = request.getAttribute("user");
        return (null != userObj) ? (User) userObj : User.DUMMY_USER();
    }

    @RequestMapping("/edit-user-profile")
    public String render(
            @ModelAttribute("user") User user,
            Model model,
            SpringAuthentication authentication
    ) {
        model.addAttribute("user", user);
        return "app/component/user/edit.jsp";
    }
}