package com.sharecare.sample.auth.magnolia;

import com.sharecare.sample.model.user.UserRepository;
import info.magnolia.cms.security.ExternalUserManager;
import info.magnolia.cms.security.User;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUserMananger extends ExternalUserManager implements ApplicationContextAware {

    private static UserRepository USER_REPOSITORY;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        USER_REPOSITORY = applicationContext.getBean(UserRepository.class);
    }

    @Override
    public User getUser(String name) throws UnsupportedOperationException {
        return SpringExternalUser.BUILD_FROM(USER_REPOSITORY.findOne(name));
    }
}
