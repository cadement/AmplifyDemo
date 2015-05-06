package com.sharecare.sample.model.user;

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
@RequestMapping(value = "/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserAssembler  userAssembler;

    @Autowired
    public UserController(UserRepository userRepository, UserAssembler userAssembler) {
        this.userRepository = userRepository;
        this.userAssembler = userAssembler;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getUserProfiles() {
        ModelAndView modelAndView = new ModelAndView("/users.html");
        modelAndView.addObject("title", "User Profiles");
        modelAndView.addObject("breadcrumbs", Collections.<String, String>singletonMap("/", "Home"));
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, params = "create")
    public ModelAndView getCreateUserProfile() {
        ModelAndView modelAndView = new ModelAndView("/users/create.html");
        modelAndView.addObject("title", "Create Profile");
        Map<String, String> breadcrumbs = new LinkedHashMap<String, String>();
        breadcrumbs.put("/", "Home");
        breadcrumbs.put("/users", "Users");
        modelAndView.addObject("breadcrumbs", breadcrumbs);
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createUserProfile(UserDTO userUpdates) {
        User user = userAssembler.assembleFrom(userUpdates);
        userRepository.save(user);
        return "redirect:/users/" + user.getUrl();
    }

    @RequestMapping(value = "/{url}", method = RequestMethod.GET)
    public ModelAndView getUserProfile(@PathVariable("url") String userUrl,
                                       SpringAuthentication authentication) {
        User user = userRepository.findOne(userUrl);

        ModelAndView userProfilePage;
        if (authentication.getPrincipal().getUrl().equals(user.getUrl())) {
            userProfilePage = new ModelAndView("/users/private-profile.html");
        } else {
            userProfilePage = new ModelAndView("/users/public-profile.html");
        }

        userProfilePage.addObject("user", user);

        userProfilePage.addObject("title", "Profile - " + user.getName());

        Map<String, String> breadcrumbs = new LinkedHashMap<String, String>();
        breadcrumbs.put("/", "Home");
        breadcrumbs.put("/users", "Users");
        userProfilePage.addObject("breadcrumbs", breadcrumbs);

        return userProfilePage;
    }

    @RequestMapping(value = "/{url}", params = "edit", method = RequestMethod.GET)
    public ModelAndView editUserProfile(@PathVariable("url") String userUrl) {
        ModelAndView userProfilePage = new ModelAndView("/users/edit.html");

        User user = userRepository.findOne(userUrl);

        userProfilePage.addObject("user", user);

        userProfilePage.addObject("title", "Edit Profile");

        Map<String, String> breadcrumbs = new LinkedHashMap<String, String>();
        breadcrumbs.put("/", "Home");
        breadcrumbs.put("/users", "Users");
        userProfilePage.addObject("breadcrumbs", breadcrumbs);

        return userProfilePage;
    }

    @RequestMapping(value = "/{url}", method = RequestMethod.POST)
    public String updateUserProfile(@PathVariable("url") String userUrl,
                                    UserDTO userUpdates) {
        User user = userRepository.findOne(userUrl);

        user = userAssembler.applyUpdates(user, userUpdates);
        userRepository.save(user);
        return "redirect:/users/" + userUrl;
    }

    @RequestMapping(value = "/{url}", method = RequestMethod.DELETE)
    public String deleteUserProfile(@PathVariable("url") String userUrl) {
        User user = userRepository.findOne(userUrl);
        userRepository.delete(user);
        return "redirect:/users";
    }
}
