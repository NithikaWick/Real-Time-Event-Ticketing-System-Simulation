import java.math.BigDecimal;

/**
 * The Vendor class represents a vendor who releases tickets to a shared TicketPool.
 * This class implements the Runnable interface to allow vendors to run in separate threads.
 */
public class Vendor implements Runnable {
    private TicketPool ticketPool; // The ticketPool which is shared among vendors and customers to add and buy tickets
    private int totalTickets; // The total number of tickets that the vendor will sell
    private int ticketReleaseRate; // The rate at which the vendor will release tickets to the pool
    private BigDecimal ticketPrice; // The price of the ticket
    private String eventName; // The name of the event
    private static int totalTicketsReleased = 0; // Static counter for total tickets released
    private static int maxTotalTickets; // Maximum total tickets to release

    /**
     * Constructs a new Vendor with the specified total tickets, release rate, ticket pool, ticket price, and event name.
     *
     * @param totalTickets the total number of tickets that the vendor will sell
     * @param ticketReleaseRate the rate (in seconds) at which the vendor will release tickets to the pool
     * @param ticketPool the shared TicketPool to which the vendor will add tickets
     * @param ticketPrice the price of the ticket
     * @param eventName the name of the event
     */
    public Vendor(int totalTickets, int ticketReleaseRate, TicketPool ticketPool, BigDecimal ticketPrice, String eventName) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.ticketPool = ticketPool;
        this.ticketPrice = ticketPrice;
        this.eventName = eventName;
    }

    /**
     * Sets the maximum total tickets to release.
     *
     * @param maxTotalTickets the maximum total tickets to release
     */
    public static void setMaxTotalTickets(int maxTotalTickets) {
        Vendor.maxTotalTickets = maxTotalTickets;
    }

    /**
     * The run method is executed when the vendor thread is started.
     * It simulates the vendor releasing tickets to the pool at the specified rate.
     */
    @Override
    public void run() {
        for (int i = 0; i < totalTickets; i++) {
            if (totalTicketsReleased >= maxTotalTickets) {
                break;
            }
            Ticket ticket = new Ticket(i, eventName, ticketPrice);
            ticketPool.addTicket(ticket);
            incrementTotalTicketsReleased();
            try {
                Thread.sleep(ticketReleaseRate * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Increments the total tickets released in a thread-safe manner.
     */
    private synchronized void incrementTotalTicketsReleased() {
        totalTicketsReleased++;
    }

    /**
     * Returns the total number of tickets released.
     *
     * @return the total number of tickets released
     */
    public static int getTotalTicketsReleased() {
        return totalTicketsReleased;
    }
}