package com.dal.drplus.model.entity;

import com.dal.drplus.model.Builder.DoctorBuilder;
import com.dal.drplus.model.Builder.PatientBuilder;
import com.dal.drplus.model.IBuilder.IPatientBuilder;
import com.dal.drplus.model.IEntity.IPatient;

public class Patient extends IPatient {
    private String patientId;
    private String patientName;
    private int patientAge;
    private String patientEmail;
    private String patientPhoneNo;
    private String patientPassword;
    private String patientAddress;
    private String patientPincode;
    private boolean privacyAgreementEnabled;

    public Patient() {
    }

    public Patient(IPatientBuilder builder){
        this.patientId = builder.getPatientId();
        this.patientName = builder.getPatientName();
        this.patientAge = builder.getPatientAge();
        this.patientEmail = builder.getPatientEmail();
        this.patientPhoneNo = builder.getPatientPhoneNo();
        this.patientPassword = builder.getPatientPassword();
        this.patientAddress = builder.getPatientAddress();
        this.patientPincode = builder.getPatientPincode();
        this.privacyAgreementEnabled = builder.isPrivacyAgreementEnabled();
    }
    public Patient(String patientId, String patientName, int patientAge, String patientEmail, String patientPhoneNo, String patientPassword, String patientAddress, String patientPincode, boolean privacyAgreementEnabled) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.patientEmail = patientEmail;
        this.patientPhoneNo = patientPhoneNo;
        this.patientPassword = patientPassword;
        this.patientAddress = patientAddress;
        this.patientPincode = patientPincode;
        this.privacyAgreementEnabled = privacyAgreementEnabled;
    }

    public String getPatientId() {
        return this.patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return this.patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientEmail() {
        return this.patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getPatientPhoneNo() {
        return this.patientPhoneNo;
    }

    public void setPatientPhoneNo(String patientPhoneNo) {
        this.patientPhoneNo = patientPhoneNo;
    }

    public int getPatientAge() {
        return this.patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientPassword() {
        return this.patientPassword;
    }

    public void setPatientPassword(String patientPassword) {
        this.patientPassword = patientPassword;
    }


    public String getPatientAddress() {
        return this.patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }

    public String getPatientPincode() {
        return this.patientPincode;
    }

    public void setPatientPincode(String patientPincode) {
        this.patientPincode = patientPincode;
    }

    public boolean isPrivacyAgreementEnabled() {
        return this.privacyAgreementEnabled;
    }

    public void setPrivacyAgreementEnabled(boolean privacyAgreementEnabled) {
        this.privacyAgreementEnabled = privacyAgreementEnabled;
    }
}

