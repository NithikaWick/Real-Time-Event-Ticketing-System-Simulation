import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get event name
        System.out.print("Enter the name of the event: ");
        String eventName = scanner.nextLine();

        // Get maximum total number of tickets
        int maxTotalTickets = getPositiveIntInput(scanner, "Enter the maximum total number of tickets: ");
        Vendor.setMaxTotalTickets(maxTotalTickets);

        // Get ticket price
        System.out.print("Enter the ticket price (LKR): ");
        BigDecimal ticketPrice = scanner.nextBigDecimal();

        // Get ticket pool maximum capacity
        int maximumCapacity = getPositiveIntInput(scanner, "Enter the maximum capacity of the ticket pool: ");

        // Get number of vendors
        int vendorCount = getPositiveIntInput(scanner, "Enter the number of vendors: ");


        // Get number of customers
//        int customerCount = getPositiveIntInput(scanner, "Enter the number of customers: ");

        // Get total tickets per vendor
        int totalTicketsPerVendor = getPositiveIntInput(scanner, "Enter total tickets per vendor: ");

        // Get quantity of tickets that the customer will buy
        int quantity = getPositiveIntInput(scanner, "Enter the quantity of tickets that the customer will buy: ");

        // Get ticket release rate for vendors
        int ticketReleaseRate = getPositiveIntInput(scanner, "Enter ticket release rate (in seconds): ");

        // Get customer retrieval rate
        int customerRetrievalRate = getPositiveIntInput(scanner, "Enter customer ticket retrieval rate (in seconds): ");
        System.out.println();
        scanner.nextLine(); // Consume newline

        // Create shared ticket pool
        TicketPool ticketPool = new TicketPool(maximumCapacity);

        // Create and start vendor threads
        Vendor[] vendors = new Vendor[vendorCount];
        Thread[] vendorThreads = new Thread[vendorCount];
        for (int i = 0; i < vendorCount; i++) {
            vendors[i] = new Vendor(totalTicketsPerVendor, ticketReleaseRate, ticketPool, ticketPrice, eventName);
            vendorThreads[i] = new Thread(vendors[i], "Vendor ID-" + (i + 1));
            vendorThreads[i].start();
        }

        // Create and start customer threads
        Customer[] customers = new Customer[maxTotalTickets];//Creating array of customers
        Thread[] customerThreads = new Thread[maxTotalTickets];//Creating array of customer threads
        for (int i = 0; i < maxTotalTickets; i++) {
            customers[i] = new Customer(ticketPool, customerRetrievalRate, quantity);
            customerThreads[i] = new Thread(customers[i], "Customer ID-" + (i + 1));
            customerThreads[i].start();
        }

        // Wait for all threads to complete
        try {
            for (Thread vendorThread : vendorThreads) {
                vendorThread.join();
            }
            for (Thread customerThread : customerThreads) {
                customerThread.join();
            }
        } catch (InterruptedException e) {
            System.err.println("Threads were interrupted: " + e.getMessage());
        }

        // Display the total number of tickets released
        System.out.println("\nTotal number of tickets released: " + Vendor.getTotalTicketsReleased());
        scanner.close();
    }

    private static int getPositiveIntInput(Scanner scanner, String prompt) {
        int input;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. " + prompt);
                scanner.next();
            }
            input = scanner.nextInt();
            if (input <= 0) {
                System.out.print("Input must be positive. ");
            }
        } while (input <= 0);
        return input;
    }
}