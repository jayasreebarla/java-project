package com.dal.drplus.model.entity;

import com.dal.drplus.model.Builder.DoctorBuilder;
import com.dal.drplus.model.IBuilder.IDoctorBuilder;
import com.dal.drplus.model.IEntity.IDoctor;

public class Doctor extends IDoctor {
    public Doctor() {
    }
    public Doctor(IDoctorBuilder builder){
        this.doctorId = builder.getDoctorId();
        this.doctorName = builder.getDoctorName();
        this.doctorPassword = builder.getDoctorPassword();
        this.doctorEmail = builder.getDoctorEmail();
        this.doctorPhoneNo = builder.getDoctorPhoneNo();
        this.doctorGender = builder.getDoctorGender();
        this.doctorAge = builder.getDoctorAge();
        this.doctorCredentials = builder.getDoctorCredentials();
        this.doctorSpecialization = builder.getDoctorSpecialization();
        this.doctorClinicAddress = builder.getDoctorClinicAddress();
        this.doctorPincode = builder.getDoctorPincode();
        this.doctorFee = builder.getDoctorFee();
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

    public boolean validateDoctorCredentials(){
        return this.doctorCredentials.matches("^[a-zA-Z0-9-]*$");
    }

    public boolean validateDoctorName(){
        return this.doctorCredentials.matches("^[a-zA-Z0-9-]*$");
    }

    public boolean validateDoctorPincode(){
        return this.doctorCredentials.matches("^[a-zA-Z0-9-]*$");
    }

    public boolean validateDoctorEmail(){
        return this.doctorEmail.matches("^[\\w.+\\-]+@gmail\\.com$");
    }

    public boolean validatePhoneNumber(){
        return this.doctorPhoneNo.matches("^[0-9-()]*$");
    }

    public boolean validateDoctorAge(){
        return this.doctorAge>0;
    }

    public boolean validateDoctorFee(){
        return this.doctorFee>0;
    }
    @Override
    public String getDoctorId() {
        return this.doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return this.doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorPassword() {
        return this.doctorPassword;
    }

    public void setDoctorPassword(String doctorPassword) {
        this.doctorPassword = doctorPassword;
    }

    public String getDoctorEmail() {
        return this.doctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public String getDoctorPhoneNo() {
        return this.doctorPhoneNo;
    }

    public void setDoctorPhoneNo(String doctorPhoneNo) {
        this.doctorPhoneNo = doctorPhoneNo;
    }

    public String getDoctorGender() {
        return this.doctorGender;
    }

    public void setDoctorGender(String doctorGender) {
        this.doctorGender = doctorGender;
    }

    public int getDoctorAge() {
        return this.doctorAge;
    }

    public void setDoctorAge(int doctorAge) {
        this.doctorAge = doctorAge;
    }

    public String getDoctorCredentials() {
        return this.doctorCredentials;
    }

    public void setDoctorCredentials(String doctorCredentials) {
        this.doctorCredentials = doctorCredentials;
    }

    public String getDoctorSpecialization() {
        return this.doctorSpecialization;
    }

    public void setDoctorSpecialization(String doctorSpecialization) {
        this.doctorSpecialization = doctorSpecialization;
    }

    public String getDoctorClinicAddress() {
        return this.doctorClinicAddress;
    }

    public void setDoctorClinicAddress(String doctorClinicAddress) {
        this.doctorClinicAddress = doctorClinicAddress;
    }

    public String getDoctorPincode() {
        return this.doctorPincode;
    }

    public void setDoctorPincode(String doctorPincode) {
        this.doctorPincode = doctorPincode;
    }

    public int getDoctorRating() {
        return this.doctorRating;
    }

    public void setDoctorRating(int doctorRating) {
        this.doctorRating = doctorRating;
    }

    public double getDoctorFee() {
        return this.doctorFee;
    }

    public void setDoctorFee(double doctorFee) {
        this.doctorFee = doctorFee;
    }

}
