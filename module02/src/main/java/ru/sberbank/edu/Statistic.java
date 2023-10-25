package ru.sberbank.edu;

import java.io.Reader;

// интерфейс можно менять
public interface Statistic {

    int getLineCount(Reader reader);
    int getSpaceCount(Reader reader);
    String getLongestLine(Reader reader);
}
