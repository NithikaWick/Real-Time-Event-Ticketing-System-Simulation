package com.example.realtimeticketingsystem.service;

import com.example.realtimeticketingsystem.model.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;

/**
 * Service class for managing configuration settings.
 */
@Service
public class ConfigurationService {
    private static final String CONFIG_FILE = "config.json"; // Path to the configuration file
    private final ObjectMapper objectMapper = new ObjectMapper(); // ObjectMapper for JSON serialization/deserialization
    private Configuration configuration; // Cached configuration object

    /**
     * Saves the given configuration to a file.
     *
     * @param config the configuration to save
     */
    public void saveConfiguration(Configuration config) {
        this.configuration = config;
        try {
            objectMapper.writeValue(new File(CONFIG_FILE), config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the configuration from a file.
     *
     * @return the loaded configuration, or null if an error occurs
     */
    public Configuration loadConfiguration() {
        if (this.configuration != null) {
            return this.configuration;
        }
        try {
            this.configuration = objectMapper.readValue(new File(CONFIG_FILE), Configuration.class);
            return this.configuration;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets the current configuration.
     *
     * @return the current configuration
     */
    public Configuration getConfiguration() {
        return this.configuration;
    }
}