package com.sharecare.sample.auth.spring;

import com.sharecare.sample.auth.magnolia.SpringExternalUser;
import com.sharecare.sample.model.user.User;
import com.sharecare.sample.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class SpringAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;

    @Autowired
    public SpringAuthenticationProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken usernameAuthenticationToken = (UsernamePasswordAuthenticationToken) authentication;
        User user = userRepository.readUser((String) usernameAuthenticationToken.getPrincipal());

        return new SpringAuthentication(SpringExternalUser.BUILD_FROM(user));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
