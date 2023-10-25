package ru.sberbank.edu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class StatisticsImpl implements Statistic {

    /**
     * Подсчитывает количество строк в переданном файле.
     *
     * @param reader прочитанный файл
     * @return количество строк в файле
     */
    @Override
    public int getLineCount(Reader reader) {
        try (BufferedReader bufferedReader = (BufferedReader) reader) {
            int countLines = 0;
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                countLines++;
            }
            return countLines;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Подсчитывает количество пробелов в переданном файле.
     *
     * @param reader прочитанный файл
     * @return количество пробелов в файле.
     */
    @Override
    public int getSpaceCount(Reader reader) {
        int whitespace = ' ';
        try (BufferedReader bufferedReader = (BufferedReader) reader) {
            int countSpaces = 0;
            int byteChar;
            while ((byteChar = bufferedReader.read()) != -1) {
                if ((byteChar) == whitespace) {
                    countSpaces++;
                }
            }
            return countSpaces;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Поиск самой длинной строки.
     * @param reader прочитанный файл
     * @return возвращает самую длинную строку.
     */
    @Override
    public String getLongestLine(Reader reader) {
        try (BufferedReader bufferedReader = (BufferedReader) reader) {
            String longestLine = "";
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.length() > longestLine.length()) {
                    longestLine = line;
                }
            }
            return longestLine;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
