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

    }
}


