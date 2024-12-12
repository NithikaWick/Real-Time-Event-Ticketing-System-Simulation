import java.math.BigDecimal;

/**
 * The Ticket class represents a ticket for an event.
 * Each ticket has a unique ID, event name, and ticket price.
 */
public class Ticket {
    private static int idCounter = 0; // Static counter for unique IDs
    private int ticketID; // The unique ID of the ticket
    private String eventName; // The name of the event
    private BigDecimal ticketPrice; // The price of the ticket

    /**
     * Constructs a new Ticket with the specified event name and ticket price.
     * The ticket ID is generated automatically.
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
     * @return the unique ID
     */
    private synchronized int generateUniqueID() {
        return idCounter++;
    }

    /**
     * Returns the ticket ID.
     *
     * @return the ticket ID
     */
    public int getTicketID() {
        return ticketID;
    }

    /**
     * Returns the event name.
     *
     * @return the event name
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Returns the ticket price.
     *
     * @return the ticket price
     */
    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    /**
     * Sets the ticket ID.
     *
     * @param ticketID the new ticket ID
     */
    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    /**
     * Sets the event name.
     *
     * @param eventName the new event name
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * Sets the ticket price.
     *
     * @param ticketPrice the new ticket price
     */
    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    /**
     * Returns a string representation of the Ticket object.
     *
     * @return a string representation of the Ticket object
     */
    @Override
    public String toString() {
        return "Ticket{" +
                "ticketID=" + ticketID +
                ", eventName='" + eventName + '\'' +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}