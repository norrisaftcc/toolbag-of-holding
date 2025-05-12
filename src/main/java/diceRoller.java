/**
 * Dice rolling utility that handles various types of dice rolls for D&D.
 */
public class diceRoller 
{
    public static void main(String[] args) 
    {
        intro();
        getDice();
    }
    
    /**
     * Displays introduction and instructions for the dice roller.
     */
    public static void intro()
    {
        System.out.println("Welcome to the dice roller!");
        System.out.println("You can roll a d100 (percentile), d20, d12, d10, d8, d6, or d4.");
        System.out.println("You can also roll multiple dice at once, and you can add a modifier to your d20 roll.");
        System.out.println("Good luck! May the dice be in your favour!");
        System.out.println();
    }

    /**
     * Gets user input for dice type, quantity, and modifiers.
     */
    public static void getDice()
    {
        int total = 0, modifier = 0;

        // Gather necessary information from player
        int sidesOfDice = ScannerUtil.getInt("What die do you want to roll? (ex. d20)\nd");
        System.out.println();

        int numOfDice = ScannerUtil.getInt("How many dice do you want to roll? ");
        System.out.println();

        if (sidesOfDice == 20)
        {
            modifier = ScannerUtil.getInt("What is your modifier? (ex. 3, -1) ");
            System.out.println();
        }

        // Handles all valid dice types
        switch (sidesOfDice)
        {
            case 100:
            case 20:
            case 12:
            case 10:
            case 8:
            case 6:
            case 4:
                rollDice(sidesOfDice, numOfDice, modifier);
                break;
            default:
                System.out.println("Invalid input. Please try again.");
                getDice();
        }
    }

    /**
     * Generic dice rolling function that works with any type of dice.
     * 
     * @param sides Number of sides on the dice
     * @param numberOfDice Number of dice to roll
     * @param modifier Modifier to add to the roll total (only used for d20)
     */
    public static void rollDice(int sides, int numberOfDice, int modifier)
    {
        int roll, rollTotal = 0;

        System.out.println("Rolling " + numberOfDice + "d" + sides + ":");
        
        for (int i = 0; i < numberOfDice; i++)
        {
            roll = (int)(Math.random() * sides) + 1;
            System.out.println("Die " + (i+1) + ": " + roll);
            rollTotal += roll;
        }

        int total = rollTotal;
        if (sides == 20 && modifier != 0) {
            total += modifier;
            System.out.println("Roll total: " + rollTotal + " + " + modifier + " = " + total);
        } else {
            System.out.println("Roll total: " + total);
        }
        
        rollAgain();
    }

    /**
     * Asks the user if they want to roll again.
     */
    public static void rollAgain()
    {
        boolean rollAgain = ScannerUtil.getYesNoInput("Would you like to roll again?");
        
        if (rollAgain)
        {
            System.out.println();
            getDice();
        }
        else
        {
            System.out.println("Thank you for using the dice roller!");
            dndToolbag.playMode();
        }
    }
}