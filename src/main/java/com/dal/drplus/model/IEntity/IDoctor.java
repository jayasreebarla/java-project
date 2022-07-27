package com.dal.drplus.model.IEntity;

public abstract class IDoctor {
    protected String doctorId;
    protected String doctorName;
    protected String doctorPassword;
    protected String doctorEmail;
    protected String doctorPhoneNo;
    protected String doctorGender;
    protected int doctorAge;
    protected String doctorCredentials;
    protected String doctorSpecialization;
    protected String doctorClinicAddress;
    protected String doctorPincode;
    protected int doctorRating;
    protected double doctorFee;

    public IDoctor() {
    }

    public IDoctor(String doctorId, String doctorName, String doctorPassword, String doctorEmail, String doctorPhoneNo, String doctorGender, int doctorAge, String doctorCredentials, String doctorSpecialization, String doctorClinicAddress, String doctorPincode, double doctorFee) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.doctorPassword = doctorPassword;
        this.doctorEmail = doctorEmail;
        this.doctorPhoneNo = doctorPhoneNo;
        this.doctorGender = doctorGender;
        this.doctorAge = doctorAge;
        this.doctorCredentials = doctorCredentials;
        this.doctorSpecialization = doctorSpecialization;
        this.doctorClinicAddress = doctorClinicAddress;
        this.doctorPincode = doctorPincode;
        this.doctorFee = doctorFee;
    }

    abstract public boolean validateDoctorCredentials();

    abstract public boolean validateDoctorName();

    abstract public boolean validateDoctorPincode();

    abstract public boolean validateDoctorEmail();

    abstract public boolean validatePhoneNumber();

    abstract public boolean validateDoctorAge();

    abstract public String getDoctorId();

    abstract public void setDoctorId(String doctorId);

    abstract public String getDoctorName();

    abstract public void setDoctorName(String doctorName);

    abstract public String getDoctorPassword();

    abstract public void setDoctorPassword(String doctorPassword);

    abstract public String getDoctorEmail();

    abstract public void setDoctorEmail(String doctorEmail);

    abstract public String getDoctorPhoneNo();

    abstract public void setDoctorPhoneNo(String doctorPhoneNo);

    abstract public String getDoctorGender();

    abstract public void setDoctorGender(String doctorGender);

    abstract public int getDoctorAge();

    abstract public void setDoctorAge(int doctorAge);

    abstract public String getDoctorCredentials();

    abstract public void setDoctorCredentials(String doctorCredentials);

    abstract public String getDoctorSpecialization();

    abstract public void setDoctorSpecialization(String doctorSpecialization);

    abstract public String getDoctorClinicAddress();

    abstract public void setDoctorClinicAddress(String doctorClinicAddress);

    abstract public String getDoctorPincode();

    abstract public void setDoctorPincode(String doctorPincode);

    abstract public int getDoctorRating();

    abstract public void setDoctorRating(int doctorRating);

    abstract public double getDoctorFee();

    abstract public void setDoctorFee(double doctorFee);
}

