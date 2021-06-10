package com.citiustech.pms.diagnosis.configuration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfigurations {

	public static final Contact DEFAULT_CONTACT =  new Contact(
		      "Pratik Tiwari", "http://localhost:8087/healthcare/diagnosis/", "pratik.tiwari@citiustech.com");
		  
		  public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("DIAGNOSIS-APPLICATION", "Diagnosis Related Information",
				  "1.0", "urn:tos", DEFAULT_CONTACT, "CitiusTech 1.0", "http://localhost:8087/healthcare/diagnosis/", Arrays.asList());

		  private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = 
		      new HashSet<String>(Arrays.asList("application/json",
		          "application/json"));

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API_INFO).produces(DEFAULT_PRODUCES_AND_CONSUMES)
				.consumes(DEFAULT_PRODUCES_AND_CONSUMES);
	}

		  
//	@Bean
//	public Docket getDocketForSwagger() {
//		return new Docket(DocumentationType.SWAGGER_2).select()
//				.apis(RequestHandlerSelectors.basePackage("com.citiustech.pms.diagnosis"))
//				.paths(PathSelectors.ant("/healthcare/diagnosis/*"))
//				.build().apiInfo(getApiInfo());
//	}
//
//	private ApiInfo getApiInfo() {
//		return new ApiInfoBuilder().title("DIAGNOSIS-APPLICATION")
//				.version("1.0.0")
//				.description("Diagnosis Related Information")
//				.build();
//	}
}
