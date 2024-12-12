package com.example.realtimeticketingsystem.service;

import com.example.realtimeticketingsystem.model.Configuration;
import com.example.realtimeticketingsystem.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;

/**
 * Service class for managing vendor operations.
 */
@Service
public class Vendor {

    @Autowired
    private ConfigurationService configurationService; // Service for managing configuration settings

    @Autowired
    private TicketPool ticketPool; // Pool of tickets available for release

    // Fields
    private int totalTickets; // Total number of tickets to be released by the vendor
    private int ticketReleaseRate; // Rate at which tickets will be released (in seconds)
    private BigDecimal ticketPrice; // Price of each ticket
    private String eventName; // Name of the event
    private static int totalTicketsReleased = 0; // Total number of tickets released so far
    private static int maxTotalTickets; // Maximum number of tickets that can be released

    /**
     * Default constructor.
     * Fields will be initialized in the @PostConstruct method.
     */
    public Vendor() {
        // Fields will be initialized in @PostConstruct
    }

    /**
     * Initializes the vendor after bean construction.
     * Sets the fields based on the configuration.
     */
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

    /**
     * Sets the maximum number of tickets that can be released.
     *
     * @param maxTotalTickets the maximum number of tickets
     */
    public static synchronized void setMaxTotalTickets(int maxTotalTickets) {
        Vendor.maxTotalTickets = maxTotalTickets;
    }

    /**
     * Releases tickets asynchronously.
     * Tickets are released at the specified rate until the total number of tickets is reached or the maximum is exceeded.
     */
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

    /**
     * Gets the total number of tickets released so far.
     *
     * @return the total number of tickets released
     */
    public static synchronized int getTotalTicketsReleased() {
        return totalTicketsReleased;
    }
}