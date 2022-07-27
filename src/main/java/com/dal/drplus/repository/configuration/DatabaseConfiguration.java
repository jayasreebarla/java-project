package com.dal.drplus.repository.configuration;

import java.sql.Connection;

public interface DatabaseConfiguration{
    Connection getDBConnection();
}
