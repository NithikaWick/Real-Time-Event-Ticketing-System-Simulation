package com.example.realtimeticketingsystem.controller;

import com.example.realtimeticketingsystem.model.Configuration;
import com.example.realtimeticketingsystem.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/configuration")
public class ConfigurationController {

    @Autowired
    private ConfigurationService configurationService;

    @PostMapping("/setup")
    public String setupConfiguration(@RequestBody Configuration config) {
        configurationService.saveConfiguration(config);
        return "Configuration saved successfully.";
    }

    @GetMapping("/load")
    public Configuration loadConfiguration() {
        return configurationService.loadConfiguration();
    }
}