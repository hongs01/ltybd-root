package com.ltybd;

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
 *  SwaggerConfig.java
 *
 *  描述：SwaggerConfig用于配置Swagger
 * 
 *  2017年4月19日 下午12:02:21 By Linsir
 */
@Configuration  
@EnableSwagger2
public class SwaggerConfig {

	
	 	@Bean
	 	public Docket baseAPI()
	 	{
	 		return new Docket(DocumentationType.SWAGGER_2)
	 				.groupName("BaseAPI")
	 				.apiInfo(apiInfo())  
	                .select()  
	                .apis(RequestHandlerSelectors.basePackage("com.ltybd"))  
	                .paths(PathSelectors.any()).build();
	 	}

		private ApiInfo apiInfo() {
			return new ApiInfoBuilder()  
	                .title("LTY_BP_ND_V1.0.0 030 V1.0.1/ltybd-baseinfo应用接口")  
	                .description("spring Boot 中构建RESTful API")  
	                .termsOfServiceUrl("")  
	                .version("1.0")  
	                .build();  
		}
}
