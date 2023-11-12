package ru.sberbank.edu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    public void shouldReturnCorrectStringRepresentation() {
        Person person = new Person("John Doe", "Moscow", 30);

        String expectedStringRepresentation = "Person{name='John Doe', city='Moscow', age=30}";

        assertEquals(expectedStringRepresentation, person.toString());
    }

    @Test
    public void shouldReturnTrueForEqualObjects() {
        Person person1 = new Person("John Doe", "Moscow", 30);
        Person person2 = new Person("John Doe", "Moscow", 30);

        assertEquals(true, person1.equals(person2));
    }

    @Test
    public void shouldReturnFalseForNullObject() {
        Person person = new Person("John Doe", "Moscow", 30);

        assertEquals(false, person.equals(null));
    }

    @Test
    public void shouldReturnFalseForObjectsOfDifferentClasses() {
        Person person = new Person("John Doe", "Moscow", 30);
        String string = "Hello";

        assertEquals(false, person.equals(string));
    }

    @Test
    public void shouldReturnFalseForObjectsWithDifferentNames() {
        Person person1 = new Person("John Doe", "Moscow", 30);
        Person person2 = new Person("Jane Doe", "Moscow", 30);

        assertEquals(false, person1.equals(person2));
    }

    @Test
    public void shouldReturnFalseForObjectsWithDifferentAges() {
        Person person1 = new Person("John Doe", "Moscow", 30);
        Person person2 = new Person("John Doe", "Moscow", 35);

        assertEquals(false, person1.equals(person2));
    }

    @Test
    public void shouldReturnFalseForObjectsWithDifferentCities() {
        Person person1 = new Person("John Doe", "Moscow", 30);
        Person person2 = new Person("John Doe", "Saint Petersburg", 30);

        assertEquals(false, person1.equals(person2));
    }

    @Test
    public void shouldReturnTrueForObjectsWithEqualNamesIgnoringCase() {
        Person person1 = new Person("John Doe", "Moscow", 30);
        Person person2 = new Person("JOHN DOE", "MOSCOW", 30);

        assertEquals(true, person1.equals(person2));
    }

    @Test
    public void shouldReturnEqualHashCodesForEqualObjects() {
        Person person1 = new Person("John Doe", "Moscow", 30);
        Person person2 = new Person("John Doe", "Moscow", 30);

        assertEquals(person1.hashCode(), person2.hashCode());
    }

    @Test
    public void shouldReturnDifferentHashCodesForDifferentObjects() {
        Person person1 = new Person("John Doe", "Moscow", 30);
        Person person2 = new Person("Jane Doe", "Saint Petersburg", 25);

        assertNotEquals(person1.hashCode(), person2.hashCode());
    }

    @Test
    public void shouldReturnDifferentHashCodesForObjectsWithDifferentNames() {
        Person person1 = new Person("John Doe", "Moscow", 30);
        Person person2 = new Person("JANE DOE", "MOSCOW", 30);

        assertNotEquals(person1.hashCode(), person2.hashCode());
    }

    @Test
    public void shouldReturnDifferentHashCodesForObjectsWithDifferentAges() {
        Person person1 = new Person("John Doe", "Moscow", 30);
        Person person2 = new Person("John Doe", "Moscow", 35);

        assertNotEquals(person1.hashCode(), person2.hashCode());
    }

    @Test
    public void shouldReturnDifferentHashCodesForObjectsWithDifferentCities() {
        Person person1 = new Person("John Doe", "Moscow", 30);
        Person person2 = new Person("John Doe", "Saint Petersburg", 30);

        assertNotEquals(person1.hashCode(), person2.hashCode());
    }

    @Test
    public void shouldThrowNullPointerExceptionIfOtherPersonIsNull() {
        Person person = new Person("John Doe", "Moscow", 30);

        assertThrows(NullPointerException.class, () -> person.compareTo(null));
    }

    @Test
    public void shouldThrowNullPointerExceptionIfOtherPersonCityIsNull() {
        Person person = new Person("John Doe", "Moscow", 30);
        Person otherPerson = new Person("John Doe", null, 30);

        assertThrows(NullPointerException.class, () -> person.compareTo(otherPerson));
    }

    @Test
    public void shouldThrowNullPointerExceptionIfOtherPersonNameIsNull() {
        Person person = new Person("John Doe", "Moscow", 30);
        Person otherPerson = new Person(null, "Moscow", 30);

        assertThrows(NullPointerException.class, () -> person.compareTo(otherPerson));
    }

    @Test
    public void shouldCompareByCityFirst() {
        Person person1 = new Person("John Doe", "Moscow", 30);
        Person person2 = new Person("John Doe", "Saint Petersburg", 30);

        assertTrue(person1.compareTo(person2) < 0);
    }

    @Test
    public void shouldCompareByNameIfCitiesAreEqual() {
        Person person1 = new Person("John Doe", "Moscow", 30);
        Person person2 = new Person("Jane Doe", "Moscow", 30);

        assertTrue(person1.compareTo(person2) > 0);
    }
}