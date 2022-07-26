package com.dal.drplus.model.service;

import com.dal.drplus.model.IEntity.IPatient;
import com.dal.drplus.repository.interfaces.IPatientRepository;

public class PatientLoginSignupService {

    IPatientRepository patientRepository;

    public PatientLoginSignupService(IPatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public boolean registerPatient(IPatient patient, String confirmPassword){
        if(confirmPassword.equals(patient.getPatientPassword())){
            IPatientRepository.StorageResult result = patientRepository.savePatient(patient);
            if(result.equals(IPatientRepository.StorageResult.SUCCESS)){
                return true;
            }else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    public boolean isPatientCredentialValid(String patientId,String password){
        String passwordFromDB=patientRepository.getPatientPasswordById(patientId);
        if(password.equals(passwordFromDB)){
            return true;
        }else{
            return false;
        }
    }

    public boolean isPatientIdExists(String patientId){
        IPatientRepository.StorageResult result =  patientRepository.isPatientIdExists(patientId);
        if(IPatientRepository.StorageResult.SUCCESS.equals(result)){
            return true;
        } else {
            return false;
        }
    }
}
