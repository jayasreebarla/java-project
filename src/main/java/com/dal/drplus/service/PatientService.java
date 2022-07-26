package com.dal.drplus.service;

import com.dal.drplus.model.IEntity.IPatient;
import com.dal.drplus.model.entity.Patient;
import com.dal.drplus.repository.interfaces.IPatientRepository;

import java.util.List;

public class PatientService {

    IPatientRepository patientRepository;

    public PatientService(IPatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> listAllPatients(){
        return patientRepository.findAll();
    }

    public IPatient getPatientById(String patientId){
        IPatient patient = patientRepository.findPatientById(patientId);
        return patient;
    }

    public boolean deletePatientById(String patientId){
        IPatientRepository.StorageResult result = patientRepository.deletePatientById(patientId);
        if(result.equals(IPatientRepository.StorageResult.SUCCESS)){
            return true;
        } else {
            return false;
        }
    }
}
