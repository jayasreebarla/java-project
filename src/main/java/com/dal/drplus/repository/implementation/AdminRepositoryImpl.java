package com.dal.drplus.repository.implementation;

import com.dal.drplus.model.IBuilder.IAdminBuilder;
import com.dal.drplus.model.IEntity.IAdmin;
import com.dal.drplus.model.entity.Admin;
import com.dal.drplus.model.factory.ModelFactory;
import com.dal.drplus.repository.configuration.DatabaseConfiguration;
import com.dal.drplus.repository.configuration.DatabaseConfigurationImpl;
import com.dal.drplus.repository.interfaces.IAdminRepository;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class AdminRepositoryImpl implements IAdminRepository {
    DatabaseConfiguration databaseConfiguration;

    public AdminRepositoryImpl() {
        this.databaseConfiguration = dbConfig();
    }
    private DatabaseConfiguration dbConfig(){
        return new DatabaseConfigurationImpl();
    }

    @Override
    public StorageResult addAdmin(IAdmin admin) {
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
    public IAdmin getAdminbyId(String adminId){
        IAdmin adminObject = null;
        String query = "Select * FROM Admin WHERE admin_id = ?";
        try {
        PreparedStatement ps = databaseConfiguration.getDBConnection().prepareStatement(query);
        ps.setString(1,adminId);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            adminObject = createAdmin(rs);
        }
        return adminObject;
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

    private Admin createAdmin(ResultSet rs) throws SQLException {
        Admin adminObject = null;
        IAdminBuilder adminBuilder = ModelFactory.instance().createAdminBuilder();

        adminBuilder
                .addAdminId(rs.getString("ADMIN_ID"))
                .addAdminPassword(rs.getString("ADMIN_PASSWORD"))
                .addAdminAccessKey(rs.getString("ADMIN_ACCESS_KEY"))
                .build();

        adminObject = ModelFactory.instance().createAdminUsingBuilder(adminBuilder);

        return adminObject;
    }
}
