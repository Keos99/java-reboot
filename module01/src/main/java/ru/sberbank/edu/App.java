package ru.sberbank.edu;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        GreetingImpl greeting = new GreetingImpl();
        GCD gcd = new GCD();

        int firstNumber = 180;
        int secondNumber = 150;

        System.out.println( "Hello World!" );
        System.out.printf("Best hobby is %s\n", greeting.getBestHobby());
        System.out.printf("GCD of %s and %s is %s", firstNumber, secondNumber,
                gcd.getDivisor(firstNumber, secondNumber));
    }
}
