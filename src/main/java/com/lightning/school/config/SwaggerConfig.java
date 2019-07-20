package com.lightning.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String API_VERSION = "V1";

    @Bean
    public Docket api () {
        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(
                        Arrays.asList(new ParameterBuilder()
                                .name("Authorization")
                                .description("Need a token type 'bearer' for use endpoint")
                                .modelRef(new ModelRef("string"))
                                .parameterType("header")
                                .required(true)
                                .build()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lightning.school.mvc.facade"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET,
                        Arrays.asList(
                                // gestion 500
                                new ResponseMessageBuilder()
                                        .code(500)
                                        .message("500 error server")
                                        .responseModel(new ModelRef("errorDto"))
                                        .build(),
                                // gestion 403
                                new ResponseMessageBuilder()
                                        .code(403)
                                        .message("Forbidden!")
                                        .build()
                        )
                );
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Lightning School API DOCS")
                .description("Api de gestion de la platforme Lightning School !!!")
                .version(API_VERSION)
                .build();
    }
}
