import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * Manages character sheet data storage and retrieval.
 * Acts as a centralized data manager for all character information.
 */
public class CharSheetManager
{
    // Character sheet file path
    private static final String DEFAULT_FILE_PATH = "charSheet.txt";
    private static final String CHARACTER_FOLDER = "characters";

    // Define the default structure of the character sheet
    private static final LinkedHashMap<String, String> DEFAULT_SECTIONS = new LinkedHashMap<>()
    {{
        put("Character Info", "Character name: \nClass: ");
        put("Hit Points", "HP: ");
        put("Assigned Stats", "Stats:\n");
        put("Inventory", "Inventory:\n");
        put("Notes", "Other Notes:\n");
    }};

    /**
     * Reads the character sheet file and returns its sections.
     * Creates a default character sheet if none exists.
     *
     * @return Map containing all character sheet sections
     * @throws IOException If an error occurs reading the file
     */
    public static Map<String, String> readCharSheet() throws IOException
    {
        return readCharSheet(DEFAULT_FILE_PATH);
    }

    /**
     * Reads a specific character sheet file and returns its sections.
     * Creates a default character sheet if none exists.
     *
     * @param filePath Path to the character sheet file
     * @return Map containing all character sheet sections
     * @throws IOException If an error occurs reading the file
     */
    public static Map<String, String> readCharSheet(String filePath) throws IOException
    {
        // Create a copy of defaults for the initial sections
        Map<String, String> sections = new LinkedHashMap<>(DEFAULT_SECTIONS);

        // Get the file and verify it exists
        File file = new File(filePath);
        if (!file.exists())
        {
            System.out.println("Character sheet file not found. Creating a new one.");
            // Create directory if needed
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }
            // Initialize with default sections by writing a new file
            writeCharSheet(sections, filePath);
            return sections;
        }

        // Read file content
        try (Scanner scanner = new Scanner(file))
        {
            String currentSection = null;

            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();

                // Check if this line is a section header
                if (sections.containsKey(line))
                {
                    currentSection = line;
                }
                // If we're in a section, add content to it
                else if (currentSection != null)
                {
                    sections.put(currentSection, sections.get(currentSection) + line + "\n");
                }
                // Otherwise, this line doesn't belong to any known section
                // We silently ignore it to maintain backward compatibility
            }
        }
        catch (FileNotFoundException e)
        {
            // We already checked existence, but just in case the file was deleted
            throw new IOException("Character sheet file was deleted while being read: " + filePath, e);
        }

        return sections;
    }

    /**
     * Writes character data to the default character sheet file.
     *
     * @param sections Map containing all character sheet sections
     * @throws IOException If an error occurs writing the file
     */
    public static void writeCharSheet(Map<String, String> sections) throws IOException
    {
        writeCharSheet(sections, DEFAULT_FILE_PATH);
    }

    /**
     * Writes character data to the specified character sheet file.
     *
     * @param sections Map containing all character sheet sections
     * @param filePath Path to the character sheet file
     * @throws IOException If an error occurs writing the file
     */
    public static void writeCharSheet(Map<String, String> sections, String filePath) throws IOException
    {
        // Create a backup of the existing file if it exists
        File file = new File(filePath);
        if (file.exists()) {
            createBackup(filePath);
        }

        // Create directory if needed
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        // Write the file
        try (FileWriter writer = new FileWriter(filePath))
        {
            for (Map.Entry<String, String> entry : DEFAULT_SECTIONS.entrySet())
            {
                String sectionName = entry.getKey();
                String content = sections.getOrDefault(sectionName, entry.getValue()).trim();

                writer.write(sectionName + "\n");
                writer.write(content + "\n\n");
            }
        }
        catch (IOException e)
        {
            // Handle specific error conditions
            if (file.exists() && !file.canWrite()) {
                throw new IOException("Cannot write to character sheet file: " + filePath + " (Permission denied)", e);
            } else {
                throw new IOException("Failed to write character sheet: " + e.getMessage(), e);
            }
        }
    }

    /**
     * Creates a backup of the character sheet file.
     *
     * @param filePath Path to the character sheet file
     * @throws IOException If an error occurs creating the backup
     */
    private static void createBackup(String filePath) throws IOException {
        File originalFile = new File(filePath);
        if (!originalFile.exists()) {
            return; // Nothing to back up
        }

        // Create backup filename with timestamp
        String timestamp = String.valueOf(System.currentTimeMillis());
        String backupPath = filePath + "." + timestamp + ".bak";

        try {
            Files.copy(originalFile.toPath(), new File(backupPath).toPath(),
                       StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            // Don't stop execution if backup fails, just log it
            System.out.println("Warning: Failed to create backup of character sheet: " + e.getMessage());
        }
    }

    /**
     * Lists all available character sheet files in the characters directory.
     *
     * @return Array of character sheet file names
     */
    public static String[] listCharacterFiles() {
        File folder = new File(CHARACTER_FOLDER);
        if (!folder.exists() || !folder.isDirectory()) {
            folder.mkdirs();
            return new String[0]; // Return empty array
        }

        // Only return .txt files
        return folder.list((dir, name) -> name.toLowerCase().endsWith(".txt"));
    }
}
