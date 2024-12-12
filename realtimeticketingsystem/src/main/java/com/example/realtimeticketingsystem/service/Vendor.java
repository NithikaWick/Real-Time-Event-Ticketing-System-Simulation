package com.example.realtimeticketingsystem.service;

import com.example.realtimeticketingsystem.model.Configuration;
import com.example.realtimeticketingsystem.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;

@Service
public class Vendor {

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private TicketPool ticketPool;

    private int totalTickets;
    private int ticketReleaseRate;
    private BigDecimal ticketPrice;
    private String eventName;
    private static int totalTicketsReleased = 0;
    private static int maxTotalTickets;

    // Default constructor
    public Vendor() {
        // Fields will be initialized in @PostConstruct
    }

    @PostConstruct
    public void init() {
        Configuration config = configurationService.getConfiguration();
        if (config != null) {
            this.totalTickets = config.getTotalTicketsPerVendor();
            this.ticketReleaseRate = config.getTicketReleaseRate();
            this.ticketPrice = config.getTicketPrice();
            this.eventName = config.getEventName();
            setMaxTotalTickets(config.getMaxTotalTickets());
        } else {
            System.out.println("Configuration not set. Using default values.");
            this.totalTickets = 10; // default value
            this.ticketReleaseRate = 1; // default value in seconds
            this.ticketPrice = BigDecimal.ZERO; // default value
            this.eventName = "Default Event";
            setMaxTotalTickets(100); // default value
        }
    }

    public static synchronized void setMaxTotalTickets(int maxTotalTickets) {
        Vendor.maxTotalTickets = maxTotalTickets;
    }

    @Async
    public void releaseTickets() {
        Configuration config = configurationService.getConfiguration();
        if (config == null) {
            System.out.println("Configuration not set.");
            return;
        }

        for (int i = 0; i < this.totalTickets; i++) {
            synchronized (Vendor.class) {
                if (totalTicketsReleased >= maxTotalTickets) {
                    System.out.println("Reached maximum total tickets. Stopping release.");
                    break;
                }
                totalTicketsReleased++;
            }
            Ticket ticket = new Ticket(0, config.getEventName(), config.getTicketPrice());
            ticketPool.addTicket(ticket);
            System.out.println("Vendor released ticket: " + ticket);

            try {
                Thread.sleep(this.ticketReleaseRate * 1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Ticket release interrupted.");
                break;
            }
        }
    }

    public static synchronized int getTotalTicketsReleased() {
        return totalTicketsReleased;
    }
}