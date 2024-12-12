package com.example.realtimeticketingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Main class for the Real-Time Event Ticketing System application.
 * This class is responsible for bootstrapping the Spring Boot application.
 */
@SpringBootApplication
@EnableAsync
public class RealTimeEventTicketingSystemApplication {

    /**
     * Main method to run the Spring Boot application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(RealTimeEventTicketingSystemApplication.class, args);
    }
}