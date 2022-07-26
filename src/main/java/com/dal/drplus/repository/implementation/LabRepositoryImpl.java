package com.dal.drplus.repository.implementation;

import com.dal.drplus.model.IBuilder.IDoctorBuilder;
import com.dal.drplus.model.IBuilder.ILabBuilder;
import com.dal.drplus.model.IEntity.ILab;
import com.dal.drplus.model.entity.Lab;
import com.dal.drplus.model.factory.ModelFactory;
import com.dal.drplus.repository.configuration.DatabaseConfiguration;
import com.dal.drplus.repository.configuration.DatabaseConfigurationImpl;
import com.dal.drplus.repository.interfaces.ILabRepository;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LabRepositoryImpl implements ILabRepository{

    String INSERT_LAB = "INSERT into Lab (lab_id,lab_person_name,lab_email_id,lab_password,lab_address,lab_contact_info,lab_pincode,lab_fee) VALUES(?,?,?,?,?,?,?,?)";
    String UPDATE_LAB = "UPDATE Lab SET lab_person_name=?,lab_email_id=?,lab_password=?,lab_address=?,lab_contact_info=?,lab_pincode=?, WHERE lab_id=?";
    String SELECT_LAB_BY_ID = "SELECT * FROM Lab WHERE lab_id=?";

    String GET_LAB_PASSWORD_BY_ID = "SELECT lab_password FROM Lab WHERE lab_id=?";
    String DELETE_LAB_BY_ID = "DELETE FROM Lab WHERE lab_id=?";
    String SELECT_LAB = "SELECT * from Lab";
    String DELETE_ALL = "DELETE from Lab";
    String SELECT_LAB_BY_PINCODE = "Select * from Lab where lab_pincode = ?";

    DatabaseConfiguration databaseConfiguration;

    public LabRepositoryImpl() {
        this.databaseConfiguration = dbConfig();
    }

    private DatabaseConfiguration dbConfig(){
        return new DatabaseConfigurationImpl();
    }


    public StorageResult isLabIdExists(String labId){
        String LAB_EXISTS_QUERY = "Select count(1) FROM Lab WHERE lab_id = ?";
        try {
            PreparedStatement ps = databaseConfiguration.getDBConnection().prepareStatement(LAB_EXISTS_QUERY);
            ps.setString(1,labId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return StorageResult.SUCCESS;
            } else {
                return StorageResult.FAILURE;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ILabRepository.StorageResult saveLab(ILab lab) {

        System.out.println("inside save lab");
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(INSERT_LAB);
            statement.setString(1, lab.getLabId());
            statement.setString(2, lab.getLabPersonName());
            statement.setString(3, lab.getLabEmailId());
            statement.setString(4, lab.getLabPassword());
            statement.setString(5, lab.getLabAddress());
            statement.setString(6, lab.getLabContactInfo());
            statement.setString(7, lab.getLabPincode());
            statement.setDouble(8,lab.getLabFee());

            int result = statement.executeUpdate();
            System.out.println("inside save !!!!!!");
            if (result == 1){return ILabRepository.StorageResult.SUCCESS;}
            else{return ILabRepository.StorageResult.FAILURE;}
        } catch (SQLException e) {
//            throw new RuntimeException(e);
            e.printStackTrace();
            return ILabRepository.StorageResult.FAILURE;
        }
    }


    @Override
    public ILab findLabById(String labId) {
        ILab lab = null;
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


    @Override
    public List<Lab> findAllLabsByPincode(String labPincode) {
        List<Lab> labsByPincode = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = databaseConfiguration.getDBConnection().prepareStatement("Select * from Lab where lab_pincode = ?");
            statement.setString(1,labPincode);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Lab labObject = new Lab();
                labObject.setLabId(rs.getString("lab_id"));
                labObject.setLabPersonName(rs.getString("lab_person_name"));
                labObject.setLabEmailId(rs.getString("lab_email_id"));
                labObject.setLabAddress(rs.getString("lab_address"));
                labObject.setLabPincode(rs.getString("lab_pincode"));
                labObject.setLabContactInfo(rs.getString("lab_contact_info"));
                labObject.setLabFee(rs.getDouble("lab_fee"));

                labsByPincode.add(labObject);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return labsByPincode;
    }

    private Lab createLab(ResultSet rs) throws SQLException {
        Lab lab= null;
        ILabBuilder builder= ModelFactory.instance().createLabBuilder();
                builder
                .addLabId(rs.getString("lab_id"))
                .addLabPersonName(rs.getString("lab_person_name"))
                .addLabEmailId(rs.getString("lab_email_id"))
                .addLabPassword(rs.getString("lab_password"))
                .addLabAddress(rs.getString("lab_address"))
                .addLabContactInfo((rs.getString("lab_contact_info")))
                .addLabPincode(rs.getString("lab_pincode"))
                .addLabFee(rs.getDouble("lab_fee")).build();
        lab = ModelFactory.instance().createLabUsingBuilder(builder);

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


