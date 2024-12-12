package com.example.realtimeticketingsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Configuration class for setting up CORS (Cross-Origin Resource Sharing) in the application.
 */
@Configuration
public class CorsConfig {

    /**
     * Defines a bean that configures CORS mappings for the application.
     *
     * @return a WebMvcConfigurer that adds CORS mappings
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            /**
             * Configures CORS mappings to allow specified origins, methods, and headers.
             *
             * @param registry the CorsRegistry to which CORS mappings are added
             */
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("http://localhost:3000") // React app URL
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");
            }
        };
    }
}