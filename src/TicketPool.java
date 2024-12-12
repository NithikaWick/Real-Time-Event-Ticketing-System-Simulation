import java.util.LinkedList;
import java.util.Queue;

/**
 * The TicketPool class represents a pool of tickets that can be shared among vendors and customers.
 * It provides methods for adding tickets to the pool and buying tickets from the pool.
 */
public class TicketPool {
    private Queue<Ticket> ticketQueue; // The queue to store tickets
    private int maximumCapacity; // The maximum capacity of the ticket pool

    /**
     * Constructs a new TicketPool with the specified maximum capacity.
     *
     * @param maximumCapacity the maximum capacity of the ticket pool
     */
    public TicketPool(int maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
        this.ticketQueue = new LinkedList<>();
    }

    /**
     * Adds a ticket to the pool. This method is synchronized to ensure thread safety.
     * If the pool is full, the calling thread will wait until space is available.
     *
     * @param ticket the Ticket object to be added to the pool
     */
    public synchronized void addTicket(Ticket ticket) {
        while (ticketQueue.size() >= maximumCapacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ticketQueue.add(ticket); // Add ticket to the queue
        notifyAll(); // Notify all waiting threads
        // Print out the message to show the thread name and the ticket added to the pool
        System.out.println("Ticket added by - " + Thread.currentThread().getName() + " to the pool. Current pool size: " + ticketQueue.size());
    }

    /**
     * Buys a ticket from the pool. This method is synchronized to ensure thread safety.
     * If the pool is empty, the calling thread will wait until a ticket is available.
     *
     * @return the Ticket object bought from the pool
     */
    public synchronized Ticket buyTicket() {
        while (ticketQueue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }
        Ticket ticket = ticketQueue.poll(); // Remove ticket from the queue
        notifyAll(); // Notify all waiting threads
        // Print out the message to show the thread name and the ticket bought from the pool
        System.out.println(Thread.currentThread().getName() + " has bought a ticket from the pool. " + ticket);
        System.out.println("Current pool size: " + ticketQueue.size());
        return ticket;
    }
}