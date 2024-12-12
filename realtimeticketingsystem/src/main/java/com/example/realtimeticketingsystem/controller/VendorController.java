package com.example.realtimeticketingsystem.controller;

import com.example.realtimeticketingsystem.service.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendor")
public class VendorController {

    @Autowired
    private Vendor vendor;

    @PostMapping("/start")
    public String startVendors() {
        vendor.releaseTickets();
        return "Vendors started releasing tickets.";
    }
}