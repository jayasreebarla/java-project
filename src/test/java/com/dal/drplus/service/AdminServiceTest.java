package com.dal.drplus.service;

import com.dal.drplus.model.entity.Admin;
import com.dal.drplus.repository.implementation.AdminRepositoryImpl;
import com.dal.drplus.repository.interfaces.IAdminRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AdminServiceTest {

    static Admin admin = new Admin("3","3","3");

    static Admin admin1 = new Admin("5","5","5");

    private static AdminService adminService;

    private static IAdminRepository adminRepository;

    @BeforeAll
    public static void init(){
        adminRepository = Mockito.mock(AdminRepositoryImpl.class);

        Mockito.when(adminRepository.getAdminbyId(admin.getAdminId())).thenReturn(admin);
        adminService = new AdminService(adminRepository);
    }

    @Test
    void getAdminByIdWhenExists(){
        Admin admin_result = adminService.getAdminbyId(admin.getAdminId());
        assertEquals(admin,admin_result);
    }

    @Test
    void getAdminByIdWhenNotExists(){
        Admin admin_result = adminService.getAdminbyId(admin1.getAdminId());
        assertNull(admin_result);
    }


}
