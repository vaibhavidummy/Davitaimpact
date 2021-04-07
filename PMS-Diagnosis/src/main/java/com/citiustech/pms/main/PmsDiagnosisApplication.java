package com.citiustech.pms.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan(basePackages = "com.citiustech.pms")
@EnableAutoConfiguration
@SpringBootApplication

public class PmsDiagnosisApplication {

	public static void main(String[] args) {
		SpringApplication.run(PmsDiagnosisApplication.class, args);
	}

}
