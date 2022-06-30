package com.dal.drplus.service;

import com.dal.drplus.model.Admin;
import com.dal.drplus.repository.interfaces.IAdminRepository;

public class AdminLoginSignupService {

    IAdminRepository adminRepository;

    public AdminLoginSignupService(IAdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public boolean registerAdmin(Admin admin, String confirmPassword) {
        if(confirmPassword.equals(admin.getAdminPassword())){
            IAdminRepository.StorageResult result = adminRepository.addAdmin(admin);
            if(result.equals(IAdminRepository.StorageResult.SUCCESS)){
                return true;
            }else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    public boolean isAdminCredentialValid(String adminId,String password) {
        String passwordFromDB = adminRepository.getAdminPasswordById(adminId);
        if(passwordFromDB.equals(password)){
            return true;
        }else{
            return false;
        }
    }
}
