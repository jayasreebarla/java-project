package com.dal.drplus.repository.interfaces;

import com.dal.drplus.model.IEntity.IAdmin;

public interface IAdminRepository {
    StorageResult addAdmin(IAdmin admin);
    public IAdmin getAdminbyId(String adminId);
    String getAdminPasswordById(String adminId);

    StorageResult isAdminIdExists(String adminId);

    enum StorageResult{
        SUCCESS,
        FAILURE
    }
}
