package org.example.corthreads;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired ;
import javax.sql.DataSource;

@SpringBootApplication
public class CorthreadsApplication implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) {

        SpringApplication.run(CorthreadsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("connexion reussi !");
    }

}
