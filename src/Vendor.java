import org.w3c.dom.ls.LSOutput;

import java.math.BigDecimal;

public class Vendor implements Runnable {
    private TicketPool ticketPool;//The ticketPool which i shared among vendors and customers to add and buy tickets
    private int totalTickets;//The total number of tickets that the vendor will to sell
    private int ticketReleaseRate;//The rate at which the vendor will release tickets to the pool
    private BigDecimal ticketPrice;//The price of the ticket
    private String eventName; //The name of the event
    private static int totalTicketsReleased = 0; // Static counter for total tickets released
    private static int maxTotalTickets; // Maximum total tickets to release


    public Vendor(int totalTickets, int ticketReleaseRate, TicketPool ticketPool, BigDecimal ticketPrice, String eventName) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.ticketPool = ticketPool;
        this.ticketPrice = ticketPrice;
        this.eventName = eventName;
    }

    public static void setMaxTotalTickets(int maxTotalTickets) {
        Vendor.maxTotalTickets = maxTotalTickets;
    }

    @Override
    public void run() {
        for (int i = 0; i < totalTickets; i++) {
//            synchronized (Vendor.class) {
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
//            }
        }
    }
        private synchronized void incrementTotalTicketsReleased () {
            totalTicketsReleased++;
        }

        public static int getTotalTicketsReleased () {
            return totalTicketsReleased;
        }

}



