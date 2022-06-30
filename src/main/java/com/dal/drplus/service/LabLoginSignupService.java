package com.dal.drplus.service;

import com.dal.drplus.model.Lab;
import com.dal.drplus.model.Patient;
import com.dal.drplus.repository.implementation.LabRepositoryImpl;
import com.dal.drplus.repository.interfaces.ILabRepository;
import com.dal.drplus.repository.interfaces.IPatientRepository;

public class LabLoginSignupService {
    ILabRepository labRepository;
    public LabLoginSignupService(LabRepositoryImpl labRepository) {
        this.labRepository = labRepository;
    }

    public boolean registerLab(Lab lab){
        ILabRepository.StorageResult result = labRepository.saveLab(lab);
        System.out.println("result inside service"+result);
            if(result.equals(IPatientRepository.StorageResult.SUCCESS)){
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
