package com.example.realtimeticketingsystem.model;

import java.math.BigDecimal;

/**
 * Model class representing the configuration settings for an event.
 */
public class Configuration {
    private String eventName;
    private int maxTotalTickets;
    private BigDecimal ticketPrice;
    private int maximumCapacity;
    private int vendorCount;
    private int totalTicketsPerVendor;
    private int quantity;
    private int ticketReleaseRate;
    private int customerRetrievalRate;

    /**
     * No-args constructor.
     */
    public Configuration() {
    }

    /**
     * Parameterized constructor.
     *
     * @param eventName the name of the event
     * @param maxTotalTickets the maximum number of total tickets
     * @param ticketPrice the price of a ticket
     * @param maximumCapacity the maximum capacity of the event
     * @param vendorCount the number of vendors
     * @param totalTicketsPerVendor the total number of tickets per vendor
     * @param quantity the quantity of tickets
     * @param ticketReleaseRate the rate at which tickets are released
     * @param customerRetrievalRate the rate at which customers retrieve tickets
     */
    public Configuration(String eventName, int maxTotalTickets, BigDecimal ticketPrice, int maximumCapacity,
                         int vendorCount, int totalTicketsPerVendor, int quantity,
                         int ticketReleaseRate, int customerRetrievalRate) {
        this.eventName = eventName;
        this.maxTotalTickets = maxTotalTickets;
        this.ticketPrice = ticketPrice;
        this.maximumCapacity = maximumCapacity;
        this.vendorCount = vendorCount;
        this.totalTicketsPerVendor = totalTicketsPerVendor;
        this.quantity = quantity;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    // Getters and Setters

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getMaxTotalTickets() {
        return maxTotalTickets;
    }

    public void setMaxTotalTickets(int maxTotalTickets) {
        this.maxTotalTickets = maxTotalTickets;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getMaximumCapacity() {
        return maximumCapacity;
    }

    public void setMaximumCapacity(int maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
    }

    public int getVendorCount() {
        return vendorCount;
    }

    public void setVendorCount(int vendorCount) {
        this.vendorCount = vendorCount;
    }

    public int getTotalTicketsPerVendor() {
        return totalTicketsPerVendor;
    }

    public void setTotalTicketsPerVendor(int totalTicketsPerVendor) {
        this.totalTicketsPerVendor = totalTicketsPerVendor;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "eventName='" + eventName + '\'' +
                ", maxTotalTickets=" + maxTotalTickets +
                ", ticketPrice=" + ticketPrice +
                ", maximumCapacity=" + maximumCapacity +
                ", vendorCount=" + vendorCount +
                ", totalTicketsPerVendor=" + totalTicketsPerVendor +
                ", quantity=" + quantity +
                ", ticketReleaseRate=" + ticketReleaseRate +
                ", customerRetrievalRate=" + customerRetrievalRate +
                '}';
    }
}