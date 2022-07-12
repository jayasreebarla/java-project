package com.dal.drplus.service;

import com.dal.drplus.model.Prescription;
import com.dal.drplus.model.Report;
import com.dal.drplus.repository.interfaces.IPrescriptionRepository;
import com.dal.drplus.repository.interfaces.IReportRepository;

import java.io.FileNotFoundException;
import java.util.List;

public class PrescriptionService {

    private IPrescriptionRepository prescriptionRepository;

    public PrescriptionService(IPrescriptionRepository reportRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }
    public boolean uploadPrescription(Prescription prescription){
        try {
            IPrescriptionRepository.StorageResult result = prescriptionRepository.uploadPrescription(prescription);
            if(result.equals(IReportRepository.StorageResult.SUCCESS)){
                return true;
            }else{
                return false;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePrescription(){

    }

    public Prescription downloadPrescription(int id){
        return prescriptionRepository.findById();
    }

    public List<Prescription> findAllbyAppointment(String appointmentId){
        return (List<Prescription>) prescriptionRepository.findAllbyAppointment(appointmentId);
    }
}
