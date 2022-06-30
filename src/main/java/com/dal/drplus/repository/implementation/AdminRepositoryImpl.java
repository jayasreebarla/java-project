package com.dal.drplus.repository.implementation;

import com.dal.drplus.model.Admin;
import com.dal.drplus.repository.configuration.DatabaseConfiguration;
import com.dal.drplus.repository.configuration.DatabaseConfigurationImpl;
import com.dal.drplus.repository.interfaces.IAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class AdminRepositoryImpl implements IAdminRepository {
    //@Autowired
    //private JdbcTemplate jdbcTemplate;

    DatabaseConfiguration databaseConfiguration;

    public AdminRepositoryImpl() {
        this.databaseConfiguration = dbConfig();
    }

    private DatabaseConfiguration dbConfig(){
        return new DatabaseConfigurationImpl();
    }

    @Override
    public StorageResult addAdmin(Admin admin) {
        String query = "insert into Admin (ADMIN_ID, ADMIN_PASSWORD, ADMIN_ACCESS_KEY) values (?,?,?)";
        try {
            PreparedStatement ps = databaseConfiguration.getDBConnection().prepareStatement(query);
            ps.setString(1, admin.getAdminId());
            ps.setString(2, admin.getAdminPassword());
            ps.setString(3, admin.getAdminAccessKey());

            ps.execute();
            return StorageResult.SUCCESS;
        }
        catch (SQLException e) {
            //throw new RuntimeException(e)
        return StorageResult.FAILURE;
        }
    }

    @Override
    public int deleteAdmin(String adminId) throws SQLException {
        String query = "delete from Admin where admin_id=?";
        PreparedStatement ps = databaseConfiguration.getDBConnection().prepareStatement(query);
        ps.setString(1,adminId);
        int result = ps.executeUpdate();
        return 0;
    }

    @Override
    public StorageResult updateAdmin(Admin admin) throws SQLException {
        String query = "update Admin set admin_password=?, admin_access_key = ? where admin_id=?";
        try {
        PreparedStatement ps = databaseConfiguration.getDBConnection().prepareStatement(query);
        ps.setString(1,admin.getAdminPassword());
        ps.setString(2,admin.getAdminAccessKey());
        ps.setString(3,admin.getAdminId());
        boolean result = ps.execute();
            return StorageResult.SUCCESS;
         }
        catch (SQLException e) {
            //throw new RuntimeException(e);
            return StorageResult.FAILURE;
        }
    }

    @Override
    public Admin getAdminbyId(String adminId){
        String query = "Select * FROM Admin WHERE admin_id = ?";
        try {
        PreparedStatement ps = databaseConfiguration.getDBConnection().prepareStatement(query);
        ps.setString(1,adminId);
        ResultSet rs = ps.executeQuery();

        Admin admin = new Admin();
        while(rs.next()){
            admin.setAdminId(rs.getString("ADMIN_ID"));
            admin.setAdminPassword(rs.getString("ADMIN_PASSWORD"));
            admin.setAdminAccessKey(rs.getString("ADMIN_ACCESS_KEY"));
        }
        return admin;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getAdminPasswordById(String adminId) {
        String query = "Select admin_password FROM Admin WHERE admin_id = ?";
        try {
        PreparedStatement ps = databaseConfiguration.getDBConnection().prepareStatement(query);
        ps.setString(1,adminId);
        ResultSet rs = ps.executeQuery();
        
        String password = null;
        while(rs.next()){
            password = rs.getString("ADMIN_PASSWORD");
        }
            return password;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
