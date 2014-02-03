package com.sharecare.sample.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class UserMaintenanceServiceTest {

    @Autowired private UserMaintenanceService userMaintenanceService;

    private Connection connection;

    @Test
    public void shouldRetrieveUser() throws SQLException {
        assertEquals("Casey Dement", userMaintenanceService.getUser("casey").getName());
        assertEquals("Nick Hess", userMaintenanceService.getUser("nick").getName());
    }
}
