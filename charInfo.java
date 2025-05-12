import java.io.IOException;
import java.util.Map;

/**
 * Handles character basic information such as name and class.
 */
public class charInfo
{
    /**
     * Main entry point for character info creation.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args)
    {
        // Get character information using ScannerUtil
        String charName = ScannerUtil.getString("Enter your character's name: ");
        String charClass = ScannerUtil.getString("Enter your character's class: ");

        displayInfo(charName, charClass);
        writeToFile(charName, charClass);
    }

    /**
     * Displays character information to the console.
     *
     * @param charName Character name
     * @param charClass Character class
     */
    public static void displayInfo(String charName, String charClass)
    {
        System.out.println("Character name: " + charName);
        System.out.println("Class: " + charClass);
    }

    /**
     * Writes character information to the character sheet file.
     *
     * @param charName Character name
     * @param charClass Character class
     */
    public static void writeToFile(String charName, String charClass)
    {
        try
        {
            Map<String, String> sections = CharSheetManager.readCharSheet();
            sections.put("Character Info", "Character name: " + charName + "\nClass: " + charClass);
            CharSheetManager.writeCharSheet(sections);
            System.out.println("Character information saved successfully.");
        }
        catch (IOException e)
        {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
