package com.dal.drplus.repository.interfaces;
import com.dal.drplus.model.IEntity.ILab;
import com.dal.drplus.model.entity.Lab;

import java.util.List;

public interface ILabRepository {

    enum StorageResult{
        SUCCESS,
        FAILURE
    }
    StorageResult saveLab(ILab lab);
    ILab findLabById(String labId);
    List<Lab> findAllLabsByPincode(String labPincode);
    String getLabPasswordById(String labId);
    StorageResult deleteLabById(String labId);
    List<Lab> findAll();
    int deleteAll();
    StorageResult isLabIdExists(String labId);
}
