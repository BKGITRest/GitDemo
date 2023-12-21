package stepDefinitions;

import groovy.xml.StreamingDOMBuilder;

import static stepDefinitions.ReusableMethods.validateHeader;

public class BasicsJava {

    public static void main(String[] args) {

        System.out.println("I Started The Java Learning");

        //Add Two Numbers
        int a = 3;
        int b = 5;
        int sum = a + b;

        System.out.println("The Sum Of the Values :" + sum);

        //Return Type
        System.out.println(validateHeader());

        //String Types
        String str = "Payment Is Done";
        System.out.println("The char at repective index is :" + str.charAt(8));
        System.out.println("The index of specific char :" + str.indexOf("D"));
        System.out.println("The substring is :" + str.substring(8));

        //Print the String in Reverse
        String s = "malayalam";
        String t = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            t = t + s.charAt(i);
        }
        System.out.println(t);

        if (s == t) {
            System.out.println("The Given String Is palindrome");
        }


        // Array is a container which stores mutiple values of same data type.
        //Here in array memory is allocated first and storing the values in an array upto the desired allocated memory.

        int z[] = new int[5];
        z[0] = 1;
        z[1] = 4;
        z[2] = 6;
        z[3] = 8;
        z[4] = 9;

        //storing the values in a array dynamically
        int c[] = {3, 4, 4, 5, 7, 6};

        for (int i = 0; i < c.length; i++) {
            System.out.println(c[i]);

        }
        System.out.println("------------------------------------------");

        //MultiDimensional Array
        int e[][] = new int[2][3];
        e[0][0] = 8;
        e[0][1] = 3;
        e[0][2] = 9;
        e[1][0] = 6;
        e[1][1] = 5;
        e[1][2] = 4;

        System.out.println("2-D Array: ");

        System.out.println(" ");

// printing a 2-D array using two nested loops
        for (int[] array : e) {
            System.out.print("[");
            for (int n : array) {
                System.out.print(n + " "); // printing each item
            }
            System.out.print("]"); // printing new line
        }
//        System.out.println("]\n");

        System.out.println("\n");

        System.out.println("------------------------------------------");

        // 3-D array
        int[][][] multiDimArray = {
                {
                        {0, 1}, {2, 3}, {3, 4}
                },
                {
                        {5, 6}, {7, 8}, {8, 9}
                }};

        System.out.println("3-D Array: ");
        // printing a 3-D array using three nested loops

        System.out.println(" ");

        for (int[][] m : multiDimArray) {
            System.out.print("[");
            for (int[] f : m) {
                System.out.print("[");
                for (int n : f) {
                    System.out.print(n + " "); // printing each item
                }
                System.out.print("]");
            }
            System.out.print("]");
        }
    }
}




