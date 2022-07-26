package com.dal.drplus.repository.interfaces;

import com.dal.drplus.model.IEntity.IPrescription;
import com.dal.drplus.model.entity.Prescription;

import java.io.FileNotFoundException;
import java.util.List;

public interface IPrescriptionRepository {

    enum StorageResult{
        SUCCESS,
        FAILURE
    }

    public StorageResult uploadPrescription(IPrescription prescription) throws FileNotFoundException;


    public IPrescription findById(int id);

    public  List<Prescription> findAllbyAppointment(int prescription_id);


}