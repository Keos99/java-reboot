package ru.sberbank.edu;

public class GCD implements CommonDivisor {

    /**
     * Возвращает общий делитель двух чисел. Применяется алгоритм Евклида.
     *
     * @param firstNumber  Первое целочисленное значение (int).
     * @param secondNumber Второе целочисленное значение (int).
     * @return Возвращает общий делитель.
     */
    @Override
    public int getDivisor(int firstNumber, int secondNumber) {
        while (secondNumber != 0) {
            int tmp = firstNumber % secondNumber;
            firstNumber = secondNumber;
            secondNumber = tmp;
        }
        return firstNumber;
    }
}
