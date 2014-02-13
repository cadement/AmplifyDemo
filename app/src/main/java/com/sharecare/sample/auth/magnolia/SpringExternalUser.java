package com.sharecare.sample.auth.magnolia;

import com.sharecare.sample.model.user.User;
import info.magnolia.cms.security.ExternalUser;
import info.magnolia.cms.security.auth.Entity;
import info.magnolia.cms.security.auth.GroupList;
import info.magnolia.cms.security.auth.RoleList;
import info.magnolia.jaas.principal.GroupListImpl;
import info.magnolia.jaas.principal.RoleListImpl;

import java.util.HashMap;
import java.util.Map;

public class SpringExternalUser extends ExternalUser {

    public static SpringExternalUser BUILD_FROM(User serverUser) {
        Map<String, String> properties = new HashMap<String, String>();
        properties.put(Entity.FULL_NAME, serverUser.getName());
        properties.put(Entity.NAME, serverUser.getUrl());
        properties.put(Entity.EMAIL, serverUser.getEmail());
        properties.put(Entity.LANGUAGE, "en");
        properties.put(Entity.LOCALE, "US");
        properties.put(Entity.ADDRESS_LINE, serverUser.getAddress());
        properties.put(Entity.PASSWORD, serverUser.getPassword());

        RoleList roleList = new RoleListImpl();
        roleList.add("superuser");

        GroupList groupList = new GroupListImpl();

        return new SpringExternalUser(serverUser, properties, groupList, roleList);
    }

    private final User serverUser;

    private SpringExternalUser(User serverUser, Map<String, String> properties, GroupList groupList, RoleList roleList) {
        super(properties, groupList, roleList);

        this.serverUser = serverUser;
    }

    public User getServerUser() {
        return serverUser;
    }
}
