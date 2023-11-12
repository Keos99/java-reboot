package ru.sberbank.edu;

import java.util.Objects;

public class Person implements Comparable<Person> {
    private String name;
    private String city;
    private int age;

    public Person(String name, String city, int age) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return name.equalsIgnoreCase(person.name) && Objects.equals(age, person.age) &&
                city.equalsIgnoreCase(person.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase(), age, city.toLowerCase());
    }

    @Override
    public int compareTo(Person o) {
        if (o == null || o.city == null || o.name == null) throw new NullPointerException();

        int result = city.compareTo(o.city);
        if (result == 0) return name.compareTo(o.name);

        return result;
    }
}
