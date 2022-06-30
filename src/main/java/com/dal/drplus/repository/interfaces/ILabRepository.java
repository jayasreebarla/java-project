package com.dal.drplus.repository.interfaces;

import com.dal.drplus.model.Lab;

import java.util.List;

public interface ILabRepository {

    enum StorageResult{
        SUCCESS,
        FAILURE
    }
    StorageResult saveLab(Lab lab);
    StorageResult updateLab(Lab lab);
    Lab findLabById(String labId);
    String getLabPasswordById(String labId);
    StorageResult deleteLabById(String labId);
    List<Lab> findAll();
    int deleteAll();
}
