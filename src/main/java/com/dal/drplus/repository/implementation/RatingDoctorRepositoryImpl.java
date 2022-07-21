package com.dal.drplus.repository.implementation;

import com.dal.drplus.model.RatingDoctor;
import com.dal.drplus.repository.configuration.DatabaseConfiguration;
import com.dal.drplus.repository.configuration.DatabaseConfigurationImpl;
import com.dal.drplus.repository.interfaces.IRatingDoctorRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RatingDoctorRepositoryImpl implements IRatingDoctorRepository {

    DatabaseConfiguration databaseConfiguration;

    private DatabaseConfiguration dbConfig(){
        return new DatabaseConfigurationImpl();
    }
    public RatingDoctorRepositoryImpl() {
        this.databaseConfiguration = dbConfig();
    }

    String INSERT_RATING_DOCTOR = "INSERT into RatingDoctor (patient_id,doctor_id,doctor_rating,review) VALUES (?,?,?,?)";
    String UPDATE_RATING_DOCTOR = "UPDATE RatingDoctor SET doctor_rating=? WHERE rating_id=? and doctor_id=?";
    String UPDATE_REVIEW_DOCTOR = "UPDATE RatingDoctor SET review=? WHERE rating_id=? and doctor_id=?";
    String SELECT_BY_RATING_AND_DOCTOR_ID = "SELECT * FROM RatingDoctor WHERE rating_id=? and doctor_id=?";
    String SELECT_BY_DOCTOR_ID = "SELECT * FROM RatingDoctor WHERE doctor_id=?";
    String SELECT_BY_DOCTOR_ID_AND_PATIENT_ID = "SELECT * FROM RatingDoctor where doctor_id = ? and patient_id = ?";
    String SELECT_BY_PATIENT_ID = "SELECT * FROM RatingDoctor WHERE patient_id=?";
    String SELECT_REVIEWS_BY_DOCTOR_ID = "SELECT review FROM RatingDoctor WHERE doctor_id=?";
    String SELECT_RATING_BY_DOCTOR_ID = "SELECT doctor_rating FROM RatingDoctor WHERE doctor_id=?";
    String DELETE_BY_DOCTOR_ID = "DELETE FROM RatingDoctor WHERE doctor_id=?";
    String DELETE_BY_PATIENT_ID = "DELETE FROM RatingDoctor WHERE patient_id=?";
    String SELECT_ALL = "SELECT * from RatingDoctor";
    String DELETE_ALL = "DELETE from RatingDoctor";

    @Override
    public StorageResult saveDoctorRating(RatingDoctor ratingDoctor) {
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(INSERT_RATING_DOCTOR);
            statement.setString(1, ratingDoctor.getPatientId());
            statement.setString(2,ratingDoctor.getDoctorId());
            statement.setInt(3,ratingDoctor.getDoctorRating());
            statement.setString(4, ratingDoctor.getReview());
            int result = statement.executeUpdate();
            if(result == 1){
                return StorageResult.SUCCESS;
            }else{
                return StorageResult.FAILURE;
            }

        } catch (SQLException e) {
//            throw new RuntimeException(e);
            return StorageResult.SUCCESS;
        }

    }

    public StorageResult updateDoctorReview(RatingDoctor ratingDoctor){
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(UPDATE_REVIEW_DOCTOR);
            statement.setString(1, ratingDoctor.getReview());
            statement.setInt(2,ratingDoctor.getRatingId());
            statement.setString(3,ratingDoctor.getDoctorId());
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
    public StorageResult updateDoctorRating(RatingDoctor ratingDoctor) {
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(UPDATE_RATING_DOCTOR);
            statement.setInt(1,ratingDoctor.getDoctorRating());
            statement.setInt(2,ratingDoctor.getRatingId());
            statement.setString(3,ratingDoctor.getDoctorId());
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
    public RatingDoctor findDoctorRatingById(int ratingId, String doctorId) {
        PreparedStatement statement = null;
        try {
            statement = databaseConfiguration.getDBConnection().prepareStatement(SELECT_BY_RATING_AND_DOCTOR_ID);
            statement.setInt(1,ratingId);
            statement.setString(2,doctorId);
            ResultSet rs = statement.executeQuery();
            RatingDoctor ratingDoctor = new RatingDoctor();
            while (rs.next()){
                ratingDoctor.setRatingId(rs.getInt("rating_id"));
                ratingDoctor.setPatientId(rs.getString("patient_id"));
                ratingDoctor.setDoctorId(rs.getString("doctor_id"));
                ratingDoctor.setDoctorRating(rs.getInt("doctor_rating"));
                ratingDoctor.setReview(rs.getString("review"));
            }
            return ratingDoctor;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean findDoctorRatingByDoctorIdAndPatientID(String doctorId, String patientId) {
        PreparedStatement statement = null;
        try {
            statement = databaseConfiguration.getDBConnection().prepareStatement(SELECT_BY_DOCTOR_ID_AND_PATIENT_ID);
            statement.setString(1,doctorId);
            statement.setString(2,patientId);
            ResultSet rs = statement.executeQuery();
            RatingDoctor ratingDoctor = new RatingDoctor();

            if(rs.next()){
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<RatingDoctor> findDoctorRatingByDoctorId(String doctorId) {
        PreparedStatement statement = null;
        List<RatingDoctor> ratingDoctorList = new ArrayList<>();
        try {
            statement = databaseConfiguration.getDBConnection().prepareStatement(SELECT_BY_DOCTOR_ID);
            statement.setString(1,doctorId);
            ResultSet rs = statement.executeQuery();
            return getRatingDoctor(ratingDoctorList, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<RatingDoctor> findDoctorRatingByPatientId(String patientId) {
        PreparedStatement statement = null;
        List<RatingDoctor> ratingDoctorList = new ArrayList<>();
        try {
            statement = databaseConfiguration.getDBConnection().prepareStatement(SELECT_BY_PATIENT_ID);
            statement.setString(1,patientId);
            ResultSet rs = statement.executeQuery();
            return getRatingDoctor(ratingDoctorList, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> findDoctorReviewsByDoctorId(String doctorId) {
        List<String> reviewList = new ArrayList<>();
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(SELECT_REVIEWS_BY_DOCTOR_ID);
            statement.setString(1,doctorId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){

                reviewList.add(rs.getString("review"));
            }
            return reviewList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Integer> findDoctorRatingListByDoctorId(String doctorId){
        List<Integer> ratingList = new ArrayList<>();

        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(SELECT_RATING_BY_DOCTOR_ID);
            statement.setString(1,doctorId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ratingList.add(rs.getInt("doctor_rating"));
            }
            return ratingList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public StorageResult deleteDoctorRatingByDoctorId(String doctorId) {
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(DELETE_BY_DOCTOR_ID);
            statement.setString(1,doctorId);
            int result  = statement.executeUpdate();
            if(result == 1) {
                return StorageResult.SUCCESS;
            } else {
                return StorageResult.FAILURE;
            }
        } catch (SQLException e) {
//            throw new RuntimeException(e);
            return StorageResult.FAILURE;
        }
    }

    @Override
    public StorageResult deleteDoctorRatingByPatientId(String patientId) {
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(DELETE_BY_PATIENT_ID);
            statement.setString(1,patientId);
            int result  = statement.executeUpdate();
            System.out.println("1 "+result);
            if(result == 1) {
                return StorageResult.SUCCESS;
            } else {
                return StorageResult.FAILURE;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
//            return StorageResult.FAILURE;
        }
    }

    @Override
    public List<RatingDoctor> findAll() {
        List<RatingDoctor> ratingDoctorList = new ArrayList<>();
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(SELECT_ALL);
            ResultSet rs = statement.executeQuery();
            return getRatingDoctor(ratingDoctorList, rs);
        } catch (SQLException e) {
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

    private List<RatingDoctor> getRatingDoctor(List<RatingDoctor> ratingDoctorList, ResultSet rs) throws SQLException {
        while (rs.next()){
            RatingDoctor ratingDoctor = new RatingDoctor();
            ratingDoctor.setRatingId(rs.getInt("rating_id"));
            ratingDoctor.setPatientId(rs.getString("patient_id"));
            ratingDoctor.setDoctorId(rs.getString("doctor_id"));
            ratingDoctor.setDoctorRating(rs.getInt("doctor_rating"));
            ratingDoctor.setReview(rs.getString("review"));
            ratingDoctorList.add(ratingDoctor);
        }
        return ratingDoctorList;
    }
}
