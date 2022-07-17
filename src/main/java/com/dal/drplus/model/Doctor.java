package com.dal.drplus.model;

public class Doctor {
    private String doctorId;
    private String doctorName;
    private String doctorPassword;
    private String doctorEmail;
    private String doctorPhoneNo;
    private String doctorGender;
    private int doctorAge;
    private String doctorCredentials;
    private String doctorSpecialization;
    private String doctorClinicAddress;
    private String doctorPincode;
    private int doctorRating;
    private double doctorFee;

    public double getDoctorFee() {
        return doctorFee;
    }

    public void setDoctorFee(double doctorFee) {
        this.doctorFee = doctorFee;
    }

    public Doctor() {
    }

    public Doctor(String doctorId, String doctorName, String doctorPassword, String doctorEmail, String doctorPhoneNo, String doctorGender, int doctorAge, String doctorCredentials, String doctorSpecialization, String doctorClinicAddress, String doctorPincode, double doctorFee) {
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

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorPassword() {
        return doctorPassword;
    }

    public void setDoctorPassword(String doctorPassword) {
        this.doctorPassword = doctorPassword;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public String getDoctorPhoneNo() {
        return doctorPhoneNo;
    }

    public void setDoctorPhoneNo(String doctorPhoneNo) {
        this.doctorPhoneNo = doctorPhoneNo;
    }

    public String getDoctorGender() {
        return doctorGender;
    }

    public void setDoctorGender(String doctorGender) {
        this.doctorGender = doctorGender;
    }

    public int getDoctorAge() {
        return doctorAge;
    }

    public void setDoctorAge(int doctorAge) {
        this.doctorAge = doctorAge;
    }

    public String getDoctorCredentials() {
        return doctorCredentials;
    }

    public void setDoctorCredentials(String doctorCredentials) {
        this.doctorCredentials = doctorCredentials;
    }

    public String getDoctorSpecialization() {
        return doctorSpecialization;
    }

    public void setDoctorSpecialization(String doctorSpecialization) {
        this.doctorSpecialization = doctorSpecialization;
    }

    public String getDoctorClinicAddress() {
        return doctorClinicAddress;
    }

    public void setDoctorClinicAddress(String doctorClinicAddress) {
        this.doctorClinicAddress = doctorClinicAddress;
    }

    public String getDoctorPincode() {
        return doctorPincode;
    }

    public void setDoctorPincode(String doctorPincode) {
        this.doctorPincode = doctorPincode;
    }

    public int getDoctorRating() {
        return doctorRating;
    }

    public void setDoctorRating(int doctorRating) {
        this.doctorRating = doctorRating;
    }


}
