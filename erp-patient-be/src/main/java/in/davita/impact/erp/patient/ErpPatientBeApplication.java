package in.davita.impact.erp.patient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableJpaRepositories
@SpringBootApplication
public class ErpPatientBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErpPatientBeApplication.class, args);
	}

}
