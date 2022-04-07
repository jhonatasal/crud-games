package com.rentgame.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {
	
	@Bean
	public Docket swagger() {
		   return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                .apis(RequestHandlerSelectors.any())
	                .paths(regex("/games.*"))
	                .build().apiInfo(info());
	}

	private ApiInfo info() {
		ApiInfo apiInfo = new ApiInfoBuilder()
						.title("API GAMES")
						.description("Manager Games")
						.version("1.0.0")
						.contact(new Contact("Jhonatas Alves", "https://github.com/jhonatasal", "jhonatasa_s@hotmail.com"))									
						.build();
		return apiInfo;
	}
}
