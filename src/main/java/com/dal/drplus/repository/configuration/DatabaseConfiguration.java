package com.dal.drplus.repository.configuration;

import org.springframework.context.EnvironmentAware;

import java.sql.Connection;


public interface DatabaseConfiguration{
    void getDBConnection();
}
