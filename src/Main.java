import java.math.BigDecimal;
import java.util.Scanner;

/**
 * The Main class is the entry point of the application.
 * It handles the configuration setup, creation of vendor and customer threads,
 * and manages the ticket pool.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Configuration config = null;

        System.out.print("Do you want to load configuration data from the previous session or configure the system again? " +
                "\nEnter load(L) or configure(C): ");
        String choice = scanner.nextLine().trim().toLowerCase();

        while (true) {
            if ((choice.equals("l"))||(choice.equals("load"))) {
                config = ConfigurationManager.loadConfiguration();
                if (config == null) {
                    System.out.println("No previous configuration found. Configuring the system again.");
                    config = configureSystem(scanner);
                } else {
                    System.out.println("\n\u001b[1mLoaded configuration: \u001b[m\n" + config);
                    System.out.println("Configuration loaded successfully.");
                    System.out.println();
                }
                break; // Break the loop after loading the configuration
            } else if ((choice.equals("c") || choice.equals("configure"))) {
                config = configureSystem(scanner);
                break;
            } else {
                System.out.println("Invalid input.");
                System.out.print("Enter (load/configure): ");
                choice = scanner.nextLine().trim().toLowerCase();
                continue;
            }
        }

        // Use the configuration data
        Vendor.setMaxTotalTickets(config.getMaxTotalTickets());
        TicketPool ticketPool = new TicketPool(config.getMaximumCapacity());

        // Create and start vendor threads
        Vendor[] vendors = new Vendor[config.getVendorCount()];
        Thread[] vendorThreads = new Thread[config.getVendorCount()];
        for (int i = 0; i < config.getVendorCount(); i++) {
            vendors[i] = new Vendor(config.getTotalTicketsPerVendor(), config.getTicketReleaseRate(), ticketPool, config.getTicketPrice(), config.getEventName());
            vendorThreads[i] = new Thread(vendors[i], "Vendor ID-" + (i + 1));
            vendorThreads[i].start();
        }

        // Create and start customer threads
        Customer[] customers = new Customer[config.getMaxTotalTickets()];
        Thread[] customerThreads = new Thread[config.getMaxTotalTickets()];
        for (int i = 0; i < config.getMaxTotalTickets(); i++) {
            customers[i] = new Customer(ticketPool, config.getCustomerRetrievalRate(), config.getQuantity());
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

        // Save the configuration data
        ConfigurationManager.saveConfiguration(config);
        System.out.println("Configuration saved to " + ConfigurationManager.CONFIG_FILE);
        scanner.close();
    }

    /**
     * Configures the system by prompting the user for various configuration settings.
     *
     * @param scanner the Scanner object used to read user input
     * @return a Configuration object with the specified settings
     */
    private static Configuration configureSystem(Scanner scanner) {
        System.out.print("\nEnter the name of the event: ");
        String eventName = scanner.nextLine();

        int maxTotalTickets = getPositiveIntInput(scanner, "Enter the maximum total number of tickets: ");

        System.out.print("Enter the ticket price (LKR): ");
        BigDecimal ticketPrice = scanner.nextBigDecimal();

        int maximumCapacity = getPositiveIntInput(scanner, "Enter the maximum capacity of the ticket pool: ");

        int vendorCount = getPositiveIntInput(scanner, "Enter the number of vendors: ");
        while (vendorCount > maxTotalTickets) { // Ensure that the number of vendors is less than the maximum total number of tickets
            System.out.println("Number of vendors cannot be greater than the maximum total number of tickets.");
            vendorCount = getPositiveIntInput(scanner, "Enter the number of vendors: ");
        }

        int totalTicketsPerVendor = getPositiveIntInput(scanner, "Enter total tickets per vendor: ");
        // Ensure that the total tickets per vendor is less than or equal to the maximum total number of tickets
        while ((totalTicketsPerVendor > maxTotalTickets) || (totalTicketsPerVendor * vendorCount < maxTotalTickets)) {
            System.out.println("Total tickets per vendor cannot be greater than the maximum total number of tickets.");
            totalTicketsPerVendor = getPositiveIntInput(scanner, "\nEnter total tickets per vendor: ");
        }

        int quantity = getPositiveIntInput(scanner, "Enter the quantity of tickets that the customer will buy: ");

        int ticketReleaseRate = getPositiveIntInput(scanner, "Enter ticket release rate (in seconds): ");

        int customerRetrievalRate = getPositiveIntInput(scanner, "Enter customer ticket retrieval rate (in seconds): ");
        System.out.println();
        scanner.nextLine(); // Consume newline

        return new Configuration(eventName, maxTotalTickets, ticketPrice, maximumCapacity, vendorCount, totalTicketsPerVendor, quantity, ticketReleaseRate, customerRetrievalRate);
    }

    /**
     * Prompts the user for a positive integer input.
     *
     * @param scanner the Scanner object used to read user input
     * @param prompt the prompt message to display to the user
     * @return a positive integer input from the user
     */
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