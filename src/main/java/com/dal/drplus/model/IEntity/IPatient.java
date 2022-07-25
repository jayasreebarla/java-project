package com.dal.drplus.model.IEntity;

public abstract class IPatient {
    protected String patientName;
    protected int patientAge;
    protected String patientEmail;
    protected String patientPhoneNo;
    protected String patientPassword;
    protected String patientAddress;
    protected String patientPincode;
    protected String patientId;
    protected boolean privacyAgreementEnabled;

    public IPatient() {
    }

    public IPatient(String patientId, String patientName, int patientAge, String patientEmail, String patientPhoneNo, String patientPassword, String patientAddress, String patientPincode, boolean privacyAgreementEnabled) {
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

    abstract public String getPatientId();

    abstract public void setPatientId(String patientId);

    abstract public String getPatientName();

    abstract public void setPatientName(String patientName);

    abstract public String getPatientEmail();

    abstract public void setPatientEmail(String patientEmail);

    abstract public String getPatientPhoneNo();

    abstract public void setPatientPhoneNo(String patientPhoneNo);

    abstract public int getPatientAge();

    abstract public void setPatientAge(int patientAge);

    abstract public String getPatientPassword();

    abstract public void setPatientPassword(String patientPassword);

    abstract public String getPatientAddress();

    abstract public void setPatientAddress(String patientAddress);

    abstract public String getPatientPincode();

    abstract public void setPatientPincode(String patientPincode);

    abstract public boolean isPrivacyAgreementEnabled();

    abstract public void setPrivacyAgreementEnabled(boolean privacyAgreementEnabled);
}
