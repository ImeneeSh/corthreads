package org.example.corthreads;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CorthreadsApplication {
    private static final Logger logger = LoggerFactory.getLogger(CorthreadsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CorthreadsApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> logger.info("Application Corthreads demarre avec succes !");
    }
}