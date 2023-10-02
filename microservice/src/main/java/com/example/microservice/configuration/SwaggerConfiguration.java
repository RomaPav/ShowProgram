package com.example.microservice.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
@OpenAPIDefinition(
        info = @Info(
                title = "Docs for MS",
                description = "Swagger module ",
                version = "1.0",
                contact = @Contact(
                        name = "Roma",
                        email = "romdev2.0@gmail.com",
                        url = "chnu.edu.org"
                )
        )
)
public class SwaggerConfiguration {

}
