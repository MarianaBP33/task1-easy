package com.mariana.task1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = { "com.mariana.model" })
@ComponentScan(basePackages = { "com.mariana.controller", "com.mariana.service" })
@EnableJpaRepositories(basePackages = { "com.mariana.repository" })
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

    }

}
