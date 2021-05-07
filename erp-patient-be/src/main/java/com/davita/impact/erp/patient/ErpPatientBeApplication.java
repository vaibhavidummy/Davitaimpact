package com.davita.impact.erp.patient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.web.client.RestTemplate;

import com.davita.impact.erp.patient.utilities.Auditor;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableFeignClients
public class ErpPatientBeApplication {

	/*
	 * @Bean public AuditorAware<String> auditorAware() { return new Auditor(); }
	 */
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ErpPatientBeApplication.class, args);
	}
	
}
