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
 * SwaggerConfig.java
 *
 * describe:
 * 
 * 2017年10月17日 下午3:35:22 created By chenq version 0.1
 *
 * 2017年10月17日 下午3:35:22 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
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
	                .title("Spring Boot中使用offline构建RESTful APIs")  
	                .description("spring Boot 中构建RESTful API")  
	                .termsOfServiceUrl("")  
	                .version("1.0")  
	                .build();  
		}
}
