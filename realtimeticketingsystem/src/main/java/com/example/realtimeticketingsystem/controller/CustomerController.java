package com.example.realtimeticketingsystem.controller;

import com.example.realtimeticketingsystem.service.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private Customer customer;

    @PostMapping("/start")
    public String startCustomers() {
        customer.purchaseTickets();
        return "Customers started purchasing tickets.";
    }
}