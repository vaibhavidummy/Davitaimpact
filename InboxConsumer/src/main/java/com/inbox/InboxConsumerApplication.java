package com.inbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.kafka.annotation.EnableKafka;
@EnableKafka
@EnableOAuth2Sso
@EnableDiscoveryClient
@SpringBootApplication
public class InboxConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(InboxConsumerApplication.class, args);
	}

}
