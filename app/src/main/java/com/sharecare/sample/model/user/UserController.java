package com.sharecare.sample.model.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/{url}")
    public ModelAndView getUserProfile(@PathVariable("url") String userUrl) throws SQLException {
        ModelAndView userProfilePage = new ModelAndView("/user-profile.html");

        User user = userRepository.readUser(userUrl);

        userProfilePage.addObject("user", user);
        userProfilePage.addObject("title", "Profile - " + user.getName());

        return userProfilePage;
    }
}
