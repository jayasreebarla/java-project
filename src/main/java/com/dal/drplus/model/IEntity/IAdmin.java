package com.dal.drplus.model.IEntity;

public abstract class IAdmin {
    protected String adminId;
    protected String adminPassword;
    protected String adminAccessKey;

    public IAdmin(){

    }

    public IAdmin(String adminId, String adminPassword, String adminAccessKey) {
        this.adminId = adminId;
        this.adminPassword = adminPassword;
        this.adminAccessKey = adminAccessKey;
    }

    abstract public String getAdminId();

    abstract public void setAdminId(String adminId);

    abstract public String getAdminPassword();

    abstract public void setAdminPassword(String adminPassword);

    abstract public String getAdminAccessKey();

    abstract public void setAdminAccessKey(String adminAccessKey);
}
