package ru.sberbank.edu;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.Reader;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {

    @Test
    void readFile() {
        String fileForSave = "src/test/resources/text.txt";
        File file = new File(fileForSave);
        Reader reader = new FileHandler().readFile(file);
        assertNotNull(reader);
    }
}