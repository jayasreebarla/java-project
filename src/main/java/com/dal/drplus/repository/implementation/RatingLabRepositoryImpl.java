package com.dal.drplus.repository.implementation;

import com.dal.drplus.model.Builder.RatingLabBuilder;
import com.dal.drplus.model.IBuilder.IRatingLabBuilder;
import com.dal.drplus.model.IEntity.IRatingLab;
import com.dal.drplus.model.entity.RatingLab;
import com.dal.drplus.model.factory.ModelFactory;
import com.dal.drplus.repository.configuration.DatabaseConfiguration;
import com.dal.drplus.repository.configuration.DatabaseConfigurationImpl;
import com.dal.drplus.repository.interfaces.IRatingLabRepository;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RatingLabRepositoryImpl implements IRatingLabRepository {

    DatabaseConfiguration databaseConfiguration;

    private DatabaseConfiguration dbConfig(){
        return new DatabaseConfigurationImpl();
    }
    public RatingLabRepositoryImpl() {
        this.databaseConfiguration = dbConfig();
    }

    String INSERT_RATING_LAB = "INSERT into RatingLab (patient_id,lab_id,lab_rating,review) VALUES (?,?,?,?)";
    String UPDATE_RATING_LAB = "UPDATE RatingLab SET lab_rating=? WHERE rating_id=? and lab_id=?";
    String UPDATE_REVIEW_LAB = "UPDATE RatingLab SET review=? WHERE rating_id=? and lab_id=?";
    String SELECT_BY_RATING_AND_LAB_ID = "SELECT * FROM RatingLab WHERE rating_id=? and lab_id=?";
    String SELECT_BY_LAB_ID = "SELECT * FROM RatingLab WHERE lab_id=?";
    String SELECT_BY_LAB_ID_AND_PATIENT_ID = "SELECT * FROM RatingLab where lab_id = ? and patient_id = ?";
    String SELECT_BY_PATIENT_ID = "SELECT * FROM RatingLab WHERE patient_id=?";
    String SELECT_REVIEWS_BY_LAB_ID = "SELECT review FROM RatingLab WHERE lab_id=?";
    String SELECT_RATING_BY_LAB_ID = "SELECT lab_rating FROM RatingLab WHERE lab_id=?";
    String DELETE_BY_LAB_ID = "DELETE FROM RatingLab WHERE lab_id=?";
    String DELETE_BY_PATIENT_ID = "DELETE FROM RatingLab WHERE patient_id=?";
    String SELECT_ALL = "SELECT * from RatingLab";
    String DELETE_ALL = "DELETE from RatingLab";

    @Override
    public StorageResult saveLabRating(IRatingLab ratingLab) {
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(INSERT_RATING_LAB);
            statement.setString(1, ratingLab.getPatientId());
            statement.setString(2,ratingLab.getLabId());
            statement.setInt(3,ratingLab.getLabRating());
            statement.setString(4, ratingLab.getReview());
            int result = statement.executeUpdate();
            if(result == 1){
                return IRatingLabRepository.StorageResult.SUCCESS;
            }else{
                return IRatingLabRepository.StorageResult.FAILURE;
            }

        } catch (SQLException e) {
            return IRatingLabRepository.StorageResult.SUCCESS;
        }
    }

    @Override
    public StorageResult updateLabRating(RatingLab ratingLab) {
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(UPDATE_RATING_LAB);
            statement.setInt(1,ratingLab.getLabRating());
            statement.setInt(2,ratingLab.getRatingId());
            statement.setString(3,ratingLab.getLabId());
            int result = statement.executeUpdate();
            if(result == 1){
                return IRatingLabRepository.StorageResult.SUCCESS;
            }else{
                return IRatingLabRepository.StorageResult.FAILURE;
            }
        } catch (SQLException e) {
            return IRatingLabRepository.StorageResult.FAILURE;
        }
    }

    @Override
    public StorageResult updateLabReview(RatingLab ratingLab) {
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(UPDATE_REVIEW_LAB);
            statement.setString(1, ratingLab.getReview());
            statement.setInt(2,ratingLab.getRatingId());
            statement.setString(3,ratingLab.getLabId());
            int result = statement.executeUpdate();
            if(result == 1){
                return IRatingLabRepository.StorageResult.SUCCESS;
            }else{
                return IRatingLabRepository.StorageResult.FAILURE;
            }
        } catch (SQLException e) {
            return IRatingLabRepository.StorageResult.FAILURE;
        }
    }

    @Override
    public IRatingLab findLabRatingById(int ratingId, String labId) {
        PreparedStatement statement = null;
        try {
            statement = databaseConfiguration.getDBConnection().prepareStatement(SELECT_BY_RATING_AND_LAB_ID);
            statement.setInt(1,ratingId);
            statement.setString(2,labId);
            ResultSet rs = statement.executeQuery();
            RatingLab ratingLab = new RatingLab();
            RatingLabBuilder ratingLabBuilder = new RatingLabBuilder();
            while (rs.next()){
                ratingLab = ratingLabBuilder
                        .addRatingId(rs.getInt("rating_id"))
                        .addPatientId(rs.getString("patient_id"))
                        .addLabId(rs.getString("lab_id"))
                        .addLabRating(rs.getInt("lab_rating"))
                        .addReview(rs.getString("review")).build();
            }
            return ratingLab;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean findLabRatingByLabIdAndPatientID(String labId, String patientId) {
        PreparedStatement statement = null;
        try {
            statement = databaseConfiguration.getDBConnection().prepareStatement(SELECT_BY_LAB_ID_AND_PATIENT_ID);
            statement.setString(1,labId);
            statement.setString(2,patientId);
            ResultSet rs = statement.executeQuery();
            RatingLab ratingLab = new RatingLab();

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
    public List<RatingLab> findLabRatingByLabId(String labId) {
        PreparedStatement statement = null;
        List<RatingLab> ratingLabList = new ArrayList<>();
        try {
            statement = databaseConfiguration.getDBConnection().prepareStatement(SELECT_BY_LAB_ID);
            statement.setString(1,labId);
            ResultSet rs = statement.executeQuery();
            return getRatingLab(ratingLabList, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<RatingLab> findLabRatingByPatientId(String patientId) {
        PreparedStatement statement = null;
        List<RatingLab> ratingLabList = new ArrayList<>();
        try {
            statement = databaseConfiguration.getDBConnection().prepareStatement(SELECT_BY_PATIENT_ID);
            statement.setString(1,patientId);
            ResultSet rs = statement.executeQuery();
            return getRatingLab(ratingLabList, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> findLabReviewsByLabId(String labId) {
        List<String> reviewList = new ArrayList<>();
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(SELECT_REVIEWS_BY_LAB_ID);
            statement.setString(1,labId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){

                reviewList.add(rs.getString("review"));
            }
            return reviewList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Integer> findLabRatingListByLabId(String labId) {
        List<Integer> ratingList = new ArrayList<>();

        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(SELECT_RATING_BY_LAB_ID);
            statement.setString(1,labId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ratingList.add(rs.getInt("lab_rating"));
            }
            return ratingList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public StorageResult deleteLabRatingByLabId(String labId) {
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(DELETE_BY_LAB_ID);
            statement.setString(1,labId);
            int result = statement.executeUpdate();
            if(result == 1) {
                return IRatingLabRepository.StorageResult.SUCCESS;
            }
            return IRatingLabRepository.StorageResult.FAILURE;
        } catch (SQLException e) {
            return IRatingLabRepository.StorageResult.FAILURE;
        }
    }

    @Override
    public StorageResult deleteLabRatingByPatientId(String patientId) {
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(DELETE_BY_PATIENT_ID);
            statement.setString(1,patientId);
            int result = statement.executeUpdate();
            if(result == 1) {
                return IRatingLabRepository.StorageResult.SUCCESS;
            }
            return IRatingLabRepository.StorageResult.FAILURE;
        } catch (SQLException e) {
            return IRatingLabRepository.StorageResult.FAILURE;
        }
    }

    @Override
    public List<RatingLab> findAll() {
        List<RatingLab> ratingLabList = new ArrayList<>();
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(SELECT_ALL);
            ResultSet rs = statement.executeQuery();
            return getRatingLab(ratingLabList, rs);
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
        }
    }

    private List<RatingLab> getRatingLab(List<RatingLab> ratingLabList, ResultSet rs) throws SQLException {
        while (rs.next()){
            RatingLab ratingLab = null;
            IRatingLabBuilder ratingLabBuilder = ModelFactory.instance().createRatingLabBuilder();

            ratingLab = ratingLabBuilder
                    .addRatingId(rs.getInt("rating_id"))
                            .addPatientId(rs.getString("patient_id"))
                                    .addLabId(rs.getString("lab_id"))
                                            .addLabRating(rs.getInt("lab_rating"))
                                                    .addReview(rs.getString("review")).build();

            ratingLabList.add(ratingLab);
        }
        return ratingLabList;
    }
}
