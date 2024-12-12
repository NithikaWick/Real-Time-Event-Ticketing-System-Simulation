package com.example.realtimeticketingsystem.websocket;

import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.realtimeticketingsystem.model.*;
import java.util.ArrayList;
import java.util.List;

/**
 * WebSocket handler for managing status updates.
 */
public class StatusWebSocketHandler extends TextWebSocketHandler {

    private final ObjectMapper mapper = new ObjectMapper(); // ObjectMapper for JSON serialization

    /**
     * Handles incoming text messages from WebSocket clients.
     *
     * @param session the WebSocket session
     * @param message the incoming text message
     * @throws Exception if an error occurs while handling the message
     */
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Handle incoming messages if needed
    }

    /**
     * Sends the status update to all connected WebSocket clients.
     *
     * @param status the status to send
     * @throws Exception if an error occurs while sending the status
     */
    public void sendStatus(Status status) throws Exception {
        String statusJson = mapper.writeValueAsString(status); // Convert status to JSON
        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage(statusJson)); // Send status update
            }
        }
    }

    // Manage sessions
    private final List<WebSocketSession> sessions = new ArrayList<>(); // List of active WebSocket sessions

    /**
     * Invoked after a new WebSocket connection is established.
     *
     * @param session the new WebSocket session
     * @throws Exception if an error occurs after connection is established
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session); // Add new session to the list
    }

    /**
     * Invoked after a WebSocket connection is closed.
     *
     * @param session the closed WebSocket session
     * @param status the status of the closed connection
     * @throws Exception if an error occurs after connection is closed
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session); // Remove closed session from the list
    }
}