import java.io.*;
import java.math.BigDecimal;

public class ConfigurationManager {
    public static final String CONFIG_FILE = "config.json";

    public static void saveConfiguration(Configuration config) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONFIG_FILE))) {
            writer.write("{\n");
            writer.write("\"eventName\": \"" + config.getEventName() + "\",\n");
            writer.write("\"maxTotalTickets\": " + config.getMaxTotalTickets() + ",\n");
            writer.write("\"ticketPrice\": " + config.getTicketPrice() + ",\n");
            writer.write("\"maximumCapacity\": " + config.getMaximumCapacity() + ",\n");
            writer.write("\"vendorCount\": " + config.getVendorCount() + ",\n");
            writer.write("\"totalTicketsPerVendor\": " + config.getTotalTicketsPerVendor() + ",\n");
            writer.write("\"quantity\": " + config.getQuantity() + ",\n");
            writer.write("\"ticketReleaseRate\": " + config.getTicketReleaseRate() + ",\n");
            writer.write("\"customerRetrievalRate\": " + config.getCustomerRetrievalRate() + "\n");
            writer.write("}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Configuration loadConfiguration() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CONFIG_FILE))) {
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            System.out.println();
            return parseJson(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Configuration parseJson(String json) {
        String[] fields = json.replace("{", "").replace("}", "").split(",");
        String eventName = null;
        int maxTotalTickets = 0;
        BigDecimal ticketPrice = null;
        int maximumCapacity = 0;
        int vendorCount = 0;
        int totalTicketsPerVendor = 0;
        int quantity = 0;
        int ticketReleaseRate = 0;
        int customerRetrievalRate = 0;

        for (String field : fields) {
            String[] keyValue = field.split(":");
            if (keyValue.length != 2) {
                System.err.println("Invalid field: " + field); // Debugging statement
                continue;
            }
            String key = keyValue[0].trim().replace("\"", "");
            String value = keyValue[1].trim().replace("\"", "");

            switch (key) {
                case "eventName":
                    eventName = value;
                    break;
                case "maxTotalTickets":
                    maxTotalTickets = Integer.parseInt(value);
                    break;
                case "ticketPrice":
                    ticketPrice = new BigDecimal(value);
                    break;
                case "maximumCapacity":
                    maximumCapacity = Integer.parseInt(value);
                    break;
                case "vendorCount":
                    vendorCount = Integer.parseInt(value);
                    break;
                case "totalTicketsPerVendor":
                    totalTicketsPerVendor = Integer.parseInt(value);
                    break;
                case "quantity":
                    quantity = Integer.parseInt(value);
                    break;
                case "ticketReleaseRate":
                    ticketReleaseRate = Integer.parseInt(value);
                    break;
                case "customerRetrievalRate":
                    customerRetrievalRate = Integer.parseInt(value);
                    break;
                default:
                    System.err.println("Unknown key: " + key); // Debugging statement
                    break;
            }
        }

        return new Configuration(eventName, maxTotalTickets, ticketPrice, maximumCapacity, vendorCount, totalTicketsPerVendor, quantity, ticketReleaseRate, customerRetrievalRate);

    }

}