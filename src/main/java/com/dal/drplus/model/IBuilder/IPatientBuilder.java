package com.dal.drplus.model.IBuilder;

import com.dal.drplus.model.entity.Patient;

public interface IPatientBuilder {

    IPatientBuilder addPatientId(String patientId);
    IPatientBuilder addPatientName(String patientName);
    IPatientBuilder addPatientEmail(String patientEmail);

    IPatientBuilder addPatientPhoneNo(String patientPhoneNo);

    IPatientBuilder addPatientAge(int patientAge);

    IPatientBuilder addPatientPassword(String patientPassword);

    IPatientBuilder addPatientAddress(String patientAddress);

    IPatientBuilder addPatientPincode(String patientPincode);

    IPatientBuilder addPrivacyAgreementEnabled(boolean privacyAgreementEnabled);

    public Patient build();

    public String getPatientId();

    public String getPatientName();

    public String getPatientEmail();

    public String getPatientPhoneNo();

    public int getPatientAge();

    public String getPatientPassword();

    public String getPatientAddress();

    public String getPatientPincode();

    public boolean isPrivacyAgreementEnabled();
}
