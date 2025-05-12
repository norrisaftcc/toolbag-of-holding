import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class charInfo 
{
    public static void main(String[] args) 
    {
        Scanner in = new Scanner (System.in);

        System.out.print("Enter your character's name: ");
        String charName = in.nextLine();
        System.out.print("Enter your character's class: ");
        String charClass = in.nextLine();

        displayInfo(charName, charClass);
        writeToFile(charName, charClass);
    }

    public static void displayInfo(String charName, String charClass)
    {
        System.out.println("Character name: " + charName);
        System.out.println("Class: " + charClass);
    }

    public static void writeToFile(String charName, String charClass) 
    {
        try 
        {
            Map<String, String> sections = CharSheetManager.readCharSheet();
            sections.put("Character Info", "Character name: " + charName + "\nClass: " + charClass);
            CharSheetManager.writeCharSheet(sections);
        } 
        catch (IOException e) 
        {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}
