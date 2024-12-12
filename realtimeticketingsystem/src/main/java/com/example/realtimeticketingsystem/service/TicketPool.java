package com.example.realtimeticketingsystem.service;

import com.example.realtimeticketingsystem.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.LinkedList;
import java.util.Queue;

@Service
public class TicketPool {
    private final Queue<Ticket> ticketQueue = new LinkedList<>();
    private int maximumCapacity;

    @Autowired
    private ConfigurationService configurationService;

    // Default constructor required for Spring
    public TicketPool() {
        // Initialization will be done in @PostConstruct method
    }

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

    // Method used by vendors to add tickets to the pool
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

    // Method used by customers to buy tickets from the pool
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