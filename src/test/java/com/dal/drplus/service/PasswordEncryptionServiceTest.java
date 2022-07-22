package com.dal.drplus.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PasswordEncryptionServiceTest {
    String hashText = "5f4dcc3b5aa765d61d8327deb882cf99";
    String password1 = "password";
    String password2 = "abcd";

    private static PasswordEncryptionService passwordEncryptionService;

    @BeforeAll
    public static void init(){
        passwordEncryptionService = new PasswordEncryptionService();
    }

    @Test
    void hashPasswordValidResult(){
        String hashedPassword1 = passwordEncryptionService.hashPassword(password1);
        assertEquals(hashText,hashedPassword1);
    }

    @Test
    void hashPasswordInvalidResult(){
        String hashedPassword2 = passwordEncryptionService.hashPassword(password2);
        assertNotEquals(hashText,hashedPassword2);
    }

}
