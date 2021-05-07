package in.davita.impact.erp.patient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import in.davita.impact.erp.patient.utilities.Auditor;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef="auditorAware")
@EnableEurekaClient
public class ErpPatientBeApplication {

	@Bean
	public AuditorAware<String> auditorAware() {
		return new Auditor();
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(ErpPatientBeApplication.class, args);
	}
	
	

}
