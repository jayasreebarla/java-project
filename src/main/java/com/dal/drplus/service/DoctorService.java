package com.dal.drplus.service;

import com.dal.drplus.model.Doctor;
import com.dal.drplus.repository.interfaces.IDoctorRepository;

import java.util.List;

public class DoctorService {
    IDoctorRepository doctorRepository;

    public DoctorService(IDoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }


    public void sortDoctorOnRating(){

    }

    public void filterDoctorOnLocation(){

    }

    public List<Doctor> filterDoctorOnPincodeAndSpecialization(String doctorPincode, String doctorSpecialization){
        return doctorRepository.findAllDoctorsBySpecializationAndPincode(doctorSpecialization,doctorPincode);
    }

    public Doctor getDoctorById(String doctorId){
        Doctor doctor = doctorRepository.findDoctorById(doctorId);
        return doctor;
    }
}
