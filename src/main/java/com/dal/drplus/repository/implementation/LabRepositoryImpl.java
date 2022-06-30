package com.dal.drplus.repository.implementation;

import com.dal.drplus.model.Lab;
import com.dal.drplus.model.Patient;
import com.dal.drplus.repository.configuration.DatabaseConfiguration;
import com.dal.drplus.repository.configuration.DatabaseConfigurationImpl;
import com.dal.drplus.repository.interfaces.ILabRepository;
import com.dal.drplus.repository.interfaces.IPatientRepository;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LabRepositoryImpl implements ILabRepository{

    String INSERT_LAB = "INSERT into Lab (lab_id,lab_person_name,lab_email_id,lab_password,lab_address,lab_contact_info,lab_pincode) VALUES(?,?,?,?,?,?,?)";
    String UPDATE_LAB = "UPDATE Lab SET lab_person_name=?,lab_email_id=?,lab_password=?,lab_address=?,lab_contact_info=?,lab_pincode=?, WHERE lab_id=?";
    String SELECT_LAB_BY_ID = "SELECT * FROM Lab WHERE lab_id=?";

    String GET_LAB_PASSWORD_BY_ID = "SELECT lab_password FROM Lab WHERE lab_id=?";
    String DELETE_LAB_BY_ID = "DELETE FROM Lab WHERE lab_id=?";
    String SELECT_LAB = "SELECT * from Lab";
    String DELETE_ALL = "DELETE from Lab";

    DatabaseConfiguration databaseConfiguration;

    public LabRepositoryImpl() {
        this.databaseConfiguration = dbConfig();
    }

    private DatabaseConfiguration dbConfig(){
        return new DatabaseConfigurationImpl();
    }

    @Override
    public ILabRepository.StorageResult saveLab(Lab lab) {

        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(INSERT_LAB);
            statement.setString(1, lab.getLabId());
            statement.setString(2, lab.getLabPersonName());
            statement.setString(3, lab.getLabEmailId());
            statement.setString(4, lab.getLabPassword());
            statement.setString(5, lab.getLabAddress());
            statement.setString(6, lab.getLabContactInfo());
            statement.setString(7, lab.getLabPincode());
            statement.executeUpdate();
            System.out.println("inisde save ");
            return ILabRepository.StorageResult.SUCCESS;
        } catch (SQLException e) {
//            throw new RuntimeException(e);
            return ILabRepository.StorageResult.FAILURE;
        }
    }

    @Override
    public StorageResult updateLab(Lab lab) {
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(UPDATE_LAB);
            statement.setString(1, lab.getLabPersonName());
            statement.setString (2, lab.getLabEmailId());
            statement.setString(3, lab.getLabPassword());
            statement.setString(4, lab.getLabAddress());
            statement.setString(5, lab.getLabContactInfo());
            statement.setString(6, lab.getLabPincode());
            statement.setString(7, lab.getLabId());
            statement.executeUpdate();
            return ILabRepository.StorageResult.SUCCESS;
        } catch (SQLException e) {
//            throw new RuntimeException(e);
            return ILabRepository.StorageResult.FAILURE;
        }
    }

    @Override
    public Lab findLabById(String labId) {
        Lab lab = null;
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(SELECT_LAB_BY_ID);
            statement.setString(1,labId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                lab = createLab(rs);
            }
            return lab;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Lab createLab(ResultSet rs) throws SQLException {

        Lab lab= new Lab();
        lab.setLabId(rs.getString("lab_id"));
        lab.setLabPersonName(rs.getString("lab_person_name"));
        lab.setLabEmailId(rs.getString("lab_email_id"));
        lab.setLabPassword(rs.getString("lab_password"));
        lab.setLabAddress(rs.getString("lab_address"));
        lab.setLabContactInfo((rs.getString("lab_phone_no")));
        lab.setLabPincode(rs.getString("lab_pincode"));
        return lab;
    }

    @Override
    public StorageResult deleteLabById(String labId) {
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(DELETE_LAB_BY_ID);
            statement.setString(1,labId);
            statement.executeUpdate();
            return ILabRepository.StorageResult.SUCCESS;
        } catch (SQLException e) {
//            throw new RuntimeException(e);
            return ILabRepository.StorageResult.FAILURE;
        }
    }

    @Override
    public List<Lab> findAll() {
        List<Lab> lab = new ArrayList<>();
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(SELECT_LAB);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                lab.add(createLab(rs));
            }
            return lab;
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
    public String getLabPasswordById(String labId) {
        PreparedStatement statement = null;
        String password = null;
        try {
            statement = databaseConfiguration.getDBConnection().prepareStatement(GET_LAB_PASSWORD_BY_ID);
            statement.setString(1,labId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                password = rs.getString("lab_password");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return password;
    }
}


