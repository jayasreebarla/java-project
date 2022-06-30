package com.dal.drplus.repository.interfaces;

import com.dal.drplus.model.Patient;

import java.util.List;

public interface IPatientRepository {

    enum StorageResult{
        SUCCESS,
        FAILURE
    }

    StorageResult savePatient(Patient patient);
    StorageResult updatePatient(Patient patient);
    Patient findPatientById(String patientId);

    String getPatientPasswordById(String patientId);
    StorageResult deletePatientById(String patientId);
    List<Patient> findAll();
    int deleteAll();

}