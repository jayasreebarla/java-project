package com.dal.drplus.model.Builder;

import com.dal.drplus.model.IBuilder.IPrescriptionBuilder;
import com.dal.drplus.model.entity.Prescription;
import com.dal.drplus.model.factory.ModelFactory;

public class PrescriptionBuilder implements IPrescriptionBuilder {
    private int prescriptionId;
    private int appointmentId;
    private String prescriptionDetails;
    private byte[] prescriptionFile;

    @Override
    public IPrescriptionBuilder addPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
        return this;
    }

    @Override
    public IPrescriptionBuilder addAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
        return this;
    }

    @Override
    public IPrescriptionBuilder addPrescriptionDetails(String prescriptionDetails) {
        this.prescriptionDetails = prescriptionDetails;
        return this;
    }

    @Override
    public IPrescriptionBuilder addPrescriptionFile(byte[] prescriptionFile) {
        this.prescriptionFile = prescriptionFile;
        return this;
    }

    @Override
    public Prescription build() {
        Prescription prescription = ModelFactory.instance().createPrescriptionUsingBuilder(this);
        return prescription;
    }

    @Override
    public int getPrescriptionId() {
        return prescriptionId;
    }

    @Override
    public int getAppointmentId() {
        return appointmentId;
    }

    @Override
    public String getPrescriptionDetails() {
        return prescriptionDetails;
    }

    @Override
    public byte[] getPrescriptionFile() {
        return prescriptionFile;
    }
}