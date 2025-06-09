package com.quasar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableMongoRepositories(basePackages = "com.quasar.repository")
public class QuasarApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuasarApplication.class, args);
    }
}
