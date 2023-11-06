package ru.sberbank.edu;

public class DataStorageBDImpl implements DataStorage {
    @Override
    public void save(int lineCount, int spaceCount, String line) {
        System.out.printf("Сохранил в БД!!!\nКоличество строк: %s\nКоличество пробелов: %s\nСамая длинная строка:\n%s",
                lineCount, spaceCount, line);
    }
}
