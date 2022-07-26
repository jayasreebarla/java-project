package com.dal.drplus.model.entity;

import com.dal.drplus.model.IBuilder.IAdminBuilder;
import com.dal.drplus.model.IEntity.IAdmin;

public class Admin extends IAdmin {
    public Admin(){
    }

    public Admin(IAdminBuilder adminBuilder){
        this.adminId = adminBuilder.getAdminId();
        this.adminPassword = adminBuilder.getAdminPassword();
        this.adminAccessKey = adminBuilder.getAdminAccessKey();
    }

    public Admin(String adminId, String adminPassword, String adminAccessKey){
        this.adminId = adminId;
        this.adminPassword = adminPassword;
        this.adminAccessKey = adminAccessKey;
    }

    public boolean validateAdminAccesskey(String adminAccessKey) {
        return adminAccessKey.matches("^[a-zA-Z0-9]{8}$");
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminAccessKey() {
        return adminAccessKey;
    }

    public void setAdminAccessKey(String adminAccessKey) {
        this.adminAccessKey = adminAccessKey;
    }
}
