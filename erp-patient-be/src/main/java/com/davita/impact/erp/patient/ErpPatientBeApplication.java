package com.davita.impact.erp.patient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.web.client.RestTemplate;

import com.davita.impact.erp.patient.utilities.Auditor;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableOAuth2Sso
@RefreshScope
public class ErpPatientBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErpPatientBeApplication.class, args);
	}
	
	@Bean
	 public RestTemplate restTemplate(RestTemplateBuilder builder) {
	      return builder.build();
	   }
}
