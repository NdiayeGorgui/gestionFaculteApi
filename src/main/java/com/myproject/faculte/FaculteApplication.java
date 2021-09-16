package com.myproject.faculte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
//http://localhost:8090/api/v2/api-docs  /la documentation a cette adresse format json
//http://localhost:8090/api/swagger-ui.html /la documentation a cette adresse format html
public class FaculteApplication {

	public static void main(String[] args) {
		SpringApplication.run(FaculteApplication.class, args);
	}

}
