package com.davita.impact.erp.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class CentralCloudConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentralCloudConfigApplication.class, args);
	}

}
