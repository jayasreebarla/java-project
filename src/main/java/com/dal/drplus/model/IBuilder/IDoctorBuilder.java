package com.dal.drplus.model.IBuilder;

import com.dal.drplus.model.IEntity.IDoctor;
import com.dal.drplus.model.entity.Doctor;

public interface IDoctorBuilder {
    public IDoctorBuilder addDoctorId(String doctorId);
    public IDoctorBuilder addDoctorName(String doctorName);
    public IDoctorBuilder addDoctorPassword(String doctorPassword);
    public IDoctorBuilder addDoctorEmail(String doctorEmail);
    public IDoctorBuilder addDoctorPhoneNo(String doctorPhoneNo);
    public IDoctorBuilder addDoctorGender(String doctorGender);
    public IDoctorBuilder addDoctorAge(int age);
    public IDoctorBuilder addDoctorCredentials(String doctorCredentials);
    public IDoctorBuilder addDoctorSpecialization(String specialization);
    public IDoctorBuilder addDoctorClinicAddress(String doctorClinicAddress);
    public IDoctorBuilder addDoctorPincode(String doctorPincode);
    public IDoctorBuilder addDoctorRating(int rating);
    public IDoctorBuilder addDoctorFee(double fee);
    public Doctor build();

}
