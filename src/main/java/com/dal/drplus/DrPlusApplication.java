package com.dal.drplus;

import com.dal.drplus.controller.MailController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DrPlusApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrPlusApplication.class, args);
		//MailController controller = new MailController();
		//controller.triggerMailService();
	}

}