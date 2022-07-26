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

    public boolean isAdminIdExists(String adminId){
        IAdminRepository.StorageResult result =  adminRepository.isAdminIdExists(adminId);
        if(IAdminRepository.StorageResult.SUCCESS.equals(result)){
            return true;
        } else {
            return false;
        }
    }

}
