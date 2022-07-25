package com.dal.drplus.repository.implementation;

import com.dal.drplus.model.Builder.DoctorBuilder;
import com.dal.drplus.model.IBuilder.IDoctorBuilder;
import com.dal.drplus.model.IEntity.IDoctor;
import com.dal.drplus.model.entity.Doctor;
import com.dal.drplus.model.factory.ModelFactory;
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
    public IDoctor findDoctorById(String id) {
        IDoctor doctorObject = null;
        PreparedStatement statement = null;
        try {
            statement = databaseConfiguration.getDBConnection().prepareStatement("Select * from Doctor where doctor_id = ?");
            statement.setString(1,id);
            ResultSet rs = statement.executeQuery();
//            IDoctorBuilder builder= ModelFactory.instance().createDoctorBuilder();
            while(rs.next()) {
                System.out.println("inside rs");
                doctorObject = createDoctor(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
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
                Doctor doctorObject = createDoctor(rs);
                doctors.add(doctorObject);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
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
    public List<Doctor> findAllDoctorsBySpecializationAndPincode(String specialization, String pincode) {
        List<Doctor> doctorsBySpecializationAndPincode = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = databaseConfiguration.getDBConnection().prepareStatement("Select * from Doctor where doctor_specialization = ? and doctor_pincode =?");
            statement.setString(1,specialization);
            statement.setString(2,pincode);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Doctor doctorObject = createDoctor(rs);

                doctorsBySpecializationAndPincode.add(doctorObject);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
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

    private Doctor createDoctor(ResultSet rs) throws Exception{
        Doctor doctorObject = null;
        IDoctorBuilder builder= ModelFactory.instance().createDoctorBuilder();

                builder
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
        doctorObject = ModelFactory.instance().createDoctorUsingBuilder(builder);

        return doctorObject;
    }
}
