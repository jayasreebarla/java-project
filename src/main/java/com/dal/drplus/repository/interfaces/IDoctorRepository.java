package com.dal.drplus.repository.interfaces;

import com.dal.drplus.model.IEntity.IDoctor;
import com.dal.drplus.model.entity.Doctor;

import java.util.List;

public interface IDoctorRepository {

    enum StorageResult{
        SUCCESS,
        FAILURE
    }

    public StorageResult saveDoctor(IDoctor doctor);
    public StorageResult updateDoctor(Doctor doctor);
    public Doctor findDoctorById(String id);
    public List<Doctor> findAllDoctors();
    public String getDoctorPasswordById(String doctorId);
    public List<Doctor> findAllDoctorsBySpecialization(String specialization);
    public List<Doctor> findAllDoctorsByPincode(String pincode);
    public List<Doctor> findAllDoctorsBySpecializationAndPincode(String specialization, String pincode);
    public StorageResult deleteDoctorById(String id);
    public StorageResult deleteAllDoctors();

}
