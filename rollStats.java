import java.io.IOException;
import java.util.Map;

/**
 * Handles character stat rolling, assignment, and modifier calculation.
 */
public class rollStats
{
    private static final String[] STAT_NAMES = { "STR", "DEX", "CON", "INT", "WIS", "CHA" };

    /**
     * Main entry point for rolling and assigning character stats.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args)
    {
        int[] rolledStats = new int[6]; // Array to store the total rolled values

        intro();

        try
        {
            // Roll initial stats
            System.out.println("Rolling your stats...");
            for (int i = 0; i < 6; i++)
            {
                int[] rollsResult = rolls(); // Get the full rolls array
                int rollTotal = rollsResult[0] + rollsResult[1] + rollsResult[2] + rollsResult[3]
                        - Math.min(Math.min(rollsResult[0], rollsResult[1]), Math.min(rollsResult[2], rollsResult[3]));
                rolledStats[i] = rollTotal; // Store the total rolled value
                System.out.println("Stat " + (i+1) + ": " + rollTotal + " (Rolls: " +
                        rollsResult[0] + ", " + rollsResult[1] + ", " +
                        rollsResult[2] + ", " + rollsResult[3] + ")");
            }

            // Allows the user to re-roll stats below 7 if they want
            rolledStats = rerollLowStats(rolledStats);

            // Assign stats and calculate modifiers
            assignStatsAndCalculateModifiers(rolledStats);
        }
        catch (Exception e)
        {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Displays introduction and instructions for stat rolling.
     */
    public static void intro()
    {
        System.out.println("Hello! Let's roll up your character's stats!");
        System.out.println("This program will roll 4d6 for each stat, dropping the lowest roll.");
        System.out.println("Then you'll be able to assign the values to your attributes.");
        System.out.println("Let's get started!");
    }

    /**
     * Rolls 4d6 for a single ability score.
     *
     * @return Array containing the four dice roll results
     */
    public static int[] rolls()
    {
        int[] results = new int[4];
        for (int i = 0; i < 4; i++) {
            results[i] = (int) (Math.random() * 6) + 1;
        }
        return results;
    }

    /**
     * Allows the user to reroll stats that are below 7.
     *
     * @param rolledStats Array of initially rolled stats
     * @return Updated array with any rerolled stats
     */
    public static int[] rerollLowStats(int[] rolledStats)
    {
        for (int i = 0; i < rolledStats.length; i++)
        {
            if (rolledStats[i] < 7)
            {
                boolean reroll = ScannerUtil.getYesNoInput("Stat " + rolledStats[i] + " is below 7. Would you like to re-roll it?");

                if (reroll)
                {
                    int[] rollsResult = rolls(); // Re-roll the stat
                    int rollTotal = rollsResult[0] + rollsResult[1] + rollsResult[2] + rollsResult[3]
                            - Math.min(Math.min(rollsResult[0], rollsResult[1]), Math.min(rollsResult[2], rollsResult[3]));
                    rolledStats[i] = rollTotal; // Update the stat with the new roll
                    System.out.println("Re-rolled stat: " + rollTotal + " (Rolls: " +
                            rollsResult[0] + ", " + rollsResult[1] + ", " +
                            rollsResult[2] + ", " + rollsResult[3] + ")");
                }
            }
        }

        return rolledStats;
    }

    /**
     * Allows the user to assign rolled stats to specific attributes.
     *
     * @param rolledStats Array of rolled stats to assign
     */
    public static void assignStatsAndCalculateModifiers(int[] rolledStats) {
        int[] assignedStats = new int[6];
        boolean[] used = new boolean[6];

        System.out.println("\nYou rolled the following stats: ");
        for (int stat : rolledStats) {
            System.out.print(stat + " ");
        }
        System.out.println("\nNow, assign these stats to your character's attributes.");

        for (int i = 0; i < STAT_NAMES.length; i++) {
            boolean validInput = false;
            while (!validInput) {
                int value = ScannerUtil.getInt("Assign a value to " + STAT_NAMES[i] + ": ");

                // Check if the value is valid and unused
                for (int j = 0; j < rolledStats.length; j++) {
                    if (rolledStats[j] == value && !used[j]) {
                        assignedStats[i] = value;
                        used[j] = true;
                        validInput = true;
                        break;
                    }
                }

                if (!validInput) {
                    System.out.println("Invalid input. Please assign an unused rolled value.");
                }
            }
        }

        // Display and save assigned stats
        saveAssignedStats(assignedStats);
    }

    /**
     * Saves the assigned stats to the character sheet and displays them.
     *
     * @param assignedStats Array of assigned stat values
     */
    private static void saveAssignedStats(int[] assignedStats) {
        System.out.println("You have assigned the stats as follows:");
        StringBuilder statsSection = new StringBuilder("Assigned Stats:\n");

        for (int i = 0; i < STAT_NAMES.length; i++) {
            int modifier = calculateModifier(assignedStats[i]);
            String modifierStr = modifier >= 0 ? "+" + modifier : String.valueOf(modifier);

            System.out.println(STAT_NAMES[i] + ": " + assignedStats[i] + " (Modifier: " + modifierStr + ")");
            statsSection.append(STAT_NAMES[i]).append(": ").append(assignedStats[i])
                      .append(" (Modifier: ").append(modifierStr).append(")\n");
        }

        try {
            Map<String, String> sections = CharSheetManager.readCharSheet();
            sections.put("Assigned Stats", statsSection.toString().trim());
            CharSheetManager.writeCharSheet(sections);
            System.out.println("Stats saved to character sheet successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Calculates the ability score modifier based on D&D rules.
     *
     * @param abilityScore The ability score value
     * @return The calculated modifier
     */
    public static int calculateModifier(int abilityScore) {
        return (abilityScore - 10) / 2;
    }
}