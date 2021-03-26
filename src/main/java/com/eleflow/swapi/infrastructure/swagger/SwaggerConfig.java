package com.eleflow.swapi.infrastructure.swagger;

import com.eleflow.swapi.port.rest.exception.ErrorResponseBody;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }



    @Bean
    public Docket documentation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.eleflow.swapi.port.rest"))
                .build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, buildGlobalResponses())
                .globalResponseMessage(RequestMethod.POST, buildGlobalResponses())
                .globalResponseMessage(RequestMethod.PUT, buildGlobalResponses())
                .globalResponseMessage(RequestMethod.DELETE, buildGlobalResponses())
                .additionalModels(new TypeResolver().resolve(ErrorResponseBody.class));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SW API")
                .description("Test API to get information about Start Wars planets")
                .build();
    }

    public List<ResponseMessage> buildGlobalResponses() {
        return List.of(
                buildGlobalResponse(HttpStatus.BAD_REQUEST),
                buildGlobalResponse(HttpStatus.NOT_FOUND),
                buildGlobalResponse(HttpStatus.INTERNAL_SERVER_ERROR)
        );
    }

    private ResponseMessage buildGlobalResponse(HttpStatus status) {
        return new ResponseMessageBuilder()
                .code(status.value())
                .message(status.getReasonPhrase())
                .responseModel(new ModelRef("ErrorResponseBody"))
                .build();
    }
}
