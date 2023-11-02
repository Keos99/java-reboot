package ru.sberbank.edu;

import java.util.Arrays;
import java.util.Collection;

public class CustomArrayImpl<T> implements CustomArray<T> {

    private int startСapacity = 8;
    private Object[] elements;
    private int size = 0;

    /**
     * Конструктор для иниуиализации пустого массива.
     */
    public CustomArrayImpl() {
        elements = new Object[startСapacity];
    }

    /**
     * Конструктор для инициализации массива с заданным размером.
     *
     * @param startSize размер массива.
     */
    public CustomArrayImpl(int startSize) {
        int size = startSize;
        if (startSize <= 0) size = startСapacity;
        elements = new Object[startSize];
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
     * Добавить один элемент.
     *
     * @param item элемент для добавления
     */
    @Override
    public boolean add(T item) {
        if (item == null) return false;

        if (size + 1 == getCapacity()) {
            ensureCapacity(getCapacity() * 2);
        }
        elements[size++] = item;
        return true;
    }

    /**
     * Добавить все элементы из массива.
     *
     * @param items массив элементов
     * @throws IllegalArgumentException if parameter items is null
     */
    @Override
    public boolean addAll(T[] items) {
        if (items == null) {
            throw new IllegalArgumentException();
        }

        if (items.length == 0) {
            return false;
        } else {
            for (T item : items) {
                add(item);
            }
            return true;
        }
    }

    /**
     * Add all items.
     *
     * @param items
     * @throws IllegalArgumentException if parameter items is null
     */
    @Override
    public boolean addAll(Collection<T> items) {
        if (items == null) {
            throw new IllegalArgumentException();
        }
        Object[] itemsArray = items.toArray();

        return addAll((T[]) itemsArray);
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
        if (items == null) {
            throw new IllegalArgumentException();
        }

        if (index + items.length > size) {
            throw new ArrayIndexOutOfBoundsException();
        }

        int tempIndex = index;

        if (items.length == 0) {
            return false;
        } else {
            for (T item : items) {
                set(tempIndex++, item);
            }
            return true;
        }
    }

    /**
     * Get item by index.
     *
     * @param index - index
     * @throws ArrayIndexOutOfBoundsException if index is out of bounds
     */
    @Override
    public T get(int index) {
        if (index > getCapacity() - 1) throw new ArrayIndexOutOfBoundsException();
        return (T) elements[index];
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
        if (index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }

        T oldElement = elementsData(index);
        elements[index] = item;
        return oldElement;
    }

    private T elementsData(int index) {
        return (T) elements[index];
    }

    /**
     * Remove item by index.
     *
     * @param index - index
     * @throws ArrayIndexOutOfBoundsException if index is out of bounds
     */
    @Override
    public void remove(int index) {
        if (index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }

        for (int i = index; i < size; i++) {
            elements[index] = elements[index + 1];
        }
        size--;
    }

    /**
     * Remove item by value. Remove first item occurrence.
     *
     * @param item - item
     * @return true if item was removed
     */
    @Override
    public boolean remove(T item) {
        if (contains(item)) {
            remove(indexOf(item));
            return true;
        }
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
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(item)) {
                return true;
            }
        }
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
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Grow current capacity to store new elements if needed.
     *
     * @param newElementsCount - new elements count
     */
    @Override
    public void ensureCapacity(int newElementsCount) {
        int elementsCount = newElementsCount;
        if (newElementsCount <= 0) elementsCount = 1;
        elements = Arrays.copyOf(elements, elementsCount);
    }

    /**
     * Get current capacity.
     */
    @Override
    public int getCapacity() {
        return elements.length;
    }

    /**
     * Reverse list.
     */
    @Override
    public void reverse() {
        Object[] objects = new Object[elements.length];
        int j = 0;
        for (int i = size() - 1; i >= 0; i--) {
            objects[j] = elements[i];
            j++;
        }
        elements = objects;
    }

    /**
     * Get copy of current array.
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size());
    }
}
