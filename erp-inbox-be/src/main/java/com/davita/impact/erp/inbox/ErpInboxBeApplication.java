package com.davita.impact.erp.inbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@EnableDiscoveryClient
public class ErpInboxBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErpInboxBeApplication.class, args);
	}

}
