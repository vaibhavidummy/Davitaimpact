package com.citiustech.pms.diagnosis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class PmsDiagnosisApplication {

	public static void main(String[] args) {
		SpringApplication.run(PmsDiagnosisApplication.class, args);
	}
}