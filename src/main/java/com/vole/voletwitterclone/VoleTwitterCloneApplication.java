package com.vole.voletwitterclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class VoleTwitterCloneApplication {

    public static void main(String[] args) {
        SpringApplication.run(VoleTwitterCloneApplication.class, args);
    }

}
