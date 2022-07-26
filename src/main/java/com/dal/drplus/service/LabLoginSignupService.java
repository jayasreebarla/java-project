package com.dal.drplus.service;

import com.dal.drplus.model.IEntity.ILab;
import com.dal.drplus.repository.interfaces.ILabRepository;

public class LabLoginSignupService {
    ILabRepository labRepository;
    public LabLoginSignupService(ILabRepository labRepository) {
        this.labRepository = labRepository;
    }

    public boolean registerLab(ILab lab){
        ILabRepository.StorageResult result = labRepository.saveLab(lab);
            if(result.equals(ILabRepository.StorageResult.SUCCESS)){
                return true;
            }else {
                return false;
            }
    }

    public boolean isLabCredentialValid(String labId,String labPassword){
        String passwordFromDB=labRepository.getLabPasswordById(labId);
        if(passwordFromDB.equals(labPassword)){
            return true;
        }else{
            return false;
        }
    }

}
