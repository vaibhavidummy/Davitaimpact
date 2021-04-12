package in.davita.impact.erp.patientdetails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import in.davita.impact.erp.patientdetails.utilities.Auditor;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef="auditorAware")
public class ErpPatientBeApplication {

	@Bean
	public AuditorAware<String> auditorAware() {
		return new Auditor();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ErpPatientBeApplication.class, args);
	}
	

}
