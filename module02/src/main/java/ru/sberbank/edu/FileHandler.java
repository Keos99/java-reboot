package ru.sberbank.edu;

import java.io.*;

/**
 *  Класс для работы с файлами
 */

public class FileHandler {
    private Reader input;

    /**
     * Чтение файла из файловой системы.
     * @param file название файла или путь к файлу
     */

    public Reader readFile(File file){
        try {
            input = new BufferedReader(new java.io.FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return input;
    }


}
