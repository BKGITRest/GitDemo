package stepDefinitions;

import static stepDefinitions.ReusableMethods.validateHeader;

public class BasicsJava {

    public static void main(String[] args) {

        System.out.println("I Started The Java Learning");

        //Add Two Numbers
        int a = 3;
        int b = 5;
        int sum = a + b;

        System.out.println("The Sum Of the Values :"+ sum);

        //Return Type
        System.out.println(validateHeader());
    }
}
