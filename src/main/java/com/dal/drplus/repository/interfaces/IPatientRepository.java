package com.dal.drplus.repository.interfaces;

import com.dal.drplus.model.IEntity.IPatient;
import com.dal.drplus.model.entity.Patient;

import java.util.List;

public interface IPatientRepository {

    enum StorageResult{
        SUCCESS,
        FAILURE
    }

    StorageResult savePatient(IPatient patient);
    StorageResult updatePatient(Patient patient);
    IPatient findPatientById(String patientId);

    String getPatientPasswordById(String patientId);
    StorageResult deletePatientById(String patientId);
    List<Patient> findAll();
    int deleteAll();

}
