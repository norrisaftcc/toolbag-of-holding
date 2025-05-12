import java.io.*;
import java.util.Map;

/**
 * Utility class to assist with unit testing.
 * Provides methods for creating test files, mocking input, etc.
 */
public class TestUtils {
    
    // Temporary directory for test files
    private static final String TEST_DIR = "test_data";
    
    /**
     * Creates the test directory if it doesn't exist
     */
    public static void setupTestDirectory() {
        File dir = new File(TEST_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
    
    /**
     * Deletes test files from the test directory
     */
    public static void cleanupTestDirectory() {
        File dir = new File(TEST_DIR);
        if (dir.exists()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
            }
        }
    }
    
    /**
     * Creates a temporary character sheet file for testing
     * 
     * @param fileName The name of the test file
     * @return The full path to the created file
     * @throws IOException If an error occurs creating the file
     */
    public static String createTestCharacterSheet(String fileName) throws IOException {
        setupTestDirectory();
        String filePath = TEST_DIR + "/" + fileName;
        
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("Character Info\n");
            writer.write("Character name: Test Character\nClass: Fighter\n\n");
            writer.write("Hit Points\n");
            writer.write("HP: 10\n\n");
            writer.write("Assigned Stats\n");
            writer.write("STR: 15 (Modifier: +2)\n");
            writer.write("DEX: 14 (Modifier: +2)\n");
            writer.write("CON: 13 (Modifier: +1)\n");
            writer.write("INT: 12 (Modifier: +1)\n");
            writer.write("WIS: 10 (Modifier: +0)\n");
            writer.write("CHA: 8 (Modifier: -1)\n\n");
            writer.write("Inventory\n");
            writer.write("- Sword\n");
            writer.write("- Shield\n");
            writer.write("- Potion\n\n");
            writer.write("Notes\n");
            writer.write("- Fighting style: Defense\n");
            writer.write("- Proficient in all armor\n");
        }
        
        return filePath;
    }
    
    /**
     * Verifies if a character sheet file contains expected sections and content
     * 
     * @param filePath Path to the character sheet file
     * @param expectedSections Map of section names to expected content fragments
     * @return True if all expected sections and content are found
     * @throws IOException If an error occurs reading the file
     */
    public static boolean verifyCharacterSheetContent(String filePath, Map<String, String> expectedSections) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            return false;
        }
        
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line).append("\n");
            }
        }
        
        String content = fileContent.toString();
        for (Map.Entry<String, String> entry : expectedSections.entrySet()) {
            if (!content.contains(entry.getKey()) || !content.contains(entry.getValue())) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Mocks user input for testing methods that use ScannerUtil
     * 
     * @param input The input to provide
     * @return InputStream that can be used for testing
     */
    public static InputStream mockUserInput(String input) {
        return new ByteArrayInputStream(input.getBytes());
    }
}