package com.elct.elctchar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ElctcharApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElctcharApplication.class, args);
    }

}
