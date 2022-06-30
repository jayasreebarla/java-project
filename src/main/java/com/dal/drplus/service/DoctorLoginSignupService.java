package com.dal.drplus.service;

import com.dal.drplus.model.Doctor;
import com.dal.drplus.repository.interfaces.IDoctorRepository;

public class DoctorLoginSignupService {

    IDoctorRepository doctorRepository;

    public DoctorLoginSignupService(IDoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public boolean registerDoctor(Doctor doctor, String confirmPassword){
        if(confirmPassword.equals(doctor.getDoctorPassword())){
            int result = doctorRepository.saveDoctor(doctor);
            if(result == 1){
                return true;
            }else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    public boolean isDoctorCredentialValid(String doctorId,String password){
        String passwordFromDB=doctorRepository.getDoctorPasswordById(doctorId);
        if(passwordFromDB.equals(password)){
            return true;
        }else{
            return false;
        }
    }

}
