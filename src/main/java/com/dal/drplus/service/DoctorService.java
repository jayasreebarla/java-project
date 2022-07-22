package com.dal.drplus.service;

import com.dal.drplus.model.Doctor;
import com.dal.drplus.repository.interfaces.IAppointmentRepository;
import com.dal.drplus.repository.interfaces.IDoctorRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class DoctorService {
    IDoctorRepository doctorRepository;

    public DoctorService(IDoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> listAllDoctor(){
        return doctorRepository.findAllDoctors();
    }

    public boolean deleteDoctorbyId(String doctorId){
        System.out.println("doc service");
        IDoctorRepository.StorageResult result = doctorRepository.deleteDoctorById(doctorId);
        System.out.println("doc service res "+result);
        if(result.equals(IDoctorRepository.StorageResult.SUCCESS)){
            return true;
        } else {
            return false;
        }
    }

    public List<Doctor> filterDoctorOnPincodeAndSpecialization(String doctorPincode, String doctorSpecialization){
        return doctorRepository.findAllDoctorsBySpecializationAndPincode(doctorSpecialization,doctorPincode);
    }

    public Doctor getDoctorById(String doctorId){
        Doctor doctor = doctorRepository.findDoctorById(doctorId);
        return doctor;
    }

    public List<Doctor> sortDoctorList(List<Doctor> unsortedList) {
        List<Doctor> listToSort = unsortedList;
        Collections.sort(listToSort, new Comparator<Doctor>() {
            @Override
            public int compare(Doctor o1, Doctor o2) {
                return o2.getDoctorRating()- o1.getDoctorRating();
            }
        });

       return listToSort;
    }
}

