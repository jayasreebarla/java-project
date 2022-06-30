package com.dal.drplus;

import com.dal.drplus.repository.configuration.DatabaseConfiguration;
import com.dal.drplus.repository.configuration.DatabaseConfigurationImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DrPlusApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrPlusApplication.class, args);
		DatabaseConfiguration db = new DatabaseConfigurationImpl();
		db.getDBConnection();
	}

}