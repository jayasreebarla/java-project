package com.dal.drplus.service;

import com.dal.drplus.model.Admin;
import com.dal.drplus.repository.interfaces.IAdminRepository;

public class AdminService {
    IAdminRepository adminRepository;

    public AdminService(IAdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin getAdminbyId(String adminId){
        Admin admin = adminRepository.getAdminbyId(adminId);
        return admin;
    }

}
