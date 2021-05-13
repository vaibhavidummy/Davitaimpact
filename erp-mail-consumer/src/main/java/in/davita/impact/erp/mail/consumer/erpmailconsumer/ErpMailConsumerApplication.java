package in.davita.impact.erp.mail.consumer.erpmailconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ErpMailConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErpMailConsumerApplication.class, args);
	}

}
