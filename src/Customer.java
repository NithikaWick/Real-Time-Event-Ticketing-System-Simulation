/**
 * The Customer class represents a customer who buys tickets from a shared TicketPool.
 * This class implements the Runnable interface to allow customers to run in separate threads.
 */
public class Customer implements Runnable {
    private TicketPool ticketPool; // The ticketPool which is shared among vendors and customers to add and buy tickets
    private int customerRetrivelRate; // The rate at which the customer will buy tickets from the pool
    private int quantity; // Quantity of tickets that the customer will buy

    /**
     * Constructs a new Customer with the specified ticket pool, retrieval rate, and quantity.
     *
     * @param ticketPool the shared TicketPool from which the customer will buy tickets
     * @param customerRetrivelRate the rate (in seconds) at which the customer will buy tickets
     * @param quantity the number of tickets the customer will buy
     */
    public Customer(TicketPool ticketPool, int customerRetrivelRate, int quantity) {
        this.ticketPool = ticketPool;
        this.customerRetrivelRate = customerRetrivelRate;
        this.quantity = quantity;
    }

    /**
     * The run method is executed when the customer thread is started.
     * It simulates the customer buying tickets from the pool at the specified rate.
     */
    @Override
    public void run() {
        for (int i = 0; i < quantity; i++) {
            Ticket ticket = ticketPool.buyTicket(); // Buy ticket from the pool
            // Uncomment the following lines to print out the message showing the thread name and the ticket bought
            // System.out.println("Ticket bought by " + Thread.currentThread().getName() + ". Ticket is " + ticket);

            try {
                Thread.sleep(customerRetrivelRate * 1000); // Sleep for the specified retrieval rate
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}