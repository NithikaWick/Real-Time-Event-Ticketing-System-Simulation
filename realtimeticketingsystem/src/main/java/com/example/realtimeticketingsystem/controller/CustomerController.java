package com.example.realtimeticketingsystem.controller;

import com.example.realtimeticketingsystem.service.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing customer operations.
 */
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private Customer customer; // Service for handling customer operations

    /**
     * Endpoint for starting the customer ticket purchasing process.
     *
     * @return a success message indicating that customers have started purchasing tickets
     */
    @PostMapping("/start")
    public String startCustomers() {
        customer.purchaseTickets();
        return "Customers started purchasing tickets.";
    }
}