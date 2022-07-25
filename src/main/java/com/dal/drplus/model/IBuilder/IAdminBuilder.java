package com.dal.drplus.model.IBuilder;

import com.dal.drplus.model.Builder.AdminBuilder;
import com.dal.drplus.model.entity.Admin;

public interface IAdminBuilder {
    IAdminBuilder addAdminId(String adminId);
    IAdminBuilder addAdminPassword(String adminPassword);
    IAdminBuilder addAdminAccessKey(String adminAccessKey);

    public Admin build();

    public String getAdminId();
    public String getAdminPassword();
    public String getAdminAccessKey();

}
