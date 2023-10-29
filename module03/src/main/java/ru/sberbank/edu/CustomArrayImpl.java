package ru.sberbank.edu;

import java.util.Collection;

public class CustomArrayImpl<T> implements CustomArray<T> {

    private int startSize = 8;
    private Object[] elements;
    private int size;
    private int capacity;

    /**
     * Конструктор для иниуиализации пустого массива.
     */
    public CustomArrayImpl() {
        elements = new Object[startSize];
        capacity = startSize;
    }

    /**
     * Конструктор для инициализации массива с заданным размером.
     *
     * @param startSize размер массива.
     */
    public CustomArrayImpl(int startSize) {
        elements = new Object[startSize];
        capacity = startSize;
    }

    /**
     * Размер массива.
     *
     * @return количество элементов в массиве.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Проверка пустой массив или нет.
     *
     * @return true если size == 0, в остальных случаях false
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Add single item.
     *
     * @param item
     */
    @Override
    public boolean add(T item) {
        if (size + 1 == capacity) {
            capacity *= 2;
            ensureCapacity(capacity);
        }
        elements[size++] = item;
        return false;
    }

    /**
     * Add all items.
     *
     * @param items
     * @throws IllegalArgumentException if parameter items is null
     */
    @Override
    public boolean addAll(T[] items) {
        return false;
    }

    /**
     * Add all items.
     *
     * @param items
     * @throws IllegalArgumentException if parameter items is null
     */
    @Override
    public boolean addAll(Collection<T> items) {
        return false;
    }

    /**
     * Add items to current place in array.
     *
     * @param index - index
     * @param items - items for insert
     * @throws ArrayIndexOutOfBoundsException if index is out of bounds
     * @throws IllegalArgumentException       if parameter items is null
     */
    @Override
    public boolean addAll(int index, T[] items) {
        return false;
    }

    /**
     * Get item by index.
     *
     * @param index - index
     * @throws ArrayIndexOutOfBoundsException if index is out of bounds
     */
    @Override
    public T get(int index) {
        return null;
    }

    /**
     * Set item by index.
     *
     * @param index - index
     * @param item
     * @return old value
     * @throws ArrayIndexOutOfBoundsException if index is out of bounds
     */
    @Override
    public T set(int index, T item) {
        return null;
    }

    /**
     * Remove item by index.
     *
     * @param index - index
     * @throws ArrayIndexOutOfBoundsException if index is out of bounds
     */
    @Override
    public void remove(int index) {

    }

    /**
     * Remove item by value. Remove first item occurrence.
     *
     * @param item - item
     * @return true if item was removed
     */
    @Override
    public boolean remove(T item) {
        return false;
    }

    /**
     * Checks if item exists.
     *
     * @param item - item
     * @return true or false
     */
    @Override
    public boolean contains(T item) {
        return false;
    }

    /**
     * Index of item.
     *
     * @param item - item
     * @return index of element or -1 of list doesn't contain element
     */
    @Override
    public int indexOf(T item) {
        return 0;
    }

    /**
     * Grow current capacity to store new elements if needed.
     *
     * @param newElementsCount - new elements count
     */
    @Override
    public void ensureCapacity(int newElementsCount) {

    }

    /**
     * Get current capacity.
     */
    @Override
    public int getCapacity() {
        return capacity;
    }

    /**
     * Reverse list.
     */
    @Override
    public void reverse() {

    }

    /**
     * Get copy of current array.
     */
    @Override
    public Object[] toArray() {
        return new Object[0];
    }
}
