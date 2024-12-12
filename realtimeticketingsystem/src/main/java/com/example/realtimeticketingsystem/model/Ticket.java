package com.example.realtimeticketingsystem.model;

import java.math.BigDecimal;

/**
 * Model class representing a ticket for an event.
 */
public class Ticket {
    private static int idCounter = 0; // Static counter for unique IDs
    private int ticketID;
    private String eventName;
    private BigDecimal ticketPrice;

    /**
     * Default no-args constructor.
     */
    public Ticket() {
    }

    /**
     * Parameterized constructor.
     *
     * @param ticketID the ID of the ticket
     * @param eventName the name of the event
     * @param ticketPrice the price of the ticket
     */
    public Ticket(int ticketID, String eventName, BigDecimal ticketPrice) {
        this.ticketID = generateUniqueID();
        this.eventName = eventName;
        this.ticketPrice = ticketPrice;
    }

    /**
     * Generates a unique ID for the ticket.
     *
     * @return a unique ticket ID
     */
    private synchronized int generateUniqueID() {
        return idCounter++;
    }

    /**
     * Gets the ID of the ticket.
     *
     * @return the ticket ID
     */
    public int getTicketID() {
        return ticketID;
    }

    /**
     * Gets the name of the event.
     *
     * @return the event name
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Gets the price of the ticket.
     *
     * @return the ticket price
     */
    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    /**
     * Sets the ID of the ticket.
     *
     * @param ticketID the ticket ID to set
     */
    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    /**
     * Sets the name of the event.
     *
     * @param eventName the event name to set
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * Sets the price of the ticket.
     *
     * @param ticketPrice the ticket price to set
     */
    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketID=" + ticketID +
                ", eventName='" + eventName + '\'' +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}