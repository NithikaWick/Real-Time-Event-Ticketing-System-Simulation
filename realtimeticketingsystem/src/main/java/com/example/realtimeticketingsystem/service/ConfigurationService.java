package com.example.realtimeticketingsystem.service;

import com.example.realtimeticketingsystem.model.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;

@Service
public class ConfigurationService {
    private static final String CONFIG_FILE = "config.json";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private Configuration configuration;

    public void saveConfiguration(Configuration config) {
        this.configuration = config;
        try {
            objectMapper.writeValue(new File(CONFIG_FILE), config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    public Configuration getConfiguration() {
        return this.configuration;
    }
}