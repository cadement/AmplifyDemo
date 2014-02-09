package com.sharecare.sample.auth.spring;

import com.sharecare.sample.auth.spring.SpringSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SpringAuthorizationFilter extends AbstractAuthenticationProcessingFilter {

    private static final RequestMatcher MATCHER = new HasSessionCookieMatcher();

    @Autowired
    public SpringAuthorizationFilter(AuthenticationManager springAuthenticationManager) {
        super("/");
        setAuthenticationManager(springAuthenticationManager);

        this.setRequiresAuthenticationRequestMatcher(MATCHER);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        Cookie cookie = getSessionCookie(request);

        Authentication authentication = new UsernamePasswordAuthenticationToken(cookie.getValue(), "");

        return getAuthenticationManager().authenticate(authentication);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (!this.requiresAuthentication(request, response)) {
            chain.doFilter(request, response);
            return;
        }

        try {
            SecurityContextHolder.getContext().setAuthentication(attemptAuthentication(request, response));
        } catch (AuthenticationException e) {
            return;
        } catch (Exception ex) {
            return;
        }

        chain.doFilter(request, response);
    }

    private static Cookie getSessionCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(SpringSessionManager.SC_COOKIE_NAME)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    private static class HasSessionCookieMatcher implements RequestMatcher {
        @Override
        public boolean matches(HttpServletRequest request) {
            return null != getSessionCookie(request);
        }
    }
}
