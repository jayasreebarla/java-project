package com.dal.drplus.model.IEntity;

public abstract class IPrescription {
    protected int prescriptionId;
    protected int appointmentId;
    protected String prescriptionDetails;
    protected byte[] prescriptionFile;

    public IPrescription(int prescriptionId, int appointmentId, String prescriptionDetails, byte[] prescriptionFile) {
        this.prescriptionId = prescriptionId;
        this.appointmentId = appointmentId;
        this.prescriptionDetails = prescriptionDetails;
        this.prescriptionFile = prescriptionFile;
    }

    public IPrescription() {
    }

    abstract public int getPrescriptionId();
    abstract public void setPrescriptionId(int prescriptionId);
    abstract public int getAppointmentId();
    abstract public void setAppointmentId(int appointmentId);
    abstract public String getPrescriptionDetails();
    abstract public void setPrescriptionDetails(String prescriptionDetails);
    abstract public byte[] getPrescriptionFile();
    abstract public void setPrescriptionFile(byte[] prescriptionFile);
}