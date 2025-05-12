import java.io.*;
import java.util.Scanner;

/**
 * Tests for the ScannerUtil class.
 * This serves as a template for how tests would be structured with JUnit.
 * Note: Actual JUnit implementation will require the JUnit library.
 */
public class ScannerUtilTest {
    
    /**
     * Main method to run tests (until JUnit is integrated)
     */
    public static void main(String[] args) {
        // Run all test methods
        testGetInt();
        testGetIntInRange();
        testGetString();
        testGetYesNoInput();
        
        System.out.println("All tests completed!");
    }
    
    /**
     * Tests the getInt method with valid input
     */
    public static void testGetInt() {
        System.out.println("Testing getInt with valid input...");
        
        // Save original System.in for restoration later
        InputStream originalIn = System.in;
        
        try {
            // Mock user input
            String input = "42\n";
            System.setIn(TestUtils.mockUserInput(input));
            
            // Reset scanner to ensure it reads from our mock input
            ScannerUtil.closeScanner();
            
            // Test the method
            int result = ScannerUtil.getInt("Enter a number: ");
            
            // Verify the result
            if (result == 42) {
                System.out.println("PASS: getInt returned the expected value 42");
            } else {
                System.out.println("FAIL: getInt returned " + result + " instead of 42");
            }
        } finally {
            // Restore original System.in
            System.setIn(originalIn);
            ScannerUtil.closeScanner();
        }
    }
    
    /**
     * Tests the getIntInRange method with valid input
     */
    public static void testGetIntInRange() {
        System.out.println("Testing getIntInRange with valid input...");
        
        InputStream originalIn = System.in;
        
        try {
            // Mock user input
            String input = "5\n";
            System.setIn(TestUtils.mockUserInput(input));
            
            // Reset scanner
            ScannerUtil.closeScanner();
            
            // Test the method
            int result = ScannerUtil.getIntInRange("Enter a number between 1 and 10: ", 1, 10);
            
            // Verify the result
            if (result == 5) {
                System.out.println("PASS: getIntInRange returned the expected value 5");
            } else {
                System.out.println("FAIL: getIntInRange returned " + result + " instead of 5");
            }
        } finally {
            System.setIn(originalIn);
            ScannerUtil.closeScanner();
        }
    }
    
    /**
     * Tests the getString method
     */
    public static void testGetString() {
        System.out.println("Testing getString...");
        
        InputStream originalIn = System.in;
        
        try {
            // Mock user input
            String input = "test string\n";
            System.setIn(TestUtils.mockUserInput(input));
            
            // Reset scanner
            ScannerUtil.closeScanner();
            
            // Test the method
            String result = ScannerUtil.getString("Enter a string: ");
            
            // Verify the result
            if (result.equals("test string")) {
                System.out.println("PASS: getString returned the expected value");
            } else {
                System.out.println("FAIL: getString returned '" + result + "' instead of 'test string'");
            }
        } finally {
            System.setIn(originalIn);
            ScannerUtil.closeScanner();
        }
    }
    
    /**
     * Tests the getYesNoInput method with 'yes' input
     */
    public static void testGetYesNoInput() {
        System.out.println("Testing getYesNoInput with 'yes' input...");
        
        InputStream originalIn = System.in;
        
        try {
            // Mock user input
            String input = "yes\n";
            System.setIn(TestUtils.mockUserInput(input));
            
            // Reset scanner
            ScannerUtil.closeScanner();
            
            // Test the method
            boolean result = ScannerUtil.getYesNoInput("Do you agree? ");
            
            // Verify the result
            if (result) {
                System.out.println("PASS: getYesNoInput returned true for 'yes' input");
            } else {
                System.out.println("FAIL: getYesNoInput returned false for 'yes' input");
            }
        } finally {
            System.setIn(originalIn);
            ScannerUtil.closeScanner();
        }
    }
}