package com.example.realtimeticketingsystem.controller;

import com.example.realtimeticketingsystem.service.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing vendor operations.
 */
@RestController
@RequestMapping("/api/vendor")
public class VendorController {

    @Autowired
    private Vendor vendor; // Service for handling vendor operations

    /**
     * Endpoint for starting the vendor ticket releasing process.
     *
     * @return a success message indicating that vendors have started releasing tickets
     */
    @PostMapping("/start")
    public String startVendors() {
        vendor.releaseTickets();
        return "Vendors started releasing tickets.";
    }
}