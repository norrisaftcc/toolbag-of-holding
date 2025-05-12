import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Tests for the CharSheetManager class.
 * This serves as a template for how tests would be structured with JUnit.
 */
public class CharSheetManagerTest {
    
    private static final String TEST_FILE = "test_character.txt";
    
    /**
     * Main method to run tests (until JUnit is integrated)
     */
    public static void main(String[] args) {
        try {
            // Set up and clean up for tests
            TestUtils.setupTestDirectory();
            
            // Run test methods
            testReadWriteCharSheet();
            testCharSheetSections();
            testBackupCreation();
            
            // Clean up after tests
            TestUtils.cleanupTestDirectory();
            
            System.out.println("All CharSheetManager tests completed!");
        } catch (Exception e) {
            System.out.println("Test failed with exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Tests the read and write functionality of CharSheetManager
     */
    public static void testReadWriteCharSheet() throws IOException {
        System.out.println("Testing reading and writing character sheet...");
        
        String testFilePath = TestUtils.createTestCharacterSheet(TEST_FILE);
        
        // Read the test file
        Map<String, String> sections = CharSheetManager.readCharSheet(testFilePath);
        
        // Verify the sections were read correctly
        if (!sections.containsKey("Character Info") || !sections.get("Character Info").contains("Test Character")) {
            System.out.println("FAIL: Character Info section not read correctly");
            return;
        }
        
        if (!sections.containsKey("Inventory") || !sections.get("Inventory").contains("Sword")) {
            System.out.println("FAIL: Inventory section not read correctly");
            return;
        }
        
        // Modify a section
        sections.put("Hit Points", "HP: 15");
        
        // Write the modified sections back
        CharSheetManager.writeCharSheet(sections, testFilePath);
        
        // Read the file again to verify changes
        sections = CharSheetManager.readCharSheet(testFilePath);
        
        if (!sections.get("Hit Points").equals("HP: 15")) {
            System.out.println("FAIL: Hit Points section not updated correctly");
            return;
        }
        
        System.out.println("PASS: Character sheet read and write functionality works correctly");
    }
    
    /**
     * Tests that all expected sections are present in a new character sheet
     */
    public static void testCharSheetSections() throws IOException {
        System.out.println("Testing character sheet sections...");
        
        // Create a new temporary file path
        String newFilePath = "test_data/new_character.txt";
        File file = new File(newFilePath);
        
        // Delete the file if it exists (to test creation of new file)
        if (file.exists()) {
            file.delete();
        }
        
        // Read the file (which should create it with default sections)
        Map<String, String> sections = CharSheetManager.readCharSheet(newFilePath);
        
        // Check that all required sections are present
        String[] requiredSections = {
            "Character Info", "Hit Points", "Assigned Stats", "Inventory", "Notes"
        };
        
        for (String section : requiredSections) {
            if (!sections.containsKey(section)) {
                System.out.println("FAIL: Required section '" + section + "' is missing");
                return;
            }
        }
        
        System.out.println("PASS: All required sections are present in a new character sheet");
    }
    
    /**
     * Tests that a backup is created when writing to an existing file
     */
    public static void testBackupCreation() throws IOException {
        System.out.println("Testing backup creation...");
        
        // Create a test file
        String testFilePath = TestUtils.createTestCharacterSheet("backup_test.txt");
        
        // Get the original file's contents for comparison
        Map<String, String> originalSections = CharSheetManager.readCharSheet(testFilePath);
        
        // Modify and write back to trigger backup
        originalSections.put("Hit Points", "HP: 999");
        CharSheetManager.writeCharSheet(originalSections, testFilePath);
        
        // Look for backup files
        File dir = new File("test_data");
        File[] files = dir.listFiles((d, name) -> name.startsWith("backup_test.txt.") && name.endsWith(".bak"));
        
        if (files == null || files.length == 0) {
            System.out.println("FAIL: No backup file was created");
            return;
        }
        
        System.out.println("PASS: Backup file was created successfully");
    }
}