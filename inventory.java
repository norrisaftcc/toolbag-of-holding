import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Handles character inventory management.
 */
public class inventory
{
    private static ArrayList<String> inventory = new ArrayList<>();

    // Menu option constants
    private static final int ADD_ITEM = 1;
    private static final int REMOVE_ITEM = 2;
    private static final int VIEW_INVENTORY = 3;
    private static final int EXIT = 4;

    /**
     * Main entry point for inventory management.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args)
    {
        boolean running = true;

        // Load existing inventory if available
        loadInventoryFromFile();

        while (running)
        {
            System.out.println("\nInventory Tracker:");
            System.out.println("1. Add item");
            System.out.println("2. Remove item");
            System.out.println("3. View inventory");
            System.out.println("4. Exit");

            int choice = ScannerUtil.getIntInRange("Choose an option: ", 1, 4);

            switch (choice)
            {
                case ADD_ITEM:
                    addItem();
                    break;
                case REMOVE_ITEM:
                    removeItem();
                    break;
                case VIEW_INVENTORY:
                    viewInventory();
                    break;
                case EXIT:
                    running = false;
                    System.out.println("Exiting inventory tracker.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Loads inventory from the character sheet file if it exists.
     */
    private static void loadInventoryFromFile() {
        try {
            Map<String, String> sections = CharSheetManager.readCharSheet();
            String inventoryContent = sections.getOrDefault("Inventory", "");

            // Clear current inventory to avoid duplicates
            inventory.clear();

            // Parse inventory items from the file
            if (inventoryContent.length() > 10) { // "Inventory:" is 10 chars, so we need more than that
                String[] lines = inventoryContent.split("\n");
                for (int i = 1; i < lines.length; i++) { // Skip the "Inventory:" header line
                    if (lines[i].startsWith("- ")) {
                        inventory.add(lines[i].substring(2));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Could not load inventory from file: " + e.getMessage());
            // Don't stop execution - we'll create a new inventory
        }
    }

    /**
     * Adds an item to the inventory.
     */
    private static void addItem()
    {
        String item = ScannerUtil.getString("Enter the name of the item to add: ");
        inventory.add(item);
        updateFile();
        System.out.println("Item added to inventory.");
    }

    /**
     * Removes an item from the inventory.
     */
    private static void removeItem()
    {
        // Display current inventory first to help the user
        viewInventory();

        if (inventory.isEmpty()) {
            return; // Don't try to remove from empty inventory
        }

        String item = ScannerUtil.getString("Enter the name of the item to remove: ");
        if (inventory.remove(item))
        {
            updateFile();
            System.out.println("Item removed from inventory.");
        }
        else
        {
            System.out.println("Item not found in inventory.");
        }
    }

    /**
     * Displays the current inventory.
     */
    private static void viewInventory()
    {
        System.out.println("\nCurrent Inventory:");
        if (inventory.isEmpty())
        {
            System.out.println("Inventory is empty.");
        }
        else
        {
            for (String item : inventory)
            {
                System.out.println("- " + item);
            }
        }
    }

    /**
     * Updates the character sheet file with the current inventory.
     */
    private static void updateFile()
    {
        try
        {
            Map<String, String> sections = CharSheetManager.readCharSheet();

            StringBuilder inventorySection = new StringBuilder("Inventory:\n");
            for (String item : inventory)
            {
                inventorySection.append("- ").append(item).append("\n");
            }

            sections.put("Inventory", inventorySection.toString().trim());
            CharSheetManager.writeCharSheet(sections);
            System.out.println("Inventory saved to character sheet.");
        }
        catch (IOException e)
        {
            System.out.println("An error occurred while updating the inventory: " + e.getMessage());
            e.printStackTrace();
        }
    }
}