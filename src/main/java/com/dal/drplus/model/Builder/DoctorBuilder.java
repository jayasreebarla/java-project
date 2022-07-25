package com.dal.drplus.model.Builder;

import com.dal.drplus.model.IBuilder.IDoctorBuilder;
import com.dal.drplus.model.entity.Doctor;
import com.dal.drplus.model.factory.ModelFactory;

public class DoctorBuilder implements IDoctorBuilder {

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
    
    public DoctorBuilder addDoctorId(String doctorId) {
        this.doctorId=doctorId;
        return this;
    }

    
    public DoctorBuilder addDoctorName(String doctorName) {
        this.doctorName=doctorName;
        return this;
    }

    
    public DoctorBuilder addDoctorPassword(String doctorPassword) {
        this.doctorPassword=doctorPassword;
        return this;
    }

    
    public DoctorBuilder addDoctorEmail(String doctorEmail) {
        this.doctorEmail=doctorEmail;
        return this;
    }

    
    public DoctorBuilder addDoctorPhoneNo(String doctorPhoneNo) {
        this.doctorPhoneNo=doctorPhoneNo;
        return this;
    }

    
    public DoctorBuilder addDoctorGender(String doctorGender) {
        this.doctorGender=doctorGender;
        return this;
    }

    
    public DoctorBuilder addDoctorAge(int age) {
        this.doctorAge=age;
        return this;
    }

    
    public DoctorBuilder addDoctorCredentials(String doctorCredentials) {
        this.doctorCredentials=doctorCredentials;
        return this;
    }

    
    public DoctorBuilder addDoctorSpecialization(String specialization) {
        this.doctorSpecialization=specialization;
        return this;
    }

    
    public DoctorBuilder addDoctorClinicAddress(String doctorClinicAddress) {
        this.doctorClinicAddress=doctorClinicAddress;
        return this;
    }

    
    public DoctorBuilder addDoctorPincode(String doctorPincode) {
        this.doctorPincode=doctorPincode;
        return this;
    }

    
    public DoctorBuilder addDoctorRating(int rating) {
        this.doctorRating=rating;
        return this;
    }

    
    public DoctorBuilder addDoctorFee(double fee) {
        this.doctorFee=fee;
        return this;
    }
    
    public Doctor build() {
       Doctor doctor = ModelFactory.instance().createDoctorUsingBuilder(this);
//        Doctor doctor = new Doctor(this);

        return doctor;
    }
    public String getDoctorId() {
        return doctorId;
    }
    public String getDoctorName() {
        return doctorName;
    }
    public String getDoctorPassword() {
        return doctorPassword;
    }
    public String getDoctorEmail() {
        return doctorEmail;
    }
    public String getDoctorPhoneNo() {
        return doctorPhoneNo;
    }
    public String getDoctorGender() {
        return doctorGender;
    }
    public int getDoctorAge() {
        return doctorAge;
    }
    public String getDoctorCredentials() {
        return doctorCredentials;
    }
    public String getDoctorSpecialization() {
        return doctorSpecialization;
    }
    public String getDoctorClinicAddress() {
        return doctorClinicAddress;
    }
    public String getDoctorPincode() {
        return doctorPincode;
    }
    public int getDoctorRating() {
        return doctorRating;
    }
    public double getDoctorFee() {
        return doctorFee;
    }
}
