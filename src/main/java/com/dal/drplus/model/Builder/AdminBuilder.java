package com.dal.drplus.model.Builder;

import com.dal.drplus.model.IBuilder.IAdminBuilder;
import com.dal.drplus.model.entity.Admin;
import com.dal.drplus.model.factory.ModelFactory;

public class AdminBuilder implements IAdminBuilder {
    private String adminId;
    private String adminPassword;
    private String adminAccessKey;

    @Override
    public AdminBuilder addAdminId(String adminId) {
        this.adminId = adminId;
        return this;
    }

    @Override
    public AdminBuilder addAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
        return this;
    }

    @Override
    public AdminBuilder addAdminAccessKey(String adminAccessKey) {
        this.adminAccessKey = adminAccessKey;
        return this;
    }

    @Override
    public Admin build() {
        Admin admin = ModelFactory.instance().createAdminUsingBuilder(this);
        return admin;
    }

    @Override
    public String getAdminId() {
        return adminId;
    }

    @Override
    public String getAdminPassword() {
        return adminPassword;
    }

    @Override
    public String getAdminAccessKey() {
        return adminAccessKey;
    }
}
