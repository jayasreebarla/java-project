package com.dal.drplus.service;

import com.dal.drplus.model.Patient;
import com.dal.drplus.repository.interfaces.IPatientRepository;

public class PatientService {

    IPatientRepository patientRepository;

    public PatientService(IPatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient getPatientById(String patientId){
        Patient patient = patientRepository.findPatientById(patientId);
        return patient;
    }
}
