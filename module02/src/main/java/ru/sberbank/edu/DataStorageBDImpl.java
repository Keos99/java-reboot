package ru.sberbank.edu;

public class DataStorageBDImpl implements DataStorage {
    @Override
    public void save(int lineCount, int spaceCount, String line) {
        System.out.println("Сохранил в БД!!!");
    }
}
