package com.sharecare.sample;

import com.sharecare.sample.model.UserMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final UserMaintenanceService userMaintenanceService;

    @Autowired
    public UserController(UserMaintenanceService userMaintenanceService) {
        this.userMaintenanceService = userMaintenanceService;
    }

    @RequestMapping(value = "/{url}")
    public ModelAndView getUserProfile(@PathVariable("url") String userUrl) throws SQLException {
        ModelAndView userProfilePage = new ModelAndView("/user-profile.html");
        userProfilePage.addObject("user", userMaintenanceService.getUser(userUrl));

        return userProfilePage;
    }
}
