import java.math.BigDecimal;

public class Ticket {
    private int ticketID;
    private String eventName;
//    private String eventDate;
//    private String eventTime;
//    private String eventLocation;
    private BigDecimal ticketPrice;



    public Ticket(int ticketID, String eventName, BigDecimal ticketPrice) {
        this.ticketID = ticketID;
        this.eventName = eventName;
        this.ticketPrice = ticketPrice;


    }
    public int getTicketID() {
        return ticketID;
    }

    public String getEventName() {
        return eventName;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketID=" + ticketID +
                ", eventName='" + eventName + '\'' +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}
