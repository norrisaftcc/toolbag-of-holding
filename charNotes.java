import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Handles character notes and information that doesn't fit elsewhere.
 * Allows players to record languages, proficiencies, feats, and other details.
 */
public class charNotes {
    private static ArrayList<String> notes = new ArrayList<>();

    // Menu option constants
    private static final int ADD_NOTE = 1;
    private static final int VIEW_NOTES = 2;
    private static final int EXIT = 3;

    /**
     * Main entry point for character notes management.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args)
    {
        boolean running = true;

        // Load existing notes if available
        loadNotesFromFile();

        while (running)
        {
            System.out.println("\nCharacter Notes Manager:");
            System.out.println("1. Add a note");
            System.out.println("2. View notes");
            System.out.println("3. Exit");

            int choice = ScannerUtil.getIntInRange("Choose an option: ", 1, 3);

            switch (choice)
            {
                case ADD_NOTE:
                    addNote();
                    break;
                case VIEW_NOTES:
                    viewNotes();
                    break;
                case EXIT:
                    running = false;
                    System.out.println("Exiting character notes manager.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Loads notes from the character sheet file if it exists.
     */
    private static void loadNotesFromFile() {
        try {
            Map<String, String> sections = CharSheetManager.readCharSheet();
            String notesContent = sections.getOrDefault("Notes", "");

            // Clear current notes to avoid duplicates
            notes.clear();

            // Parse notes from the file
            if (notesContent.length() > 6) { // "Notes:" is 6 chars, so we need more than that
                String[] lines = notesContent.split("\n");
                for (int i = 1; i < lines.length; i++) { // Skip the "Notes:" header line
                    if (lines[i].startsWith("- ")) {
                        notes.add(lines[i].substring(2));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Could not load notes from file: " + e.getMessage());
            // Don't stop execution - we'll create new notes
        }
    }

    /**
     * Adds a note to the character's notes collection.
     */
    private static void addNote()
    {
        String note = ScannerUtil.getString("Enter your character note: ");

        if (!note.trim().isEmpty()) {
            notes.add(note);
            updateFile();
            System.out.println("Note added successfully.");
        } else {
            System.out.println("Note cannot be empty. No note added.");
        }
    }

    /**
     * Displays all character notes.
     */
    private static void viewNotes()
    {
        System.out.println("\nCharacter Notes:");
        if (notes.isEmpty())
        {
            System.out.println("No notes available.");
        }
        else
        {
            for (int i = 0; i < notes.size(); i++)
            {
                System.out.println((i+1) + ". " + notes.get(i));
            }
        }
    }

    /**
     * Updates the character sheet file with the current notes.
     */
    private static void updateFile()
    {
        try
        {
            Map<String, String> sections = CharSheetManager.readCharSheet();

            StringBuilder notesSection = new StringBuilder("Notes:\n");
            for (String note : notes)
            {
                notesSection.append("- ").append(note).append("\n");
            }

            sections.put("Notes", notesSection.toString().trim());
            CharSheetManager.writeCharSheet(sections);
            System.out.println("Notes saved to character sheet.");
        }
        catch (IOException e)
        {
            System.out.println("An error occurred while updating the notes: " + e.getMessage());
            e.printStackTrace();
        }
    }
}