package com.dal.drplus.model.IBuilder;

import com.dal.drplus.model.entity.Prescription;

public interface IPrescriptionBuilder {
    IPrescriptionBuilder addPrescriptionId(int prescriptionId);
    IPrescriptionBuilder addAppointmentId(int appointmentId);
    IPrescriptionBuilder addPrescriptionDetails(String prescriptionDetails);
    IPrescriptionBuilder addPrescriptionFile(byte[] prescriptionFile);

    public Prescription build();

    public int getPrescriptionId();
    public int getAppointmentId();
    public String getPrescriptionDetails();
    public byte[] getPrescriptionFile();
}