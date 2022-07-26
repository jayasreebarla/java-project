package com.dal.drplus.model.entity;

import com.dal.drplus.model.IBuilder.IPrescriptionBuilder;
import com.dal.drplus.model.IEntity.IPrescription;

public class Prescription extends IPrescription {
    public Prescription(){

    }

    public Prescription(IPrescriptionBuilder prescriptionBuilder) {
        this.prescriptionId = prescriptionBuilder.getPrescriptionId();
        this.appointmentId = prescriptionBuilder.getAppointmentId();
        this.prescriptionDetails = prescriptionBuilder.getPrescriptionDetails();
        this.prescriptionFile = prescriptionBuilder.getPrescriptionFile();
    }

    public Prescription(int prescriptionId, int appointmentId, String prescriptionDetails, byte[] prescriptionFile) {
        this.prescriptionId = prescriptionId;
        this.appointmentId = appointmentId;
        this.prescriptionDetails = prescriptionDetails;
        this.prescriptionFile = prescriptionFile;
    }

    public boolean validatePrescriptionDetails(String reportDetails) {
        return reportDetails.endsWith(".pdf");
    }

    public void setPrescription(byte[] prescription) {
        this.prescriptionFile = prescription;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }


    public String getPrescriptionDetails() {
        return prescriptionDetails;
    }

    public void setPrescriptionDetails(String prescriptionDetails) {
        this.prescriptionDetails = prescriptionDetails;
    }

    public byte[] getPrescriptionFile() {
        return prescriptionFile;
    }

    public void setPrescriptionFile(byte[] prescriptionFile) {
        this.prescriptionFile = prescriptionFile;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getAppointmentId(){
        return appointmentId;
    }

    public byte[] getPrescription() {
        return prescriptionFile;
    }
}