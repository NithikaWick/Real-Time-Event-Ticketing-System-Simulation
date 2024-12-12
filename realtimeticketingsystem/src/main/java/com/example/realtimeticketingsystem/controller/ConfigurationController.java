package com.example.realtimeticketingsystem.controller;

import com.example.realtimeticketingsystem.model.Configuration;
import com.example.realtimeticketingsystem.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing configuration settings.
 */
@RestController
@RequestMapping("/api/configuration")
public class ConfigurationController {

    @Autowired
    private ConfigurationService configurationService; // Service for handling configuration operations

    /**
     * Endpoint for setting up a new configuration.
     *
     * @param config the Configuration object to be saved
     * @return a success message indicating the configuration was saved
     */
    @PostMapping("/setup")
    public String setupConfiguration(@RequestBody Configuration config) {
        configurationService.saveConfiguration(config);
        return "Configuration saved successfully.";
    }

    /**
     * Endpoint for loading the current configuration.
     *
     * @return the current Configuration object
     */
    @GetMapping("/load")
    public Configuration loadConfiguration() {
        return configurationService.loadConfiguration();
    }
}