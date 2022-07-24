package com.dal.drplus.service;

import com.dal.drplus.model.entity.Lab;
import com.dal.drplus.repository.interfaces.ILabRepository;

import java.util.List;

public class LabService  {
    ILabRepository labRepository;

    public LabService(ILabRepository labRepository) {
        this.labRepository = labRepository;
    }

    public List<Lab> listAllLabs(){
        return labRepository.findAll();
    }
    public Lab getLabById(String labId){
        Lab lab = labRepository.findLabById(labId);
        return lab;
    }
    public Lab findLabById(String labId){
        return labRepository.findLabById(labId);
    }

    public boolean deleteLabById(String labId){
        ILabRepository.StorageResult result = labRepository.deleteLabById(labId);
        if(result.equals(ILabRepository.StorageResult.SUCCESS)){
            return true;
        } else {
            return false;
        }
    }

    public List<Lab> filterLabOnPincode(String labPincode){
        return labRepository.findAllLabsByPincode(labPincode);
    }
}
