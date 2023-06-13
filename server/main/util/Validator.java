package main.util;

import java.util.Scanner;

public class Validator
{
    public static String getString(String prompt)
    {
	Scanner sc = new Scanner(System.in);
        System.out.print(prompt);
        String s = sc.next();  // read user entry
        sc.nextLine();  // discard any other data entered on the line
        return s;
    }


    public static String getLine(String prompt)
    {
	Scanner sc = new Scanner(System.in);
        System.out.print(prompt);
        String s = sc.nextLine();  // read user entry
        
        return s;
    }


    public static int getInt(String prompt)
    {
	Scanner sc = new Scanner(System.in);
        int i = 0;
        boolean isValid = false;
        while (isValid == false)
        {
            System.out.print(prompt);
            if (sc.hasNextInt())
            {
                i = sc.nextInt();
                isValid = true;
            }
            else
            {
                System.out.println("Error! Invalid integer value. Try again.");
            }
            sc.nextLine();  // discard any other data entered on the line
        }
        return i;
    }

    public static int getInt( String prompt, int min, int max)
    {
	Scanner sc = new Scanner(System.in);
        int i = 0;
        boolean isValid = false;
        while (isValid == false)
        {
            i = getInt(prompt);
            if (i <= min)
                System.out.println(
                    "Error! Number must be greater than " + min + ".");
            else if (i >= max)
                System.out.println(
                    "Error! Number must be less than " + max + ".");
            else
                isValid = true;
        }
        return i;
    }

    public static int getInt( String prompt, int min)
    {
	Scanner sc = new Scanner(System.in);
        int i = 0;
        boolean isValid = false;
        while (isValid == false)
        {
            i = getInt(prompt);
            if (i <= min)
                System.out.println(
                    "Error! Number must be greater than " + min + ".");
            else
                isValid = true;
        }
        return i;
    }

    public static double getDouble( String prompt)
    {
	Scanner sc = new Scanner(System.in);
        double d = 0;
        boolean isValid = false;
        while (isValid == false)
        {
            System.out.print(prompt);
            if (sc.hasNextDouble())
            {
                d = sc.nextDouble();
                isValid = true;
            }
            else
            {
                System.out.println("Error! Invalid decimal value. Try again.");
            }
            sc.nextLine();  // discard any other data entered on the line
        }
        return d;
    }

    public static double getDouble(String prompt, double min, double max)
    {
	Scanner sc = new Scanner(System.in);
        double d = 0;
        boolean isValid = false;
        while (isValid == false)
        {
            d = getDouble(prompt);
            if (d <= min)
                System.out.println(
                    "Error! Number must be greater than " + min + ".");
            else if (d >= max)
                System.out.println(
                    "Error! Number must be less than " + max + ".");
            else
                isValid = true;
        }
        return d;
    }
}
