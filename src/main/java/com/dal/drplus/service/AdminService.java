package com.dal.drplus.service;

import com.dal.drplus.model.IEntity.IAdmin;
import com.dal.drplus.repository.interfaces.IAdminRepository;

public class AdminService {
    IAdminRepository adminRepository;

    public AdminService(IAdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public IAdmin getAdminbyId(String adminId){
        IAdmin admin = adminRepository.getAdminbyId(adminId);
        return admin;
    }

}
