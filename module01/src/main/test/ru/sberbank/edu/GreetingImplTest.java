package ru.sberbank.edu;

import junit.framework.Assert;
import junit.framework.TestCase;

public class GreetingImplTest extends TestCase {

    GreetingImpl greeting = new GreetingImpl();
    public void testGetBestHobby() {
        String preparedBestHobby = "Programming";

        Assert.assertEquals(preparedBestHobby, greeting.getBestHobby());
    }
}