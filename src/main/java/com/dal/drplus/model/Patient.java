package com.dal.drplus.model;

public class Patient {
    private String patientId;
    private String patientName;
    private int patientAge;
    private String patientEmail;
    private String patientPhoneNo;
    private String patientPassword;
    private String patientAddress;
    private Double patientPincode;
    private boolean privacyAgreementEnabled;

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getPatientPhoneNo() {
        return patientPhoneNo;
    }

    public void setPatientPhoneNo(String patientPhoneNo) {
        this.patientPhoneNo = patientPhoneNo;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientPassword() {
        return patientPassword;
    }

    public void setPatientPassword(String patientPassword) {
        this.patientPassword = patientPassword;
    }


    public String getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }

    public Double getPatientPincode() {
        return patientPincode;
    }

    public void setPatientPincode(Double patientPincode) {
        this.patientPincode = patientPincode;
    }

    public boolean isPrivacyAgreementEnabled() {
        return privacyAgreementEnabled;
    }

    public void setPrivacyAgreementEnabled(boolean privacyAgreementEnabled) {
        this.privacyAgreementEnabled = privacyAgreementEnabled;
    }
}

