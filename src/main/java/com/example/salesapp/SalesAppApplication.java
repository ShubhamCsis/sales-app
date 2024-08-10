package com.example.salesapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class to bootstrap the Spring Boot application.
 * It automatically scans and configures all components (e.g., @RestController, @Service, @Repository) in the package and sub-packages.
 */
@SpringBootApplication
public class SalesAppApplication {

    /**
     * The main method that serves as the entry point for the Spring Boot application.
     *
     * @param args Command-line arguments passed during the startup of the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(SalesAppApplication.class, args);
    }
}
