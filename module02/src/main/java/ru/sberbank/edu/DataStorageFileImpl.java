package ru.sberbank.edu;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DataStorageFileImpl implements DataStorage {

    private BufferedWriter writer;
    private File file;

    public DataStorageFileImpl(String filePath) {
        file = new File(filePath);
    }

    @Override
    public void save(int lineCount, int spaceCount, String line) {
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write("Количество строк: " + lineCount + " Количество пробелов: " + spaceCount +
                    " Самая длинная строка:\n" + line);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
