package ru.sberbank.edu;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class StatisticsImplTest {

    String fileForRead = "src/test/resources/text.txt";
    FileHandler fileHandler = new FileHandler();
    Statistic statistics = new StatisticsImpl();
    File file = new File(fileForRead);


    @Test
    void getLineCount() {
        int prepLineCount = 11;
        assertEquals(prepLineCount, statistics.getLineCount(fileHandler.readFile(file)));
    }

    @Test
    void getSpaceCount() {
        int prepSpaceCount = 120;
        assertEquals(prepSpaceCount, statistics.getSpaceCount(fileHandler.readFile(file)));
    }

    @Test
    void getLongestLine() {
        String prepLine = "the methods specified by an interface were abstract, containing nobody. This is";
        assertEquals(prepLine, statistics.getLongestLine(fileHandler.readFile(file)));
    }
}