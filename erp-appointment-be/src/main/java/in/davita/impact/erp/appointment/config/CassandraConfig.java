package in.davita.impact.erp.appointment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.EnableCassandraAuditing;
import org.springframework.data.domain.AuditorAware;

import in.davita.impact.erp.appointment.util.Auditable;
import in.davita.impact.erp.appointment.util.AuditorAwareImpl;

@Configuration
@EnableCassandraAuditing
public class CassandraConfig {
	 @Bean
	  public AuditorAware<String> myAuditorProvider() {
	      return new AuditorAwareImpl();
	  }

}
