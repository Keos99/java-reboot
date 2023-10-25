package ru.sberbank.edu;

import java.io.File;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        FileHandler fileHandler = new FileHandler();
        Statistic statistics = new StatisticsImpl();
        File file = new File("module02/src/main/resources/text.txt");

        System.out.println("Количество строк: " + statistics.getLineCount(fileHandler.readFile(file)));
        System.out.println("Количество пробелов: " + statistics.getSpaceCount(fileHandler.readFile(file)));
        System.out.println("Самая длинная строка:\n" + statistics.getLongestLine(fileHandler.readFile(file)));
    }
}
