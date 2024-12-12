package com.example.realtimeticketingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class RealTimeEventTicketingSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(RealTimeEventTicketingSystemApplication.class, args);
    }
}