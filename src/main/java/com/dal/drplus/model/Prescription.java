package com.dal.drplus.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Prescription {
    String prescriptionId;

    String appointmentId;
    String prescriptionDetails;
     Byte[] Prescription;
     String fileName;

    public String getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(String prescriptionId) {
        this.prescriptionId = prescriptionId;
    }


    public String getPrescriptionDetails() {
        return prescriptionDetails;
    }

    public void setPrescriptionDetails(String prescriptionDetails) {
        this.prescriptionDetails = prescriptionDetails;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getAppointmentId(){
        return appointmentId;
    }

    public FileInputStream getPrescription() throws FileNotFoundException
    {
        FileInputStream fileName;
        fileName = new FileInputStream(this.fileName);
        return fileName;
    }
}
