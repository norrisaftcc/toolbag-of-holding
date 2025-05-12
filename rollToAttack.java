//import java.util.Random;
import java.util.Scanner;

public class rollToAttack 
{
    public static void main(String[] args) 
    {
        //Scanner in = new Scanner(System.in);

        //calling methods
        intro();
        rollAttack();
        //rollAgain();
    }
    
    public static void intro()
    {
        //welcomes player, explains how to use the program
        System.out.println("Welcome to combat!");
        System.out.println("You'll roll for attack and add your modifiers, and a proficiency bonus if applicable.");
        System.out.println();
    }

    public static void rollAttack()
    {
        Scanner in = new Scanner(System.in);
        String hit = "y";

        //gather necessary information from player
        System.out.println("What is your skill modifier? ");
        int modifier = in.nextInt();
        System.out.println();

        System.out.println("What is your proficiency bonus? (enter 0 if not applicable) ");
        int profBonus = in.nextInt();
        System.out.println();

        //rolls d20 for attack
        final int SIDES = 20;
        int roll, rollTotal = 0;

        roll = (int)(Math.random() * SIDES) + 1;
        System.out.println(roll);
        rollTotal += roll;

        int total = rollTotal + modifier + profBonus;
        System.out.println(rollTotal + " + " + modifier + " + " + profBonus);
        System.out.println ("You rolled " + total);

        System.out.println("Did you hit? y/n: ");
        hit = in.next();

        if (hit.equalsIgnoreCase("y"))
        {
            System.out.println("Roll for damage!");

            System.out.println("What die do you want to roll? (ex. d12) ");
            String sidesOfDice = in.next();
            System.out.println();

            System.out.println("How many dice do you want to roll? ");
            int numOfDice = in.nextInt();
            System.out.println();
            
            getDice(sidesOfDice, numOfDice);
        }
        else if (!hit.equalsIgnoreCase("y"))
        {
            System.out.println("Better luck next time!");
        }

        in.close();
    }

    public static void getDice(String sidesOfDice, int numOfDice)
    {
        //determines which method to call based on player input
        switch (sidesOfDice)
        {
            case "d12":
                d12(numOfDice);
                break;
            case "d10":
                d10(numOfDice);
                break;
            case "d8":
                d8(numOfDice);
                break;
            case "d6":
                d6(numOfDice);
                break;
            case "d4":
                d4(numOfDice);
                break;
            default:
                System.out.println("Invalid input. Please try again.");
                getDice(sidesOfDice, numOfDice);
        }
    }

    public static void d12 (int numOfDice)
    {
        final int SIDES = 12;
        int roll, i, rollTotal = 0;

        for (i = 0; i < numOfDice; i++)
        {
            roll = (int)(Math.random() * SIDES) + 1;
            System.out.println(roll);
            rollTotal += roll;
        }

        System.out.println ("You rolled " + rollTotal);
        rollAgain();
    }

    public static void d10 (int numOfDice)
    {
        final int SIDES = 10;
        int roll, i, rollTotal = 0;

        for (i = 0; i < numOfDice; i++)
        {
            roll = (int)(Math.random() * SIDES) + 1;
            System.out.println(roll);
            rollTotal += roll;
        }

        System.out.println ("You rolled " + rollTotal);
        rollAgain();
    }

    public static void d8 (int numOfDice)
    {
        final int SIDES = 8;
        int roll, i, rollTotal = 0;

        for (i = 0; i < numOfDice; i++)
        {
            roll = (int)(Math.random() * SIDES) + 1;
            System.out.println(roll);
            rollTotal += roll;
        }

        System.out.println ("You rolled " + rollTotal);
        rollAgain();
    }

    public static void d6 (int numOfDice)
    {
        final int SIDES = 6;
        int roll, i, rollTotal = 0;

        for (i = 0; i < numOfDice; i++)
        {
            roll = (int)(Math.random() * SIDES) + 1;
            System.out.println(roll);
            rollTotal += roll;
        }

        System.out.println ("You rolled " + rollTotal);
        rollAgain();
    }

    public static void d4 (int numOfDice)
    {
        final int SIDES = 4;
        int roll, i, rollTotal = 0;

        for (i = 0; i < numOfDice; i++)
        {
            roll = (int)(Math.random() * SIDES) + 1;
            System.out.println(roll);
            rollTotal += roll;
        }

        System.out.println ("You rolled " + rollTotal);
        rollAgain();
    }

    public static void rollAgain()
    {
        Scanner in = new Scanner(System.in);

        System.out.println("Would you like to roll again? (yes/no) ");
        String answer = in.nextLine();

        if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y"))
        {
            System.out.println();
            rollAttack();
        }
        else if (answer.equalsIgnoreCase("no") || answer.equalsIgnoreCase("n"))
        {
            System.out.println("Thank you for using the dice roller!");
        }
        else
        {
            System.out.println("Invalid input. Please try again.");
            rollAgain();
        }

        in.close();
    }
}