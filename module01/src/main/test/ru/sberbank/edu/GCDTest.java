package ru.sberbank.edu;

import junit.framework.Assert;
import junit.framework.TestCase;

public class GCDTest extends TestCase {

    GCD gcd = new GCD();
    public void testGetDivisor() {
        int preparedGCD = 30;
        int firstNumber = 180;
        int secondNumber = 150;

        Assert.assertEquals(preparedGCD, gcd.getDivisor(firstNumber, secondNumber));
    }
}