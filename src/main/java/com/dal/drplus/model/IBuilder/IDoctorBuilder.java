package com.dal.drplus.model.IBuilder;

import com.dal.drplus.model.Builder.DoctorBuilder;
import com.dal.drplus.model.entity.Doctor;

public interface IDoctorBuilder {
    IDoctorBuilder addDoctorId(String doctorId);
    IDoctorBuilder addDoctorName(String doctorName);
    IDoctorBuilder addDoctorPassword(String doctorPassword);
    IDoctorBuilder addDoctorEmail(String doctorEmail);
    IDoctorBuilder addDoctorPhoneNo(String doctorPhoneNo);
    IDoctorBuilder addDoctorGender(String doctorGender);
    IDoctorBuilder addDoctorAge(int age);
    IDoctorBuilder addDoctorCredentials(String doctorCredentials);
    IDoctorBuilder addDoctorSpecialization(String specialization);
    IDoctorBuilder addDoctorClinicAddress(String doctorClinicAddress);
    IDoctorBuilder addDoctorPincode(String doctorPincode);
    IDoctorBuilder addDoctorRating(int rating);
    IDoctorBuilder addDoctorFee(double fee);
    public Doctor build();
    public String getDoctorId();
    public String getDoctorName();
    public String getDoctorPassword();
    public String getDoctorEmail();
    public String getDoctorPhoneNo();
    public String getDoctorGender();
    public int getDoctorAge();
    public String getDoctorCredentials();
    public String getDoctorSpecialization();
    public String getDoctorClinicAddress();
    public String getDoctorPincode();
    public int getDoctorRating();
    public double getDoctorFee();
}
