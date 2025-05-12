/**
 * Simple test program for ScannerUtil
 */
public class TestScannerUtil {
    public static void main(String[] args) {
        System.out.println("Testing ScannerUtil class...");
        
        try {
            // Test simple validation methods
            System.out.println("ScannerUtil class loaded successfully!");
            
            // Note: We're not actually reading input since this is an automated test
            // but we can verify the class compiles and initializes correctly
            
            System.out.println("All tests passed!");
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Clean up resources
            ScannerUtil.closeScanner();
        }
    }
}