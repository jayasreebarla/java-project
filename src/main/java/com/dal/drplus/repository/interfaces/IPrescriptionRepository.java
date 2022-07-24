package com.dal.drplus.repository.interfaces;

import com.dal.drplus.model.entity.Prescription;

import java.io.FileNotFoundException;
import java.util.List;

public interface IPrescriptionRepository {

    enum StorageResult{
        SUCCESS,
        FAILURE
    }

    public StorageResult uploadPrescription(Prescription prescription) throws FileNotFoundException;


    public default Prescription findById(int id){
        return null;
    }

    public default List<Prescription> findAllbyAppointment(int prescription_id) {
        return null;
    }

    int deleteById(int prescription_id);


}
