package ru.sberbank.edu;

import java.io.File;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        String fileForSave = "module02/src/main/resources/text2.txt";
        String fileForRead = "module02/src/main/resources/text.txt";

        FileHandler fileHandler = new FileHandler();
        Statistic statistics = new StatisticsImpl();
        DataStorage storage = new DataStorageFileImpl(fileForSave);
        File file = new File(fileForRead);

        int lineCount = statistics.getLineCount(fileHandler.readFile(file));
        int spaceCount = statistics.getSpaceCount(fileHandler.readFile(file));
        String line = statistics.getLongestLine(fileHandler.readFile(file));

        System.out.println("Количество строк: " + lineCount + " Количество пробелов: " + spaceCount +
                "Самая длинная строка:\n" + line);

        storage.save(lineCount, spaceCount, line);

        storage = new DataStorageBDImpl();
        storage.save(lineCount, spaceCount, line);
    }
}
