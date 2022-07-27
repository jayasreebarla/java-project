package com.dal.drplus.model.service;

import com.dal.drplus.model.IEntity.ILab;
import com.dal.drplus.model.entity.Lab;
import com.dal.drplus.repository.interfaces.ILabRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LabService  {
    ILabRepository labRepository;

    public LabService(ILabRepository labRepository) {
        this.labRepository = labRepository;
    }

    public List<Lab> listAllLabs(){
        return labRepository.findAll();
    }
    public ILab getLabById(String labId){
        ILab lab = labRepository.findLabById(labId);
        return lab;
    }
    public ILab findLabById(String labId){
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

    public boolean isLabIdExists(String labId){
        ILabRepository.StorageResult result = labRepository.isLabIdExists(labId);
        if(result.equals(ILabRepository.StorageResult.SUCCESS)){
            return true;
        } else {
            return false;
        }
    }

    public List<Lab> filterLabOnPincode(String labPincode){
        return labRepository.findAllLabsByPincode(labPincode);
    }

    public List<Lab> sortLabList(List<Lab> unsortedList){
        List<Lab> listToSort = unsortedList;
        Collections.sort(listToSort, new Comparator<Lab>() {
            @Override
            public int compare(Lab o1, Lab o2) {
                return o2.getLabRating()- o1.getLabRating();
            }
        });

        return listToSort;
    }

}
