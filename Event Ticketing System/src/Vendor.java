import java.math.BigDecimal;

public class Vendor implements Runnable{
    private TicketPool ticketPool;//The ticketPool which i shared among vendors and customers to add and buy tickets
    private int totalTickets;//The total number of tickets that the vendor will to sell
    private int ticketReleaseRate;//The rate at which the vendor will release tickets to the pool

    public Vendor(TicketPool ticketPool, int i, int ticketReleaseRate) {

    }

    @Override
    public void run() {
        for (int i = 0; i < totalTickets; i++) {
            Ticket ticket = new Ticket(i, "Event " + i, new BigDecimal(1000));
            ticketPool.addTicket(ticket);
            try {
                Thread.sleep(ticketReleaseRate * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
