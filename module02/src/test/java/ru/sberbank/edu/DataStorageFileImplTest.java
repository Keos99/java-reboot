package ru.sberbank.edu;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DataStorageFileImplTest {

    @Test
    void save() throws IOException {
        String fileForSave = "src/test/resources/text2.txt";
        String expectedData = "Количество строк: 1 Количество пробелов: 2 Самая длинная строка:\nЭто строка";

        File file = new File(fileForSave);
        DataStorageFileImpl storage = new DataStorageFileImpl(fileForSave);
        storage.save(1, 2, "Это строка");
        String actualData = FileUtils.readFileToString(file, "UTF-8");
        assertEquals(expectedData, actualData);
        file.delete();
    }
}