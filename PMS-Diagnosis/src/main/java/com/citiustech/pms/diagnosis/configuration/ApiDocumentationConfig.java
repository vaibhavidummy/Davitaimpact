package com.citiustech.pms.diagnosis.configuration;

import io.swagger.annotations.Contact;
import io.swagger.annotations.ExternalDocs;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.SwaggerDefinition;

@SwaggerDefinition(
		info = @Info(
                description = "Diagnosis Related Information",
                version = "V1",
                title = "DIAGNOSIS-APPLICATION",
                contact = @Contact(
                   name = "Pratik Tiwari", 
                   email = "pratik.tiwari@citiustech.com" 
                ),
                license = @License(
                   name = "CitiusTech" 
                )
        ),
        consumes = {"application/json", "application/json"},
        produces = {"application/json", "application/json"},
        schemes = {SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS},
        externalDocs = @ExternalDocs(value = "Read This For Sure", url = "http://localhost:8087/healthcare/diagnosis/")
		)
public interface ApiDocumentationConfig {

}
