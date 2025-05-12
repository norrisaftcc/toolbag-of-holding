/**
 * Handles attack rolls in combat, including modifiers, proficiency,
 * and damage calculations.
 */
public class rollToAttack
{
    // Dice constants
    private static final int D20 = 20;
    private static final int D12 = 12;
    private static final int D10 = 10;
    private static final int D8 = 8;
    private static final int D6 = 6;
    private static final int D4 = 4;

    /**
     * Main entry point for attack rolling.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args)
    {
        intro();
        rollAttack();
    }

    /**
     * Displays introduction and explains how to use the attack roller.
     */
    public static void intro()
    {
        System.out.println("Welcome to combat!");
        System.out.println("You'll roll for attack and add your modifiers, and a proficiency bonus if applicable.");
        System.out.println();
    }

    /**
     * Handles the attack roll process including damage calculation if the attack hits.
     */
    public static void rollAttack()
    {
        // Gather necessary information from player
        int modifier = ScannerUtil.getInt("What is your skill modifier? ");
        System.out.println();

        int profBonus = ScannerUtil.getInt("What is your proficiency bonus? (enter 0 if not applicable) ");
        System.out.println();

        // Roll d20 for attack
        int attackRoll = rollDie(D20);
        System.out.println("Attack roll: " + attackRoll);

        // Calculate total attack value
        int total = attackRoll + modifier + profBonus;
        System.out.println(attackRoll + " + " + modifier + " + " + profBonus);
        System.out.println("You rolled " + total + " to hit");

        // Ask if the attack hit
        boolean hit = ScannerUtil.getYesNoInput("Did you hit?");

        if (hit)
        {
            rollDamage();
        }
        else
        {
            System.out.println("Better luck next time!");

            // Ask if player wants to roll again
            boolean rollAgain = ScannerUtil.getYesNoInput("Would you like to make another attack roll?");
            if (rollAgain) {
                System.out.println();
                rollAttack();
            } else {
                System.out.println("Returning to combat menu.");
            }
        }
    }

    /**
     * Handles damage roll after a successful hit.
     */
    private static void rollDamage()
    {
        System.out.println("Roll for damage!");

        // Get damage die type
        String dieName = ScannerUtil.getString("What die do you want to roll? (ex. d12) ");
        System.out.println();

        // Parse the die type
        int sides = parseDieType(dieName);
        if (sides == 0) {
            System.out.println("Invalid die type. Valid options are d4, d6, d8, d10, or d12.");
            rollDamage();
            return;
        }

        // Get number of dice to roll
        int numOfDice = ScannerUtil.getInt("How many dice do you want to roll? ");
        System.out.println();

        // Roll for damage
        rollDamage(sides, numOfDice);
    }

    /**
     * Parses a die name (e.g. "d6") into the number of sides.
     *
     * @param dieName String representation of the die type
     * @return The number of sides, or 0 if invalid
     */
    private static int parseDieType(String dieName) {
        dieName = dieName.toLowerCase().trim();

        // Handle both "d12" and "12" formats
        if (dieName.startsWith("d")) {
            dieName = dieName.substring(1);
        }

        try {
            int sides = Integer.parseInt(dieName);
            if (sides == 4 || sides == 6 || sides == 8 || sides == 10 || sides == 12) {
                return sides;
            }
        } catch (NumberFormatException e) {
            // Do nothing, will return 0 below
        }

        return 0;
    }

    /**
     * Rolls damage dice and displays the result.
     *
     * @param sides Number of sides on the damage die
     * @param numOfDice Number of dice to roll
     */
    private static void rollDamage(int sides, int numOfDice)
    {
        int rollTotal = 0;

        System.out.println("Rolling " + numOfDice + "d" + sides + " for damage:");

        for (int i = 0; i < numOfDice; i++)
        {
            int roll = rollDie(sides);
            System.out.println("Die " + (i+1) + ": " + roll);
            rollTotal += roll;
        }

        System.out.println("Total damage: " + rollTotal);

        // Ask if player wants to roll again
        askRollAgain();
    }

    /**
     * Rolls a single die of the specified size.
     *
     * @param sides Number of sides on the die
     * @return The result of the die roll (1 to sides)
     */
    private static int rollDie(int sides) {
        return (int)(Math.random() * sides) + 1;
    }

    /**
     * Asks the user if they want to roll again and handles the response.
     */
    private static void askRollAgain()
    {
        boolean rollAgain = ScannerUtil.getYesNoInput("Would you like to make another attack roll?");

        if (rollAgain)
        {
            System.out.println();
            rollAttack();
        }
        else
        {
            System.out.println("Returning to combat menu.");
        }
    }
}