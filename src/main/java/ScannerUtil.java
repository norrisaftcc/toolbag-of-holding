import java.util.Scanner;

/**
 * Utility class to manage Scanner operations throughout the application.
 * Provides a single point of responsibility for Scanner creation and input methods.
 */
public class ScannerUtil {
    private static Scanner scanner = null;
    
    /**
     * Get the singleton Scanner instance.
     * Creates a new Scanner if one doesn't exist yet.
     * 
     * @return The singleton Scanner instance
     */
    public static Scanner getScanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }
    
    /**
     * Close the Scanner when the application is shutting down.
     * Should be called only once at application exit.
     */
    public static void closeScanner() {
        if (scanner != null) {
            scanner.close();
            scanner = null;
        }
    }
    
    /**
     * Get an integer input from the user with validation.
     * Keeps prompting until a valid integer is entered.
     * 
     * @param prompt The message to display to the user
     * @return The validated integer input
     */
    public static int getInt(String prompt) {
        Scanner sc = getScanner();
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                int input = sc.nextInt();
                sc.nextLine(); // Consume the newline
                return input;
            } else {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine(); // Consume the invalid input
            }
        }
    }
    
    /**
     * Get an integer input within a specific range.
     * 
     * @param prompt The message to display to the user
     * @param min The minimum acceptable value (inclusive)
     * @param max The maximum acceptable value (inclusive)
     * @return The validated integer input within the specified range
     */
    public static int getIntInRange(String prompt, int min, int max) {
        while (true) {
            int input = getInt(prompt);
            if (input >= min && input <= max) {
                return input;
            } else {
                System.out.printf("Please enter a number between %d and %d.%n", min, max);
            }
        }
    }
    
    /**
     * Get a string input from the user.
     * 
     * @param prompt The message to display to the user
     * @return The string input
     */
    public static String getString(String prompt) {
        Scanner sc = getScanner();
        System.out.print(prompt);
        return sc.nextLine();
    }
    
    /**
     * Get a yes/no response from the user.
     * 
     * @param prompt The message to display to the user
     * @return true for yes, false for no
     */
    public static boolean getYesNoInput(String prompt) {
        Scanner sc = getScanner();
        while (true) {
            System.out.print(prompt + " (yes/no): ");
            String input = sc.nextLine().trim().toLowerCase();
            
            if (input.equals("yes") || input.equals("y")) {
                return true;
            } else if (input.equals("no") || input.equals("n")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
    }
}