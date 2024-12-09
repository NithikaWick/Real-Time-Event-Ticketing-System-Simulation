public class Customer  implements Runnable{
    private TicketPool ticketPool; //The ticketPool which i shared among vendors and customers to add and buy tickets
    private int customerRetrivelRate; //The rate at which the customer will buy tickets from the pool
    private int quantity; //Quantity of tickets that the customer will buy

    public Customer(TicketPool ticketPool, int customerRetrivelRate, int quantity) {
        this.ticketPool = ticketPool;
        this.customerRetrivelRate = customerRetrivelRate;
        this.quantity = quantity;
    }
    @Override
    public void run() {
        for (int i = 0; i < quantity; i++) {
            Ticket ticket = ticketPool.buyTicket();//buy ticket from the pool
//            System.out.println("Ticket bought by "
//                    + Thread.currentThread().getName() + ". Ticket is "
//                    + ticket);//print out the message to show the thread name and the ticket bought

            try {
                Thread.sleep(customerRetrivelRate * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
