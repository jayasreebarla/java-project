package com.dal.drplus.repository.implementation;

import com.dal.drplus.model.Doctor;
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
    public int saveDoctor(Doctor doctor) {

        PreparedStatement statement = null;
        try {
            statement = databaseConfiguration.getDBConnection().prepareStatement("INSERT into Doctor(doctor_id, doctor_name, doctor_password, doctor_email, doctor_phone, doctor_gender, doctor_age, doctor_credentials, doctor_specialization, doctor_clinic_address, doctor_pincode) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
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
            return statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateDoctor(Doctor doctor) {

        PreparedStatement statement = null;
        try {
            statement = databaseConfiguration.getDBConnection().prepareStatement("UPDATE Doctor set doctor_name=?, doctor_email=?,doctor_phone=? WHERE doctor_id=?");
            statement.setString(1, doctor.getDoctorName());
            statement.setString(2,doctor.getDoctorEmail());
            statement.setString(3, doctor.getDoctorPhoneNo());
            statement.setString(4,doctor.getDoctorId());
            return statement.executeUpdate();

        } catch (SQLException e) {
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
            while(rs.next()) {
                System.out.println("inside rs");
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
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return doctorObject;
    }

    @Override
    public List<Doctor> findAllDoctors(Connection connection) throws SQLException {

        List<Doctor> doctors = new ArrayList<>();
        PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement("Select * from Doctor");
        ResultSet rs = statement.executeQuery();
        while (rs.next()){
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
           doctors.add(doctorObject);
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
                doctorsBySpecializationAndPincode.add(doctorObject);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return doctorsBySpecializationAndPincode;
    }

    @Override
    public int deleteDoctorById(String id) {
        PreparedStatement statement = null;
        int returnValue;
        try {
            statement = databaseConfiguration.getDBConnection().prepareStatement("Delete from Doctor where doctor_id = ?");
            statement.setString(1,id);
            return statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteAllDoctors() {
        PreparedStatement statement = null;
        int returnValue;
        try {
            statement = databaseConfiguration.getDBConnection().prepareStatement("Delete from Doctor");
            return statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


//`doctor_id`, `doctor_name`, `doctor_password`, `doctor_email`, `doctor_phone`,
// `doctor_gender`, `doctor_age`, `doctor_credentials`, `doctor_specialization`,
// `doctor_clinic_address`, `doctor_pincode`

/*


    String GET_PATIENT_PASSWORD_BY_ID = "SELECT patient_password FROM Patient WHERE patient_id=?";

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




    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int saveDoctor(Doctor doctor){
        return jdbcTemplate.update("INSERT into doctor(doctor_id, doctor_name, doctor_password, doctor_email, doctor_phone, doctor_gender, doctor_age, doctor_credentials, doctor_specialization, doctor_clinic_address, doctor_pincode) VALUES (?,?,?,?,?,?,?,?,?,?,?)",
                new Object[] { doctor.getDoctorId(), doctor.getDoctorName(), doctor.getDoctorPassword(), doctor.getDoctorEmailId(),
                        doctor.getDoctorPhoneNumber(), doctor.getDoctorGender(), doctor.getDoctorAge(), doctor.getDoctorCredentials(),
                        doctor.getDoctorSpecialization(), doctor.getDoctorClinicAddress(), doctor.getDoctorPincode()});
    }

    @Override
    public int updateDoctor(Doctor doctor) {
        return jdbcTemplate.update("UPDATE doctor set doctor_name=?, doctor_email=?,doctor_phone=? WHERE doctor_id=?",
                new Object[] {doctor.getDoctorName(), doctor.getDoctorEmailId(), doctor.getDoctorPhoneNumber()});
    }

    @Override
    public Doctor findDoctorById(String id) {
        try{
            Doctor doctor = jdbcTemplate.queryForObject("SELECT * from doctor where id = ?",
                    BeanPropertyRowMapper.newInstance(Doctor.class),id);
            return doctor;
        }catch(IncorrectResultSizeDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Doctor> findAllDoctors() {
        return jdbcTemplate.query("SELECT * from doctor", BeanPropertyRowMapper.newInstance(Doctor.class));
    }

    @Override
    public List<Doctor> findAllDoctorsBySpecialization(String specialization) {
        return (List<Doctor>) jdbcTemplate.queryForObject("SELECT * from doctor where doctor_specialization = ?",
                BeanPropertyRowMapper.newInstance(Doctor.class),specialization);
    }

    @Override
    public List<Doctor> findAllDoctorsByPincode(String pincode) {
        return (List<Doctor>) jdbcTemplate.queryForObject("SELECT * from doctor where doctor_pincode = ?",
                BeanPropertyRowMapper.newInstance(Doctor.class),pincode);
    }

    @Override
    public List<Doctor> findAllDoctorsBySpecializationAndPincode(String specialization, String pincode) {
        return (List<Doctor>) jdbcTemplate.queryForObject("SELECT * from specialization where doctor_specialization = ? and doctor_pincode = ?",
                BeanPropertyRowMapper.newInstance(Doctor.class), specialization, pincode);
    }

    @Override
    public int deleteDoctorById(String id) {
        return jdbcTemplate.update("DELETE from doctor where doctor_id = ?",id);
    }

    @Override
    public int deleteAllDoctors() {
        return jdbcTemplate.update("DELETE from doctor");
    }*/



