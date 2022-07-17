package com.dal.drplus;

import com.dal.drplus.controller.MailController;
import com.dal.drplus.repository.configuration.DatabaseConfiguration;
import com.dal.drplus.repository.configuration.DatabaseConfigurationImpl;
import com.dal.drplus.service.MailReminderService;
import com.dal.drplus.service.interfaces.MailService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class DrPlusApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrPlusApplication.class, args);
		DatabaseConfiguration db = new DatabaseConfigurationImpl();
		db.getDBConnection();
//		MailController controller = new MailController();
//		controller.triggerMailService();
	}

}