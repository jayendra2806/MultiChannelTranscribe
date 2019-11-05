package com.dgtl.datamanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author VS5051403
 * @EnableSwagger2 annotation enables Swagger support in the class
 * 
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	/**
	 * Springfox Docket instance provides the primary API configuration with
	 * sensible defaults and convenience methods for configuration
	 * 
	 * @return
	 */
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
				.build().apiInfo(metaData());
	}
	
	
	/**
	 * metaData() method that returns and ApiInfo object initialized with
	 * information about our API
	 * 
	 * @return
	 */
	private ApiInfo metaData() {
		ApiInfo apiInfo = new ApiInfo("Data Management Service", "Spring Boot REST APIs for Data Management Service",
				"1.0", "Terms of service",
				new Contact("Atos-Syntel Digital One", "https://swagger.io/", "vikramAditya.sen@syntelinc.com"),
				"Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0");
		return apiInfo;
	}
}