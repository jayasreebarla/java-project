package com.dal.drplus.repository.interfaces;

import com.dal.drplus.model.Prescription;

import java.io.FileNotFoundException;

public interface IPrescriptionRepository {

    enum StorageResult{
        SUCCESS,
        FAILURE
    }

    public StorageResult savePrescription(Prescription prescription) throws FileNotFoundException;

    int updatePrescription(Prescription prescription) throws FileNotFoundException;

    public default Prescription findById(Long prescription_id){
        return null;
    }

    int deleteById(Long prescription_id);


}
