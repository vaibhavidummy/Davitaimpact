package com.citiustech.pms.procedure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class PmsProcedureApplication {

	public static void main(String[] args) {
		SpringApplication.run(PmsProcedureApplication.class, args);
	}
}
