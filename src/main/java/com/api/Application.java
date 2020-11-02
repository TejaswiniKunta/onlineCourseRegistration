package com.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication(scanBasePackages = {"com","com.api.Application"})
@EnableSwagger2
@EnableElasticsearchRepositories(basePackages = {"com.api.repositories"})
public class Application {

    public static void main(String args[]){
        SpringApplication.run(Application.class,args);
    }
}
