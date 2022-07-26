package com.dal.drplus.repository.interfaces;

import com.dal.drplus.model.IEntity.IAdmin;
import com.dal.drplus.model.entity.Admin;

import java.sql.SQLException;

public interface IAdminRepository {
    StorageResult addAdmin(IAdmin admin);
    public IAdmin getAdminbyId(String adminId);
    String getAdminPasswordById(String adminId);

    enum StorageResult{
        SUCCESS,
        FAILURE
    }
}
