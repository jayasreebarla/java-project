package com.dal.drplus.model.service;

import com.dal.drplus.model.IEntity.IDoctor;
import com.dal.drplus.repository.interfaces.IDoctorRepository;

public class DoctorLoginSignupService {

    IDoctorRepository doctorRepository;

    public DoctorLoginSignupService(IDoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Boolean isDoctorIdExists(String doctorId){
        IDoctorRepository.StorageResult result = doctorRepository.isDoctorIdExists(doctorId);

        if(result.equals(IDoctorRepository.StorageResult.SUCCESS)){
            return true;
        }else{
            return false;
        }
    }
    public boolean registerDoctor(IDoctor doctor, String confirmPassword){
        if(confirmPassword.equals(doctor.getDoctorPassword())){
            IDoctorRepository.StorageResult result = doctorRepository.saveDoctor(doctor);
            if(result == IDoctorRepository.StorageResult.SUCCESS){
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
        if(password.equals(passwordFromDB)){
            return true;
        }else{
            return false;
        }
    }

}
