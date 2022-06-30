package com.dal.drplus.service;

import com.dal.drplus.model.Lab;
import com.dal.drplus.model.Patient;
import com.dal.drplus.repository.interfaces.ILabRepository;
import com.dal.drplus.repository.interfaces.IPatientRepository;

public class LabService  {
    ILabRepository labRepository;

    public LabService(ILabRepository labRepository) {
        this.labRepository = labRepository;
    }

    public Lab getLabById(String labId){
        Lab lab = labRepository.findLabById(labId);
        return lab;
    }
}
