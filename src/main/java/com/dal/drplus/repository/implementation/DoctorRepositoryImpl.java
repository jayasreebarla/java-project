package com.dal.drplus.repository.implementation;

import com.dal.drplus.model.Builder.DoctorBuilder;
import com.dal.drplus.model.IEntity.IDoctor;
import com.dal.drplus.model.entity.Doctor;
import com.dal.drplus.repository.configuration.DatabaseConfiguration;
import com.dal.drplus.repository.configuration.DatabaseConfigurationImpl;
import com.dal.drplus.repository.interfaces.IDoctorRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DoctorRepositoryImpl implements IDoctorRepository {

    DatabaseConfiguration databaseConfiguration;
    private DatabaseConfiguration DbConfig(){
        return new DatabaseConfigurationImpl();
    }

    public DoctorRepositoryImpl() {
        this.databaseConfiguration = DbConfig();
    }

    @Override
    public StorageResult saveDoctor(IDoctor doctor) {

        PreparedStatement statement = null;
        try {
            statement = databaseConfiguration.getDBConnection().prepareStatement("INSERT into Doctor(doctor_id, doctor_name, doctor_password, doctor_email, doctor_phone, doctor_gender, doctor_age, doctor_credentials, doctor_specialization, doctor_clinic_address, doctor_pincode, doctor_fee) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
            statement.setString(1,doctor.getDoctorId());
            statement.setString(2, doctor.getDoctorName());
            statement.setString(3, doctor.getDoctorPassword());
            statement.setString(4,doctor.getDoctorEmail());
            statement.setString(5, doctor.getDoctorPhoneNo());
            statement.setString(6, doctor.getDoctorGender());
            statement.setInt(7,doctor.getDoctorAge());
            statement.setString(8, doctor.getDoctorCredentials());
            statement.setString(9, doctor.getDoctorSpecialization());
            statement.setString(10, doctor.getDoctorClinicAddress());
            statement.setString(11,doctor.getDoctorPincode());
            statement.setDouble(12,doctor.getDoctorFee());

            if(statement.executeUpdate() == 1){
                return IDoctorRepository.StorageResult.SUCCESS;
            }
            else {
                return IDoctorRepository.StorageResult.FAILURE;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public StorageResult updateDoctor(Doctor doctor) {

        PreparedStatement statement = null;
        try {
            statement = databaseConfiguration.getDBConnection().prepareStatement("UPDATE Doctor set doctor_name=?, doctor_email=?,doctor_phone=? WHERE doctor_id=?");
            statement.setString(1, doctor.getDoctorName());
            statement.setString(2,doctor.getDoctorEmail());
            statement.setString(3, doctor.getDoctorPhoneNo());
            statement.setString(4,doctor.getDoctorId());
            if(statement.executeUpdate() == 1){
                return IDoctorRepository.StorageResult.SUCCESS;
            }
            else {
                return IDoctorRepository.StorageResult.FAILURE;
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Doctor findDoctorById(String id) {
        Doctor doctorObject = new Doctor();
        PreparedStatement statement = null;
        try {
            statement = databaseConfiguration.getDBConnection().prepareStatement("Select * from Doctor where doctor_id = ?");
            statement.setString(1,id);
            ResultSet rs = statement.executeQuery();
            DoctorBuilder buider=new DoctorBuilder();
            while(rs.next()) {
                System.out.println("inside rs");

                doctorObject=buider
                .addDoctorId(rs.getString("doctor_id"))
                .addDoctorName(rs.getString("doctor_name"))
                .addDoctorPassword(rs.getString("doctor_password"))
                .addDoctorEmail(rs.getString("doctor_email"))
                .addDoctorPhoneNo(rs.getString("doctor_phone"))
                .addDoctorGender(rs.getString("doctor_gender"))
                .addDoctorAge(rs.getInt("doctor_age"))
                .addDoctorCredentials(rs.getString("doctor_credentials"))
                .addDoctorSpecialization(rs.getString("doctor_specialization"))
                .addDoctorClinicAddress(rs.getString("doctor_clinic_address"))
                .addDoctorPincode(rs.getString("doctor_pincode"))
                .addDoctorFee(rs.getDouble("doctor_fee")).build();


//                doctorObject.setDoctorId(rs.getString("doctor_id"));
//                doctorObject.setDoctorName(rs.getString("doctor_name"));
//                doctorObject.setDoctorPassword(rs.getString("doctor_password"));
//                doctorObject.setDoctorEmail(rs.getString("doctor_email"));
//                doctorObject.setDoctorPhoneNo(rs.getString("doctor_phone"));
//                doctorObject.setDoctorGender(rs.getString("doctor_gender"));
//                doctorObject.setDoctorAge(rs.getInt("doctor_age"));
//                doctorObject.setDoctorCredentials(rs.getString("doctor_credentials"));
//                doctorObject.setDoctorSpecialization(rs.getString("doctor_specialization"));
//                doctorObject.setDoctorClinicAddress(rs.getString("doctor_clinic_address"));
//                doctorObject.setDoctorPincode(rs.getString("doctor_pincode"));
//                doctorObject.setDoctorFee(rs.getDouble("doctor_fee"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return doctorObject;
    }

    @Override
    public List<Doctor> findAllDoctors() {
        List<Doctor> doctors;
        try {
            doctors = new ArrayList<>();
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement("Select * from Doctor");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Doctor doctorObject = new Doctor();
                doctorObject.setDoctorId(rs.getString("doctor_id"));
                doctorObject.setDoctorName(rs.getString("doctor_name"));
                doctorObject.setDoctorPassword(rs.getString("doctor_password"));
                doctorObject.setDoctorEmail(rs.getString("doctor_email"));
                doctorObject.setDoctorPhoneNo(rs.getString("doctor_phone"));
                doctorObject.setDoctorGender(rs.getString("doctor_gender"));
                doctorObject.setDoctorAge(rs.getInt("doctor_age"));
                doctorObject.setDoctorCredentials(rs.getString("doctor_credentials"));
                doctorObject.setDoctorSpecialization(rs.getString("doctor_specialization"));
                doctorObject.setDoctorClinicAddress(rs.getString("doctor_clinic_address"));
                doctorObject.setDoctorPincode(rs.getString("doctor_pincode"));
                doctorObject.setDoctorFee(rs.getDouble("doctor_fee"));

                doctors.add(doctorObject);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return doctors;
    }

    @Override
    public String getDoctorPasswordById(String doctorId) {

        PreparedStatement statement = null;
        String password = null;
        try {
            statement = databaseConfiguration.getDBConnection().prepareStatement("SELECT doctor_password FROM Doctor WHERE doctor_id=?");
            statement.setString(1,doctorId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                password = rs.getString("doctor_password");
                System.out.println("password"+password);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return password;
    }

    @Override
    public List<Doctor> findAllDoctorsBySpecialization(String specialization) {
        List<Doctor> doctorsBySpecialization = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = databaseConfiguration.getDBConnection().prepareStatement("Select * from Doctor  where doctor_specialization = ?");
            statement.setString(1,specialization);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Doctor doctorObject = new Doctor();
                doctorObject.setDoctorId(rs.getString("doctor_id"));
                doctorObject.setDoctorName(rs.getString("doctor_name"));
                doctorObject.setDoctorEmail(rs.getString("doctor_email"));
                doctorObject.setDoctorPhoneNo(rs.getString("doctor_phone"));
                doctorObject.setDoctorGender(rs.getString("doctor_gender"));
                doctorObject.setDoctorAge(rs.getInt("doctor_age"));
                doctorObject.setDoctorCredentials(rs.getString("doctor_credentials"));
                doctorObject.setDoctorSpecialization(rs.getString("doctor_specialization"));
                doctorObject.setDoctorClinicAddress(rs.getString("doctor_clinic_address"));
                doctorObject.setDoctorPincode(rs.getString("doctor_pincode"));
                doctorObject.setDoctorFee(rs.getDouble("doctor_fee"));

                doctorsBySpecialization.add(doctorObject);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return doctorsBySpecialization;
    }

    @Override
    public List<Doctor> findAllDoctorsByPincode(String pincode) {
        List<Doctor> doctorsByPincode = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = databaseConfiguration.getDBConnection().prepareStatement("Select * from Doctor where doctor_pincode = ?");
            statement.setString(1,pincode);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Doctor doctorObject = new Doctor();
                doctorObject.setDoctorId(rs.getString("doctor_id"));
                doctorObject.setDoctorName(rs.getString("doctor_name"));
                doctorObject.setDoctorEmail(rs.getString("doctor_email"));
                doctorObject.setDoctorPhoneNo(rs.getString("doctor_phone"));
                doctorObject.setDoctorGender(rs.getString("doctor_gender"));
                doctorObject.setDoctorAge(rs.getInt("doctor_age"));
                doctorObject.setDoctorCredentials(rs.getString("doctor_credentials"));
                doctorObject.setDoctorSpecialization(rs.getString("doctor_specialization"));
                doctorObject.setDoctorClinicAddress(rs.getString("doctor_clinic_address"));
                doctorObject.setDoctorPincode(rs.getString("doctor_pincode"));
                doctorObject.setDoctorFee(rs.getDouble("doctor_fee"));

                doctorsByPincode.add(doctorObject);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return doctorsByPincode;
    }

    @Override
    public List<Doctor> findAllDoctorsBySpecializationAndPincode(String specialization, String pincode) {
        List<Doctor> doctorsBySpecializationAndPincode = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = databaseConfiguration.getDBConnection().prepareStatement("Select * from Doctor where doctor_specialization = ? and doctor_pincode =?");
            statement.setString(1,specialization);
            statement.setString(2,pincode);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Doctor doctorObject = new Doctor();
                doctorObject.setDoctorId(rs.getString("doctor_id"));
                doctorObject.setDoctorName(rs.getString("doctor_name"));
                doctorObject.setDoctorEmail(rs.getString("doctor_email"));
                doctorObject.setDoctorPhoneNo(rs.getString("doctor_phone"));
                doctorObject.setDoctorGender(rs.getString("doctor_gender"));
                doctorObject.setDoctorAge(rs.getInt("doctor_age"));
                doctorObject.setDoctorCredentials(rs.getString("doctor_credentials"));
                doctorObject.setDoctorSpecialization(rs.getString("doctor_specialization"));
                doctorObject.setDoctorClinicAddress(rs.getString("doctor_clinic_address"));
                doctorObject.setDoctorPincode(rs.getString("doctor_pincode"));
                doctorObject.setDoctorFee(rs.getDouble("doctor_fee"));

                doctorsBySpecializationAndPincode.add(doctorObject);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return doctorsBySpecializationAndPincode;
    }

    @Override
    public StorageResult deleteDoctorById(String id) {
        PreparedStatement statement = null;
        int returnValue;
        try {
            statement = databaseConfiguration.getDBConnection().prepareStatement("Delete from Doctor where doctor_id = ?");
            statement.setString(1,id);
            int result = statement.executeUpdate();
            System.out.println("result inside repo"+result);
            if(result==1){
            return IDoctorRepository.StorageResult.SUCCESS;
            }else {
                return IDoctorRepository.StorageResult.FAILURE;
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            return IDoctorRepository.StorageResult.FAILURE;
        }
    }

    @Override
    public StorageResult deleteAllDoctors() {
        PreparedStatement statement = null;
        int returnValue;
        try {
            statement = databaseConfiguration.getDBConnection().prepareStatement("Delete from Doctor");
            if(statement.executeUpdate() == 1){
                return IDoctorRepository.StorageResult.SUCCESS;
            }
            else {
                return IDoctorRepository.StorageResult.FAILURE;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
