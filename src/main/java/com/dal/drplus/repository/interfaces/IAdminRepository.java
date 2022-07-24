package com.dal.drplus.repository.interfaces;

import com.dal.drplus.model.entity.Admin;

import java.sql.SQLException;

public interface IAdminRepository {
    StorageResult addAdmin(Admin admin);
    public int deleteAdmin(String adminId) throws SQLException;
    StorageResult updateAdmin(Admin admin) throws SQLException;
    public Admin getAdminbyId(String adminId);

    String getAdminPasswordById(String adminId);

    enum StorageResult{
        SUCCESS,
        FAILURE
    }
}
