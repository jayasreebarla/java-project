package com.dal.drplus.service;

import com.dal.drplus.model.Admin;
import com.dal.drplus.repository.implementation.AdminRepositoryImpl;
import com.dal.drplus.repository.interfaces.IAdminRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdminLoginSignupServiceTest {

    static Admin admin = new Admin("3","3","3");
    static Admin admin1 = new Admin("2","2","2");

    String confirmPassword = "3";
    String confirmPassword1 = "2";

    private static AdminLoginSignupService adminLoginSignupService;
    private static IAdminRepository adminRepository;

    @BeforeAll
    public static void init(){
        adminRepository = Mockito.mock(AdminRepositoryImpl.class);

        Mockito.when(adminRepository.addAdmin(admin)).thenReturn(IAdminRepository.StorageResult.SUCCESS);
        Mockito.when(adminRepository.addAdmin(admin1)).thenReturn(IAdminRepository.StorageResult.FAILURE);
        Mockito.when(adminRepository.getAdminPasswordById("3")).thenReturn("3");
        adminLoginSignupService = new AdminLoginSignupService(adminRepository);
    }

    @Test
    void addAdminTrue(){
        boolean result = adminLoginSignupService.registerAdmin(admin,confirmPassword);
        assertTrue(result);
    }

    @Test
    void addAdminFalse(){
        boolean result = adminLoginSignupService.registerAdmin(admin1,confirmPassword1);
        assertFalse(result);
    }

    @Test
    void addAdminConfirmPasswordNotEquals(){
        boolean result = adminLoginSignupService.registerAdmin(admin1,confirmPassword);
        assertFalse(result);
    }

    @Test
    void isAdminCredsValid(){
        boolean result = adminLoginSignupService.isAdminCredentialValid(admin.getAdminId(),admin.getAdminPassword());
        assertTrue(result);
    }

    @Test
    void isAdminCredsInvalid(){
        boolean result = adminLoginSignupService.isAdminCredentialValid(admin.getAdminId(), admin1.getAdminPassword());
        assertFalse(result);
    }


}
