package com.example.realtimeticketingsystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

import com.example.realtimeticketingsystem.websocket.StatusWebSocketHandler;

import org.springframework.context.annotation.Bean;

/**
 * Configuration class for setting up WebSocket in the application.
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    /**
     * Registers WebSocket handlers for the application.
     *
     * @param registry the WebSocketHandlerRegistry to which handlers are added
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new StatusWebSocketHandler(), "/ws/status")
                .setAllowedOrigins("*");
    }
}