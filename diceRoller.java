//import java.util.Random;
import java.util.Scanner;

public class diceRoller 
{
    public static void main(String[] args) 
    {
        //Scanner in = new Scanner(System.in);

        //calling methods
        intro();
        getDice();
    }
    
    public static void intro()
    {
        //welcomes player, explains how to use the program
        System.out.println("Welcome to the dice roller!");
        System.out.println("You can roll a d100 (percentile), d20, d12, d10, d8, d6, or d4.");
        System.out.println("You can also roll multiple dice at once, and you can add a modifier to your d20 roll.");
        System.out.println("Good luck! May the dice be in your favour!");
        System.out.println();
    }

    public static void getDice()
    {
        int total = 0, modifier = 0;
        Scanner in = new Scanner(System.in);

        //gather necessary information from player
        System.out.println("What die do you want to roll? (ex. d20)");
        System.out.print("d");
        int sidesOfDice = in.nextInt();
        System.out.println();

        System.out.println("How many dice do you want to roll? ");
        int numOfDice = in.nextInt();
        System.out.println();

        if (sidesOfDice == 20)
        {
            System.out.println("What is your modifier? (ex. 3, -1) ");
            modifier = in.nextInt();
            System.out.println();
        }

        //determines which method to call based on player input
        switch (sidesOfDice)
        {
            case 100:
                d100(numOfDice, total);
                break;
            case 20:
                d20(numOfDice, modifier, total);
                break;
            case 12:
                d12(numOfDice, total);
                break;
            case 10:
                d10(numOfDice, total);
                break;
            case 8:
                d8(numOfDice, total);
                break;
            case 6:
                d6(numOfDice, total);
                break;
            case 4:
                d4(numOfDice, total);
                break;
            default:
                System.out.println("Invalid input. Please try again.");
                getDice();
        }

        in.close();
    }

    public static void d100 (int numOfDice, int total)
    {
        final int SIDES = 100;
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

    public static void d20 (int numOfDice, int modifier, int total)
    {
        final int SIDES = 20;
        int roll, i, rollTotal = 0;

        for (i = 0; i < numOfDice; i++)
        {
            roll = (int)(Math.random() * SIDES) + 1;
            System.out.println(roll);
            rollTotal += roll;
        }

        total = rollTotal + modifier;
        System.out.println ("You rolled " + total);
        rollAgain();
    }

    public static void d12 (int numOfDice, int total)
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

    public static void d10 (int numOfDice, int total)
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

    public static void d8 (int numOfDice, int total)
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

    public static void d6 (int numOfDice, int total)
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

    public static void d4 (int numOfDice, int total)
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
            getDice();
        }
        else if (answer.equalsIgnoreCase("no") || answer.equalsIgnoreCase("n"))
        {
            System.out.println("Thank you for using the dice roller!");
            dndToolbag.playMode(in);
        }
        else
        {
            System.out.println("Invalid input. Please try again.");
            rollAgain();
        }
        in.close();
    }
}