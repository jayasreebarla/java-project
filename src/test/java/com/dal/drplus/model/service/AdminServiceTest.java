package com.dal.drplus.model.service;

import com.dal.drplus.model.IEntity.IAdmin;
import com.dal.drplus.model.entity.Admin;
import com.dal.drplus.model.service.AdminService;
import com.dal.drplus.repository.implementation.AdminRepositoryImpl;
import com.dal.drplus.repository.interfaces.IAdminRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class AdminServiceTest {

    static Admin admin = new Admin("3","3","3");

    static Admin admin1 = new Admin("5","5","5");

    private static AdminService adminService;

    private static IAdminRepository adminRepository;

    @BeforeAll
    public static void init(){
        adminRepository = Mockito.mock(AdminRepositoryImpl.class);

        Mockito.when(adminRepository.getAdminbyId(admin.getAdminId())).thenReturn(admin);
        Mockito.when(adminRepository.isAdminIdExists(admin.getAdminId())).thenReturn(IAdminRepository.StorageResult.SUCCESS);
        Mockito.when(adminRepository.isAdminIdExists(admin1.getAdminId())).thenReturn(IAdminRepository.StorageResult.FAILURE);
        adminService = new AdminService(adminRepository);
    }

    @Test
    void getAdminByIdWhenExists(){
        IAdmin admin_result = adminService.getAdminbyId(admin.getAdminId());
        assertEquals(admin,admin_result);
    }

    @Test
    void getAdminByIdWhenNotExists(){
        IAdmin admin_result = adminService.getAdminbyId(admin1.getAdminId());
        assertNull(admin_result);
    }

    @Test
    void isAdminIdExistsTrue(){
        boolean result = adminService.isAdminIdExists(admin.getAdminId());
        assertTrue(result);
    }

    @Test
    void isAdminIdExistsFalse(){
        boolean result = adminService.isAdminIdExists(admin1.getAdminId());
        assertFalse(result);
    }
}
