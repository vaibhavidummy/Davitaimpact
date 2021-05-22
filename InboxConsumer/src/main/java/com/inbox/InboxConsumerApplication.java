package com.inbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
@EnableKafka
@SpringBootApplication
public class InboxConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(InboxConsumerApplication.class, args);
	}

}
