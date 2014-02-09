package com.sharecare.sample.auth.spring;

import com.sharecare.sample.auth.magnolia.SpringExternalUser;
import com.sharecare.sample.model.user.User;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collections;

public class SpringAuthentication extends AbstractAuthenticationToken {

    private final SpringExternalUser user;

    public SpringAuthentication(SpringExternalUser user) {
        super(Collections.<GrantedAuthority>emptyList());
        this.user = user;
        this.setAuthenticated(true);
    }

    @Override
    public String getCredentials() {
        return user.getPassword();
    }

    @Override
    public User getPrincipal() {
        return user.getServerUser();
    }
}
