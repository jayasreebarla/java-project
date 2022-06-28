package com.dal.drplus.repository.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//@Configuration
//@Component
//
public class DatabaseConfigurationImpl implements DatabaseConfiguration{
    //private Environment env;
//    ConfigVariables variables;
//    private ConfigVariables createVariable(){
//        return new ConfigVariables();
//    }
    public DatabaseConfigurationImpl() {
//        this.variables = createVariable();
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
