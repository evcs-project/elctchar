package com.elct.elctchar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private String filterApiPath = "com.elct.elctchar.web.controller";
    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(filterApiPath))
                .paths(PathSelectors.ant("/api/**"))
                .build()
                .apiInfo(apiInfo("전기차 충전소 프로젝트 API 문서", "1.0.0"));
    }

    private ApiInfo apiInfo(String title, String version) {
        return new ApiInfo(
                title,
                "전기차 충전소 api 명세",
                version,
                null,
                null,
                "라이센스 : elct",
                "https://www.notion.so/e0e71f8f0c034bcd819fdc5ddb232bfe?v=1c68fb91d7104087a2141eb45d3ff0a8",
                new ArrayList<>()
    );
    }
}
