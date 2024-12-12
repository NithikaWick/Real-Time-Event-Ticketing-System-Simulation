package com.example.realtimeticketingsystem.service;

import com.example.realtimeticketingsystem.model.Configuration;
import com.example.realtimeticketingsystem.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Service class for managing customer operations.
 */
@Service
public class Customer {

    @Autowired
    private TicketPool ticketPool; // Pool of tickets available for purchase

    @Autowired
    private ConfigurationService configurationService; // Service for managing configuration settings

    // Fields
    private int customerRetrievalRate; // The rate at which the customer will buy tickets from the pool
    private int quantity; // Quantity of tickets that the customer will buy

    /**
     * Default no-args constructor.
     */
    public Customer() {
        // Default constructor
    }

    /**
     * Method to start purchasing tickets asynchronously.
     */
    @Async
    public void purchaseTickets() {
        Configuration config = configurationService.getConfiguration();
        if (config == null) {
            System.out.println("Configuration not set.");
            return;
        }

        // Initialize fields from configuration
        this.customerRetrievalRate = config.getCustomerRetrievalRate();
        this.quantity = config.getQuantity();

        for (int i = 0; i < quantity; i++) {
            Ticket ticket = ticketPool.buyTicket(); // Buy ticket from the pool

            if (ticket != null) {
                System.out.println("Ticket bought by " + Thread.currentThread().getName() + ". Ticket: " + ticket);
            } else {
                System.out.println("No tickets available for " + Thread.currentThread().getName());
            }

            try {
                Thread.sleep(customerRetrievalRate * 1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore the interrupted status
            }
        }
    }
}