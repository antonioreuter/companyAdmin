package com.company.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by aandra1 on 12/02/16.
 */

@EnableSwagger2
@Configuration
public class SwaggerConfig {

  @Bean
  public Docket companyAdminApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.company.admin.controllers.api"))
        .paths(PathSelectors.ant("/api/**"))
        .build()
        .apiInfo(apiInfo());
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Company Admin - Web API")
        .description("Company Admin Web API - An exemple of a well designed WEB API based on REST")
        .contact("Antonio Reuter")
        .license("Apache License Version 2.0")
        .version("1.0")
        .build();
  }
}
