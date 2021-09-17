package com.myproject.faculte.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//ctte classe permet de personaliser la documentation(filter ce qu'on ne veut pas afficher)
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				//.apis(RequestHandlerSelectors.any())  //on demande a swagger de documenter tout
				.apis(RequestHandlerSelectors.basePackage("com.myproject.faculte"))  //on demande a swagger de documenter notre package de base
				.paths(PathSelectors.any())  //permet de filtrer selon les uri ici on affiche tout //.paths(PathSelectors.regex("pathRegex:/Cours.*")) pour filtrer afficher uniquent ce qui respecte le regex
				.build();                    //pour construire
	}

}

//http://localhost:8090/api/v2/api-docs  /la documentation a cette adresse format json
//http://localhost:8090/api/swagger-ui.html /la documentation a cette adresse format html