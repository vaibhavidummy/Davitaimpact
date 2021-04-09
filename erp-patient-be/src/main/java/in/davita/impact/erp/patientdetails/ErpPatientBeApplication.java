package in.davita.impact.erp.patientdetails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ErpPatientBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErpPatientBeApplication.class, args);
	}

}
