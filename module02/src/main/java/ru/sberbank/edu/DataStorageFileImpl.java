package ru.sberbank.edu;

import java.io.*;

public class DataStorageFileImpl implements DataStorage {

    private Writer writer;
    private File file;

    public DataStorageFileImpl(String filePath) {
        file = new File(filePath);
    }

    @Override
    public void save(int lineCount, int spaceCount, String line) {
        try (Writer result = new BufferedWriter(new FileWriter(file))) {
            writer = result;
            writer.write("Количество строк: " + lineCount + " Количество пробелов: " + spaceCount +
                    " Самая длинная строка:\n" + line);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
