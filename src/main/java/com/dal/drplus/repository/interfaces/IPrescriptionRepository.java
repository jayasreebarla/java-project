package com.dal.drplus.repository.interfaces;

import com.dal.drplus.model.Prescription;

import java.io.FileNotFoundException;

public interface IPrescriptionRepository {
    int updateBill(Prescription prescription) throws FileNotFoundException;

    int deleteById(Long prescription_id);

    int deleteAll();
}
