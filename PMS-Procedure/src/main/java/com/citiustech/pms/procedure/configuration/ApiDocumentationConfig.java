package com.citiustech.pms.procedure.configuration;

import io.swagger.annotations.Contact;
import io.swagger.annotations.ExternalDocs;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.SwaggerDefinition;

@SwaggerDefinition(
		info = @Info(
                description = "Procedure Related Information",
                version = "V1",
                title = "PROCEDURE-APPLICATION",
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
        externalDocs = @ExternalDocs(value = "Read This For Sure", url = "http://localhost:8086/healthcare/procedure/")
		)
public interface ApiDocumentationConfig {

}
