package com.dal.drplus.model;

public class Admin {
    private String adminId;
    private String adminPassword;
    private String adminAccessKey;

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

    public void login(){

    }

    public void logout(){

    }

    public void verifyAccessKey(){

    }
}
