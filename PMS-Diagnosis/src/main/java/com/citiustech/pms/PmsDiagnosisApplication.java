package com.citiustech.pms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication

public class PmsDiagnosisApplication {

	public static void main(String[] args) {
		SpringApplication.run(PmsDiagnosisApplication.class, args);
	}

}
