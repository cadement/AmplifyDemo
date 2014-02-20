package com.sharecare.sample.auth.magnolia;

import com.sharecare.sample.auth.spring.SpringSessionManager;
import com.sharecare.sample.model.user.User;
import com.sharecare.sample.model.user.UserRepository;
import info.magnolia.jaas.sp.jcr.JCRAuthenticationModule;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.security.auth.login.AccountLockedException;
import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import java.sql.Timestamp;

@Component
public class SpringLoginModule extends JCRAuthenticationModule implements ApplicationContextAware {

    private static UserRepository       USER_REPOSITORY;
    private static SpringSessionManager SESSION_MANAGER;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        USER_REPOSITORY = applicationContext.getBean(UserRepository.class);
        SESSION_MANAGER = applicationContext.getBean(SpringSessionManager.class);
    }

    @Override
    public boolean login() throws LoginException {
        boolean success = super.login();

        if (success) {
            SESSION_MANAGER.createSession((SpringExternalUser) this.user);

            User serverUser = ((SpringExternalUser) this.user).getServerUser();
            serverUser.setLastLogin(new Timestamp(System.currentTimeMillis()));
            USER_REPOSITORY.updateUser(serverUser);
        }

        return success;
    }

    @Override
    public boolean logout() throws LoginException {
        boolean success = super.logout();

        if (success) {
            SESSION_MANAGER.destroySession();
        }

        return success;
    }

    @Override
    public void validateUser() throws LoginException {
        initUser();

        if (this.user == null) {
            throw new AccountNotFoundException(this.name + " not found.");
        }

        if (!this.user.isEnabled()) {
            throw new AccountLockedException(this.name + " is locked.");
        }

        matchPassword();
    }

    @Override
    protected void matchPassword() throws LoginException {
        String serverPassword = user.getPassword();

        if (StringUtils.isEmpty(serverPassword)) {
            throw new FailedLoginException("we do not allow users with no password");
        }

        if (!StringUtils.equals(serverPassword, new String(this.pswd))) {
            throw new FailedLoginException("passwords do not match");
        }
    }
}
