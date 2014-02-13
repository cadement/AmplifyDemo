package com.sharecare.sample.model;

import com.sharecare.sample.model.user.User;
import com.sharecare.sample.model.user.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;

import static com.sharecare.sample.model.ModelFixture.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/test-context.xml","/test-security.xml"})
public class UserRepositoryTest {

    @Autowired private UserRepository userRepository;

    @Test
    public void shouldUserCRUD() {
        // can create user
        userRepository.createUser(USER3);
        assertEquals(USER3, userRepository.readUser(USER3.getUrl()));

        // can read user
        assertEquals(USER1, userRepository.readUser(USER1.getUrl()));
        assertEquals(USER2, userRepository.readUser(USER2.getUrl()));

        // can update user
        User user1 = userRepository.readUser(USER1.getUrl());
        user1.setLastLogin(new Timestamp(System.currentTimeMillis()));
        userRepository.updateUser(user1);
        assertEquals(user1, userRepository.readUser(USER1.getUrl()));

        User user2 = userRepository.readUser(USER2.getUrl());
        user2.setLastLogin(new Timestamp(System.currentTimeMillis()));
        userRepository.updateUser(user2);
        assertEquals(user2, userRepository.readUser(USER2.getUrl()));

        // can delete user
        userRepository.deleteUser(USER3);
        User user3 = null;
        try {
            user3 = userRepository.readUser(USER3.getUrl());
        } catch (EmptyResultDataAccessException ignore) {
        }
        assertNull(user3);
    }
}
