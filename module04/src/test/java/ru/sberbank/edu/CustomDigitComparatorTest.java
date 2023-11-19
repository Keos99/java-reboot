package ru.sberbank.edu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomDigitComparatorTest {

    private final CustomDigitComparator comparator = new CustomDigitComparator();

    @Test
    public void shouldCompareEvenNumbers() {
        assertEquals(0, comparator.compare(2, 2));
        assertEquals(-2, comparator.compare(2, 4));
        assertEquals(2, comparator.compare(4, 2));
    }

    @Test
    public void shouldCompareOddNumbers() {
        assertEquals(0, comparator.compare(1, 1));
        assertEquals(-2, comparator.compare(1, 3));
        assertEquals(2, comparator.compare(3, 1));
    }

    @Test
    public void shouldPreferEvenNumbers() {
        assertEquals(-1, comparator.compare(2, 1));
        assertEquals(-1, comparator.compare(4, 3));
        assertEquals(-1, comparator.compare(6, 5));
    }

    @Test
    public void shouldThrowNullPointerException() {
        assertThrows(NullPointerException.class, () -> comparator.compare(null, null));
    }
}