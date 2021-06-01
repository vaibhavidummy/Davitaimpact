package com.citiustech.pms.procedure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableOAuth2Sso
@RefreshScope
public class PmsProcedureApplication {

	public static void main(String[] args) {
		SpringApplication.run(PmsProcedureApplication.class, args);
	}
}
