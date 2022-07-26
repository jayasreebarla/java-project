package com.dal.drplus.repository.implementation;

import com.dal.drplus.model.Builder.PatientBuilder;
import com.dal.drplus.model.IBuilder.IDoctorBuilder;
import com.dal.drplus.model.IBuilder.IPatientBuilder;
import com.dal.drplus.model.IEntity.IPatient;
import com.dal.drplus.model.entity.Doctor;
import com.dal.drplus.model.entity.Patient;
import com.dal.drplus.model.factory.ModelFactory;
import com.dal.drplus.repository.configuration.DatabaseConfiguration;
import com.dal.drplus.repository.configuration.DatabaseConfigurationImpl;
import com.dal.drplus.repository.interfaces.IPatientRepository;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PatientRepositoryImpl implements IPatientRepository{

//implements IPatientRepository

    String INSERT_PATIENT = "INSERT into Patient (patient_id,patient_name,patient_age,patient_email,patient_phone_no,patient_password,patient_address,patient_pincode,privacy_agreement_enabled) VALUES(?,?,?,?,?,?,?,?,?)";
    String UPDATE_PATIENT = "UPDATE Patient SET patient_name=?,patient_age=?,patient_email=?,patient_phone_no=?,patient_password=?,patient_address=?,patient_pincode=?,privacy_agreement_enabled=? WHERE patient_id=?";
    String SELECT_PATIENT_BY_ID = "SELECT * FROM Patient WHERE patient_id=?";

    String GET_PATIENT_PASSWORD_BY_ID = "SELECT patient_password FROM Patient WHERE patient_id=?";
    String DELETE_PATIENT_BY_ID = "DELETE FROM Patient WHERE patient_id=?";
    String SELECT_PATIENTS = "SELECT * from Patient";
    String DELETE_ALL = "DELETE from Patient";

    DatabaseConfiguration databaseConfiguration;

    public PatientRepositoryImpl() {
        this.databaseConfiguration = dbConfig();
    }

    private DatabaseConfiguration dbConfig(){
        return new DatabaseConfigurationImpl();
    }


    @Override
    public StorageResult savePatient(IPatient patient) {

        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(INSERT_PATIENT);
            statement.setString(1,patient.getPatientId());
            statement.setString(2,patient.getPatientName());
            statement.setInt(3,patient.getPatientAge());
            statement.setString(4,patient.getPatientEmail());
            statement.setString(5,patient.getPatientPhoneNo());
            statement.setString(6,patient.getPatientPassword());
            statement.setString(7,patient.getPatientAddress());
            statement.setString(8,patient.getPatientPincode());
            statement.setBoolean(9,patient.isPrivacyAgreementEnabled());
            int result = statement.executeUpdate();
            if(result==1){
                return StorageResult.SUCCESS;
            }
            else {
                return StorageResult.FAILURE;
            }
        } catch (SQLException e) {
//            throw new RuntimeException(e);
            return StorageResult.FAILURE;
        }
    }

    @Override
    public StorageResult updatePatient(Patient patient) {
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(UPDATE_PATIENT);
            statement.setString(1,patient.getPatientName());
            statement.setInt(2,patient.getPatientAge());
            statement.setString(3,patient.getPatientEmail());
            statement.setString(4,patient.getPatientPhoneNo());
            statement.setString(5,patient.getPatientPassword());
            statement.setString(6,patient.getPatientAddress());
            statement.setString(7,patient.getPatientPincode());
            statement.setBoolean(8, patient.isPrivacyAgreementEnabled());
            statement.setString(9,patient.getPatientId());
            statement.executeUpdate();
            return StorageResult.SUCCESS;
        } catch (SQLException e) {
//            throw new RuntimeException(e);
            return StorageResult.FAILURE;
        }
    }

    @Override
    public IPatient findPatientById(String patientId) {
        IPatient patient = null;
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(SELECT_PATIENT_BY_ID);
            statement.setString(1,patientId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                patient = createPatient(rs);
            }
            return patient;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String getPatientPasswordById(String patientId) {
        PreparedStatement statement = null;
        String password = null;
        try {
            statement = databaseConfiguration.getDBConnection().prepareStatement(GET_PATIENT_PASSWORD_BY_ID);
            statement.setString(1,patientId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
            password = rs.getString("patient_password");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return password;
    }

    @Override
    public StorageResult deletePatientById(String patientId) {
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(DELETE_PATIENT_BY_ID);
            statement.setString(1,patientId);
            int result = statement.executeUpdate();
            if(result == 1){
                return StorageResult.SUCCESS;
            }else{
                return StorageResult.FAILURE;
            }
        } catch (SQLException e) {
//            throw new RuntimeException(e);
            return StorageResult.FAILURE;
        }
    }

    @Override
    public List<Patient> findAll() {
        List<Patient> patients = new ArrayList<>();
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(SELECT_PATIENTS);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                patients.add(createPatient(rs));
            }
            return patients;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int deleteAll() {
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(DELETE_ALL);
            return statement.executeUpdate();
        } catch (SQLException e) {
            return -1;
            //throw new RuntimeException(e);
        }

    }

    private Patient createPatient(ResultSet rs) throws Exception{
        Patient patient = null;
        IPatientBuilder builder= ModelFactory.instance().createPatientBuilder();

        patient = builder
                .addPatientId(rs.getString("patient_id"))
                .addPatientName(rs.getString("patient_name"))
                .addPatientAge(rs.getInt("patient_age"))
                .addPatientEmail(rs.getString("patient_email"))
                .addPatientPhoneNo(rs.getString("patient_phone_no"))
                .addPatientPassword(rs.getString("patient_password"))
                .addPatientAddress(rs.getString("patient_address"))
                .addPatientPincode(rs.getString("patient_pincode"))
                .addPrivacyAgreementEnabled(rs.getBoolean("privacy_agreement_enabled")).build();

        //patient = ModelFactory.instance().createPatientUsingBuilder(builder);

        return patient;
    }
}
