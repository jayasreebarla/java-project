package com.dal.drplus.repository.interfaces;

import com.dal.drplus.model.Doctor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IDoctorRepository {

    enum StorageResult{
        SUCCESS,
        FAILURE
    }

    public int saveDoctor(Doctor doctor);
    public int updateDoctor(Doctor doctor);
    public Doctor findDoctorById(String id);
    public List<Doctor> findAllDoctors();
    public String getDoctorPasswordById(String doctorId);
    public List<Doctor> findAllDoctorsBySpecialization(String specialization);
    public List<Doctor> findAllDoctorsByPincode(String pincode);
    public List<Doctor> findAllDoctorsBySpecializationAndPincode(String specialization, String pincode);
    public StorageResult deleteDoctorById(String id);
    public int deleteAllDoctors();

}
