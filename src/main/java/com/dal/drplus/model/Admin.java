package com.dal.drplus.model;

public class Admin {
    private String adminId;
    private String adminPassword;
    private String adminAccessKey;

    public Admin(){

    }

    public Admin(String adminId, String adminPassword, String adminAccessKey){
        this.adminId = adminId;
        this.adminPassword = adminPassword;
        this.adminAccessKey = adminAccessKey;
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
