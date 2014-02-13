package com.sharecare.sample.auth.spring;

import com.sharecare.sample.auth.magnolia.SpringExternalUser;
import com.sharecare.sample.auth.spring.SpringAuthentication;
import com.sharecare.sample.model.user.User;
import info.magnolia.context.MgnlContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Component
public class SpringSessionManager {

    public static final String SC_COOKIE_NAME = "SC-Sample-Session";

    public void createSession(SpringExternalUser user) {
        SpringAuthentication authentication = new SpringAuthentication(user);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        HttpServletResponse response = MgnlContext.getWebContext().getResponse();

        Cookie authCookie = new Cookie(SC_COOKIE_NAME, user.getName());
        authCookie.setPath("/");
        authCookie.setMaxAge(1800);
        response.addCookie(authCookie);
    }

    public void destroySession() {
        SecurityContextHolder.clearContext();

        HttpServletResponse response = MgnlContext.getWebContext().getResponse();

        Cookie authCookie = new Cookie(SC_COOKIE_NAME, "");
        authCookie.setPath("/");
        authCookie.setMaxAge(0);
        response.addCookie(authCookie);
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (null != authentication && SpringAuthentication.class.isAssignableFrom(authentication.getClass())) {
            return ((SpringAuthentication) authentication).getPrincipal();
        }

        return null;
    }
}
