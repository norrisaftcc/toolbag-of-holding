import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles character hit point calculation and tracking.
 */
public class hitPoints
{
    // Menu option constants
    private static final int CLASS_LIST = 1;
    private static final int CALC = 2;

    // Hit die constants
    private static final int D12 = 12;
    private static final int D10 = 10;
    private static final int D8 = 8;
    private static final int D6 = 6;

    // Class to hit die mapping
    private static final Map<String, Integer> CLASS_HIT_DICE = new HashMap<>();

    static {
        // Initialize the class hit dice map
        CLASS_HIT_DICE.put("Artificer", D8);
        CLASS_HIT_DICE.put("Barbarian", D12);
        CLASS_HIT_DICE.put("Bard", D8);
        CLASS_HIT_DICE.put("Cleric", D8);
        CLASS_HIT_DICE.put("Druid", D8);
        CLASS_HIT_DICE.put("Fighter", D10);
        CLASS_HIT_DICE.put("Monk", D8);
        CLASS_HIT_DICE.put("Paladin", D10);
        CLASS_HIT_DICE.put("Ranger", D10);
        CLASS_HIT_DICE.put("Rogue", D8);
        CLASS_HIT_DICE.put("Sorcerer", D6);
        CLASS_HIT_DICE.put("Warlock", D8);
        CLASS_HIT_DICE.put("Wizard", D6);
    }

    /**
     * Main entry point for hit point calculation.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args)
    {
        showMenu();
    }

    /**
     * Displays the main menu for hit point calculation.
     */
    public static void showMenu()
    {
        // Introduce program and get user input
        System.out.println("Let's calculate your hit points!");
        System.out.println("1. Look at a list of hit dice for base game classes");
        System.out.println("2. Calculate your hit points");

        int userChoice = ScannerUtil.getIntInRange("Enter your choice (1-2): ", 1, 2);

        if (userChoice == CLASS_LIST)
        {
            classList();
            calcHitPoints();
        }
        else if (userChoice == CALC)
        {
            calcHitPoints();
        }
    }

    /**
     * Displays the hit dice for each base game class.
     */
    public static void classList()
    {
        System.out.println("\nClass Hit Dice:");
        System.out.println("---------------");

        for (Map.Entry<String, Integer> entry : CLASS_HIT_DICE.entrySet()) {
            System.out.printf("%-10s Hit Die: d%d\n", entry.getKey(), entry.getValue());
        }
        System.out.println();
    }

    /**
     * Calculates hit points based on hit die and CON modifier.
     */
    public static void calcHitPoints()
    {
        // Get the hit die size
        int validDice[] = {6, 8, 10, 12};
        System.out.println("\nEnter your hit die size (must be 6, 8, 10, or 12):");

        int sidesOfDice = 0;
        boolean validInput = false;

        while (!validInput) {
            sidesOfDice = ScannerUtil.getInt("d");

            for (int die : validDice) {
                if (sidesOfDice == die) {
                    validInput = true;
                    break;
                }
            }

            if (!validInput) {
                System.out.println("Invalid hit die. Please enter 6, 8, 10, or 12.");
            }
        }

        // Get the CON modifier
        int conMod = getConMod();
        System.out.println("CON modifier: " + conMod);

        // Calculate hit points (max roll for first level + CON modifier)
        int hitPoints = sidesOfDice + conMod;

        System.out.println("Your hit points are: " + hitPoints);

        // Ask if the user wants to save the hit points
        boolean saveHP = ScannerUtil.getYesNoInput("Do you want to save these hit points to your character sheet?");

        if (saveHP) {
            addHitPoints(hitPoints);
        }
    }

    /**
     * Gets the character's Constitution modifier from the character sheet.
     *
     * @return The CON modifier value
     */
    public static int getConMod()
    {
        try {
            Map<String, String> sections = CharSheetManager.readCharSheet();
            String statsSection = sections.getOrDefault("Assigned Stats", "");

            // Look for CON in the stats section
            for (String line : statsSection.split("\n")) {
                if (line.startsWith("CON:")) {
                    // Extract the CON value from the line
                    String[] parts = line.split(":");
                    if (parts.length >= 2) {
                        String[] valueParts = parts[1].trim().split(" ");
                        if (valueParts.length >= 1) {
                            int conValue = Integer.parseInt(valueParts[0]);
                            return (conValue - 10) / 2; // Calculate and return the modifier
                        }
                    }
                }
            }

            // If CON not found, prompt user to enter manually
            System.out.println("CON value not found in character sheet.");
            return ScannerUtil.getInt("Please enter your Constitution modifier manually: ");
        }
        catch (IOException e) {
            System.out.println("An error occurred while reading the character sheet: " + e.getMessage());
            return ScannerUtil.getInt("Please enter your Constitution modifier manually: ");
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid CON value format in the character sheet.");
            return ScannerUtil.getInt("Please enter your Constitution modifier manually: ");
        }
    }

    /**
     * Adds hit points to the character sheet.
     *
     * @param hitPoints The hit points value to save
     */
    public static void addHitPoints(int hitPoints)
    {
        try
        {
            Map<String, String> sections = CharSheetManager.readCharSheet();
            sections.put("Hit Points", "Hit Points: " + hitPoints);
            CharSheetManager.writeCharSheet(sections);
            System.out.println("Hit points successfully saved to character sheet!");
        }
        catch (IOException e)
        {
            System.out.println("An error occurred while updating hit points: " + e.getMessage());
            e.printStackTrace();
        }
    }
}