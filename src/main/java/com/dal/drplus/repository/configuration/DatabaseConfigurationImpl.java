package com.dal.drplus.repository.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfigurationImpl implements DatabaseConfiguration{

    public DatabaseConfigurationImpl() {

    }

    @Override
    public Connection getDBConnection() {
        System.out.println("inside get connection"+ ConfigVariables.getDburl());
        System.out.println(ConfigVariables.getDbpassword());
        System.out.println(ConfigVariables.getDbusername());
        try {
            return DriverManager.getConnection(ConfigVariables.getDburl(),ConfigVariables.getDbusername(),ConfigVariables.getDbpassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
