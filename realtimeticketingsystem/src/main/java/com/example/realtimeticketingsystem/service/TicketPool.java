package com.example.realtimeticketingsystem.service;

import com.example.realtimeticketingsystem.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Service class for managing a pool of tickets.
 */
@Service
public class TicketPool {
    private final Queue<Ticket> ticketQueue = new LinkedList<>(); // Queue to hold tickets
    private int maximumCapacity; // Maximum capacity of the ticket pool

    @Autowired
    private ConfigurationService configurationService; // Service for managing configuration settings

    /**
     * Default constructor required for Spring.
     */
    public TicketPool() {
        // Initialization will be done in @PostConstruct method
    }

    /**
     * Initializes the ticket pool after bean construction.
     * Sets the maximum capacity based on the configuration.
     */
    @PostConstruct
    public void init() {
        // Initialize maximumCapacity after bean construction
        if (configurationService.getConfiguration() != null) {
            this.maximumCapacity = configurationService.getConfiguration().getMaximumCapacity();
        } else {
            System.out.println("Configuration not set. Using default maximum capacity.");
            this.maximumCapacity = 100; // Default value or handle as needed
        }
    }

    /**
     * Adds a ticket to the pool.
     * If the pool is full, the calling thread will wait until space is available.
     *
     * @param ticket the ticket to add
     */
    public synchronized void addTicket(Ticket ticket) {
        while (ticketQueue.size() >= maximumCapacity) {
            try {
                System.out.println("Ticket pool is full. " + Thread.currentThread().getName() + " is waiting.");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Reset interrupt status
                return;
            }
        }
        ticketQueue.add(ticket); // Add ticket to the queue
        notifyAll(); // Notify all waiting threads
        // Print out the message to show the thread name and the ticket added to the pool
        System.out.println("Ticket added by - " + Thread.currentThread().getName() + " to the pool. Current pool size: " + ticketQueue.size());
    }

    /**
     * Buys a ticket from the pool.
     * If no tickets are available, the calling thread will wait until a ticket is added.
     *
     * @return the ticket bought from the pool, or null if interrupted
     */
    public synchronized Ticket buyTicket() {
        while (ticketQueue.isEmpty()) {
            try {
                System.out.println("No tickets available. " + Thread.currentThread().getName() + " is waiting.");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Reset interrupt status
                return null;
            }
        }
        Ticket ticket = ticketQueue.poll(); // Remove ticket from the queue
        notifyAll(); // Notify all waiting threads
        // Print out the message to show the thread name and the ticket bought from the pool
        System.out.println(Thread.currentThread().getName() + " has bought a ticket from the pool: " + ticket);
        System.out.println("Current pool size: " + ticketQueue.size());
        return ticket;
    }
}