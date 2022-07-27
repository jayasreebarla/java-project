package com.dal.drplus.model.IBuilder;

import com.dal.drplus.model.entity.Admin;

public interface IAdminBuilder {
    IAdminBuilder addAdminId(String adminId);
    IAdminBuilder addAdminPassword(String adminPassword);
    IAdminBuilder addAdminAccessKey(String adminAccessKey);

    Admin build();

    String getAdminId();
    String getAdminPassword();
    String getAdminAccessKey();

}
