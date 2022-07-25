package com.dal.drplus.model.Builder;

import com.dal.drplus.model.entity.Doctor;
import com.dal.drplus.model.entity.Patient;
import com.dal.drplus.model.factory.ModelFactory;

public class PatientBuilder {
    private String patientId;
    private String patientName;
    private int patientAge;
    private String patientEmail;
    private String patientPhoneNo;
    private String patientPassword;
    private String patientAddress;
    private String patientPincode;
    private boolean privacyAgreementEnabled;

    public PatientBuilder addPatientId(String patientId) {
        this.patientId = patientId;
        return this;
    }
    public PatientBuilder addPatientName(String patientName) {
        this.patientName = patientName;
        return this;
    }
    public PatientBuilder addPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
        return this;
    }

    public PatientBuilder addPatientPhoneNo(String patientPhoneNo) {
        this.patientPhoneNo = patientPhoneNo;
        return this;
    }

    public PatientBuilder addPatientAge(int patientAge) {
        this.patientAge = patientAge;
        return this;
    }


    public PatientBuilder addPatientPassword(String patientPassword) {
        this.patientPassword = patientPassword;
        return this;
    }

    public PatientBuilder addPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
        return this;
    }


    public PatientBuilder addPatientPincode(String patientPincode) {
        this.patientPincode = patientPincode;
        return this;
    }

    public PatientBuilder addPrivacyAgreementEnabled(boolean privacyAgreementEnabled) {
        this.privacyAgreementEnabled = privacyAgreementEnabled;
        return this;
    }

    public Patient build() {
        Patient patient = (Patient) ModelFactory.instance().createPatientBuilder(this);
        return patient;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }


    public String getPatientEmail() {
        return patientEmail;
    }


    public String getPatientPhoneNo() {
        return patientPhoneNo;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public String getPatientPassword() {
        return patientPassword;
    }


    public String getPatientAddress() {
        return patientAddress;
    }

    public String getPatientPincode() {
        return patientPincode;
    }

    public boolean isPrivacyAgreementEnabled() {
        return privacyAgreementEnabled;
    }

}
