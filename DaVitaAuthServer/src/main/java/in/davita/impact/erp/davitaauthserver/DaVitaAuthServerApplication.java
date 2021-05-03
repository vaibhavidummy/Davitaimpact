package in.davita.impact.erp.davitaauthserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import in.davita.impact.erp.davitaauthserver.filter.ErrorFilter;
import in.davita.impact.erp.davitaauthserver.filter.PostFilter;
import in.davita.impact.erp.davitaauthserver.filter.PreFilter;
import in.davita.impact.erp.davitaauthserver.filter.RouteFilter;


@EnableFeignClients
@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableZuulProxy
@SpringBootApplication
public class DaVitaAuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DaVitaAuthServerApplication.class, args);
	}
	
	 @Bean
	    public PreFilter preFilter() {
	        return new PreFilter();
	    }
	    @Bean
	    public PostFilter postFilter() {
	        return new PostFilter();
	    }
	    @Bean
	    public ErrorFilter errorFilter() {
	        return new ErrorFilter();
	    }
	    @Bean
	    public RouteFilter routeFilter() {
	        return new RouteFilter();
	    }
	

}
