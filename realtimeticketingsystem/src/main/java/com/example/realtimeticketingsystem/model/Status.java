package com.example.realtimeticketingsystem.model;

/**
 * Model class representing the status of an operation or event.
 */
public class Status {

    private String message; // The message associated with the status

    private String type; // The type of the status

    /**
     * No-args constructor.
     */
    public Status() {}

    /**
     * Parameterized constructor.
     *
     * @param message the message associated with the status
     * @param type the type of the status
     */
    public Status(String message, String type) {
        this.message = message;
        this.type = type;
    }

    /**
     * Gets the message associated with the status.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message associated with the status.
     *
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the type of the status.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the status.
     *
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
}