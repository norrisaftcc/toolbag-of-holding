import java.io.IOException;
import java.io.FileWriter;

public class dndToolbag
{
    // Define constants at the class level
    private static final int PLAY = 1;
    private static final int CREATE = 2;
    private static final int EXIT = 3;

    public static void main(String[] args)
    {
        try {
            showMainMenu();
        } finally {
            // Ensure scanner is closed when application exits
            ScannerUtil.closeScanner();
        }
    }

    private static void showMainMenu()
    {
        System.out.println("Welcome to the Tool Bag of Holding!\n\nDo you want to play or create a character?");
        System.out.println("1. Play\n2. Character Creation\n3. Exit");

        int userMode = ScannerUtil.getIntInRange("Choose: ", 1, 3);

        switch (userMode) {
            case PLAY:
                playMode();
                break;
            case CREATE:
                charCreation();
                break;
            case EXIT:
                System.out.println("Thank you for using the Tool Bag of Holding. Goodbye!");
                break;
            default:
                System.out.println("Invalid selection. Please try again.");
                showMainMenu();
                break;
        }
    }

    // Play mode constants
    private static final int PLAY_ROLL = 1;
    private static final int PLAY_INVENTORY = 2;
    private static final int PLAY_CHAR_SHEET = 3;
    private static final int PLAY_TRACKER = 4;
    private static final int PLAY_COMBAT = 5;
    private static final int PLAY_BACK = 6;

    public static void playMode()
    {
        boolean running = true;

        while (running) {
            System.out.println("You are in play mode.\nYou can:");
            System.out.println("1. Roll");
            System.out.println("2. Keep track of inventory");
            System.out.println("3. Check your character sheet");
            System.out.println("4. Track inspiration, hp, and xp");
            System.out.println("5. Enter combat");
            System.out.println("6. Back to main menu");

            int choice = ScannerUtil.getIntInRange("Choose an option: ", 1, 6);

            switch (choice)
            {
                case PLAY_ROLL:
                    diceRoller.main(null);
                    break;
                case PLAY_INVENTORY:
                    inventory.main(null);
                    break;
                case PLAY_CHAR_SHEET:
                    System.out.println("Call character sheet java file");
                    break;
                case PLAY_TRACKER:
                    System.out.println("Call insp/hp/xp tracker");
                    break;
                case PLAY_COMBAT:
                    combatMode();
                    break;
                case PLAY_BACK:
                    running = false;
                    showMainMenu();
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }


    // Character creation constants
    private static final int CREATE_ROLL_STATS = 1;
    private static final int CREATE_CALC_HP = 2;
    private static final int CREATE_INVENTORY = 3;
    private static final int CREATE_NOTES = 4;
    private static final int CREATE_FINISHED = 5;
    private static final int CREATE_BACK = 6;

    public static void charCreation()
    {
        boolean running = true;

        charInfo.main(null);

        while (running)
        {
            System.out.println("You are in character creation mode.\nYou can:");
            System.out.println("1. Roll stats");
            System.out.println("2. Calculate HP");
            System.out.println("3. Start your inventory");
            System.out.println("4. Make notes");
            System.out.println("5. Finished");
            System.out.println("6. Back to main menu");

            int choice = ScannerUtil.getIntInRange("Choose an option: ", 1, 6);

            switch (choice)
            {
                case CREATE_ROLL_STATS:
                    rollStats.main(null);
                    break;
                case CREATE_CALC_HP:
                    hitPoints.main(null);
                    break;
                case CREATE_INVENTORY:
                    inventory.main(null);
                    break;
                case CREATE_NOTES:
                    charNotes.main(null);
                    break;
                case CREATE_FINISHED:
                    System.out.println("Character creation finished.");
                    running = false;
                    playMode();
                    break;
                case CREATE_BACK:
                    running = false;
                    showMainMenu();
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private static final int ATTACK = 1;
    private static final int SPELLS = 2;
    private static final int TRACK = 3;
    private static final int FLEE = 4;

    public static void combatMode()
    {
        System.out.println("You are in combat mode.\nYou can:");
        System.out.println("1. Engage");
        System.out.println("2. Cast spells");
        System.out.println("3. Track HP and inspiration");
        System.out.println("4. Flee (exit)");

        int choice = ScannerUtil.getIntInRange("Choose an option: ", 1, 4);

        switch (choice) {
            case ATTACK:
                rollToAttack.main(null);
                break;
            case SPELLS:
                //TODO
                System.out.println("Spell tracking not yet implemented.");
                combatMode(); // Return to combat menu
                break;
            case TRACK:
                //TODO
                System.out.println("HP and inspiration tracking not yet implemented.");
                combatMode(); // Return to combat menu
                break;
            case FLEE:
                System.out.println("You have disengaged from combat.");
                playMode();
                break;
            default:
                System.out.println("Invalid input.");
                combatMode();
                break;
        }
    }
}