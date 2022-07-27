package com.dal.drplus.model.entity;

import com.dal.drplus.model.factory.ModelFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdminTest {

    private static Admin admin = (Admin) ModelFactory.instance().createAdmin();
    private static String adminAccessKey1 = "1234";
    private static String adminAccessKey2 = "1234abcd";

    @Test
    void validateAdminAccessKeyTrue(){
        boolean result = admin.validateAdminAccesskey(adminAccessKey2);
        assertTrue(result);
    }

    @Test
    void validateAdminAccessKeyFalse(){
        boolean result = admin.validateAdminAccesskey(adminAccessKey1);
        assertFalse(result);
    }
}
