package com.davita.impact.erp.patient;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//@SpringBootTest
class ErpPatientBeApplicationTests {

	//@Test
	void contextLoads() {
	}

	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
}
