package com.dal.drplus.service;

import com.dal.drplus.model.entity.Prescription;
import com.dal.drplus.repository.interfaces.IPrescriptionRepository;

import java.io.FileNotFoundException;
import java.util.List;

public class PrescriptionService {

    private IPrescriptionRepository prescriptionRepository;

    public PrescriptionService(IPrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }
    public boolean uploadPrescription(Prescription prescription){
        try {
            IPrescriptionRepository.StorageResult result = prescriptionRepository.uploadPrescription(prescription);
            if(result.equals(IPrescriptionRepository.StorageResult.SUCCESS)){
                return true;
            }else{
                return false;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public int deletePrescription(int id){
        return prescriptionRepository.deleteById(id);
    }

    public Prescription downloadPrescription(int id){
        return prescriptionRepository.findById(id);
    }

    public List<Prescription> findAllbyAppointment(int appointmentId){
        return (List<Prescription>) prescriptionRepository.findAllbyAppointment(appointmentId);
    }
}
