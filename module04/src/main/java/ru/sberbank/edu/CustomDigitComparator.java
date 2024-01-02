package ru.sberbank.edu;

import java.util.Comparator;

/**
 * Класс сравнения Integer накладывающий порядок сравнения.
 * Порядок сравнения:
 * - сначала сравниваются четные числа
 * - после сравниваются нечетные числа
 */
public class CustomDigitComparator implements Comparator<Integer> {

    /**
     * Метод сравнивает два Integer и накладывает порядок сравнения.
     * Порядок сравнения:
     * - сначала сравниваются четные числа.
     * - после сравниваются нечетные числа.
     *
     * @param o1 первый объект для сравнения.
     * @param o2 второй объект для сравнения..
     * @return
     * @throws NullPointerException если один из входяших объектов равен null.
     */
    @Override
    public int compare(Integer o1, Integer o2) {
        if (o1 == null && o2 == null) throw new NullPointerException();

        if (o1 % 2 == 0 && o2 % 2 == 0) {
            return o1 - o2;
        } else if (o1 % 2 != 0 && o2 % 2 != 0) {
            return o1 - o2;
        } else {
            return -1;
        }
    }
}
