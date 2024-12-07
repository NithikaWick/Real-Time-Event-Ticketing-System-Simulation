import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private Queue<Ticket> ticketQueue;
    private int maximumCapacity;

    public TicketPool(int maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
        this.ticketQueue = new LinkedList<>();
    }
    //Add ticket method which uses by vendords to add tickets to the pool
    public synchronized void addTicket(Ticket ticket) {
        while (ticketQueue.size() >= maximumCapacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ticketQueue.add(ticket);//add ticket to the queue
        notifyAll();//notify all waiting threads
        //print out the message to show the tread name and the ticket added to the pool
        System.out.println(Thread.currentThread().getName() + " has added a ticket to he pool." + ticket);
        System.out.println("Current pool size: " + ticketQueue.size());
    }

    //Buy ticket method which uses by customers to buy tickets from the pool
    public synchronized Ticket buyTicket() {
        while (ticketQueue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }
        Ticket ticket = ticketQueue.poll();//remove ticket from the queue
        notifyAll();//notify all waiting threads
        //print out the message to show the tread name and the ticket bought from the pool
        System.out.println(Thread.currentThread().getName() + " has bought a ticket from the pool." + ticket);
        System.out.println("Current pool size: " + ticketQueue.size());
        return ticket;
    }
}
