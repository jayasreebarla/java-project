package com.dal.drplus.model;

public class Prescription {
    int prescriptionId;

    int appointmentId;
    String prescriptionDetails;

    private byte[] prescriptionFile;

    public void setPrescription(byte[] prescription) {
        this.prescriptionFile = prescription;
    }


     String fileName;

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

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getAppointmentId(){
        return appointmentId;
    }

//    public Blob getPrescription() throws FileNotFoundException
//    {
//        FileInputStream fileName;
//        fileName = new FileInputStream(this.fileName);
//        return fileName;
//    }

    public byte[] getPrescription() {
        return prescriptionFile;
    }
}
