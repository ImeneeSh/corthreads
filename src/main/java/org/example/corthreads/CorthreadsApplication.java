package org.example.corthreads;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class CorthreadsApplication implements CommandLineRunner {

    public static void main(String[] args) {

        SpringApplication.run(CorthreadsApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("connexion reussi !");
    }

}
