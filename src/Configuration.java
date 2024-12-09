import java.math.BigDecimal;

public class Configuration {
    private String eventName;
    private int maxTotalTickets;
    private BigDecimal ticketPrice;
    private int maximumCapacity;
    private int vendorCount;
    private int totalTicketsPerVendor;
    private int quantity;
    private int ticketReleaseRate;
    private int customerRetrievalRate;

    // Constructor
    public Configuration(String eventName, int maxTotalTickets, BigDecimal ticketPrice, int maximumCapacity, int vendorCount, int totalTicketsPerVendor, int quantity, int ticketReleaseRate, int customerRetrievalRate) {
        this.eventName = eventName;
        this.maxTotalTickets = maxTotalTickets;
        this.ticketPrice = ticketPrice;
        this.maximumCapacity = maximumCapacity;
        this.vendorCount = vendorCount;
        this.totalTicketsPerVendor = totalTicketsPerVendor;
        this.quantity = quantity;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    // Getters and setters for all fields
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getMaxTotalTickets() {
        return maxTotalTickets;
    }

    public void setMaxTotalTickets(int maxTotalTickets) {
        this.maxTotalTickets = maxTotalTickets;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getMaximumCapacity() {
        return maximumCapacity;
    }

    public void setMaximumCapacity(int maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
    }

    public int getVendorCount() {
        return vendorCount;
    }

    public void setVendorCount(int vendorCount) {
        this.vendorCount = vendorCount;
    }

    public int getTotalTicketsPerVendor() {
        return totalTicketsPerVendor;
    }

    public void setTotalTicketsPerVendor(int totalTicketsPerVendor) {
        this.totalTicketsPerVendor = totalTicketsPerVendor;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }
}