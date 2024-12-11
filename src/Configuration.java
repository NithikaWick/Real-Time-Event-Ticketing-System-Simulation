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

    @Override
    public String toString() {
        return  "\n\u001B[1m• eventName =\u001B[0m'" + eventName + '\'' +
                ", \n\u001B[1m• maxTotalTickets = \u001B[0m" + maxTotalTickets +
                ", \n\u001B[1m• ticketPrice = \u001B[0m" + ticketPrice +
                ", \n\u001B[1m• maximumCapacity = \u001B[0m" + maximumCapacity +
                ", \n\u001B[1m• vendorCount = \u001B[0m" + vendorCount +
                ", \n\u001B[1m• totalTicketsPerVendor = \u001B[0m" + totalTicketsPerVendor +
                ", \n\u001B[1m• quantity = \u001B[0m" + quantity +
                ", \n\u001B[1m• ticketReleaseRate = \u001B[0m" + ticketReleaseRate +
                ", \n\u001B[1m• customerRetrievalRate = \u001B[0m" + customerRetrievalRate + '\n';
    }
}