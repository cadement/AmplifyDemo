package com.sharecare.sample.model.user;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class UserAssembler {

    public User applyUpdates(User user, UserDTO userUpdates) {
        return new User(
                user.getUrl(),
                (null != userUpdates.getPassword()) ? userUpdates.getPassword() : user.getPassword(),
                (null != userUpdates.getName()) ? userUpdates.getName() : user.getName(),
                (null != userUpdates.getEmail()) ? userUpdates.getEmail() : user.getEmail(),
                (null != userUpdates.getAddress()) ? userUpdates.getAddress() : user.getAddress(),
                user.getAdmin(),
                new Timestamp(System.currentTimeMillis()),
                user.getLastLogin()
        );
    }

    public User assembleFrom(UserDTO userUpdates) {
        return new User(
                userUpdates.getUrl(),
                userUpdates.getPassword(),
                userUpdates.getName(),
                userUpdates.getEmail(),
                userUpdates.getAddress(),
                false,
                new Timestamp(System.currentTimeMillis()),
                null
        );
    }
}
