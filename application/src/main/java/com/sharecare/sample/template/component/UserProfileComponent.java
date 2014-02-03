package com.sharecare.sample.template.component;

import com.sharecare.sample.config.ApplicationModule;
import com.sharecare.sample.model.User;
import com.sharecare.sample.template.StandardComponent;
import info.magnolia.module.blossom.annotation.Template;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Template(
        id = ApplicationModule.NAME + ":components/user-profile",
        title = "User Profile"
)
@StandardComponent
@Controller
public class UserProfileComponent {

    @RequestMapping("/user-profile")
    public String render(HttpServletRequest request, Model model) {
        Object userObj = request.getAttribute("user");
        if (null != userObj) {
            model.addAttribute("user", userObj);
        } else {
            model.addAttribute("user", new User(-1, "Dummy User", "nop@ssw0rd", "dummy", new Date()));
        }
        return "component/" + ApplicationModule.NAME + "/user-profile.jsp";
    }
}