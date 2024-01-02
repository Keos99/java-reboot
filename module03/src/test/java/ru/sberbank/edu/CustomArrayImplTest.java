package ru.sberbank.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class CustomArrayImplTest {

    CustomArrayImpl<String> myArray;
    CustomArrayImpl<String> emptyArray;
    ArrayList<String> testCollection;

    @BeforeEach
    void init(){
        emptyArray = new CustomArrayImpl<>();

        myArray = new CustomArrayImpl<>();
        myArray.add("One");
        myArray.add("Two");
        myArray.add("Three");
        myArray.add("Four");
        myArray.add("Five");
        myArray.add("Six");
        myArray.add("Seven");
        myArray.add("Eight");
        myArray.add("Nine");
        myArray.add("Ten");

        testCollection = new ArrayList<>();
        testCollection.add("One");
        testCollection.add("Two");
        testCollection.add("Three");
        testCollection.add("Four");
        testCollection.add("Five");
    }
    @Test
    void size() {
        assertEquals(10, myArray.size());
    }

    @Test
    void isEmpty() {
        assertTrue(emptyArray.isEmpty());
        assertFalse(myArray.isEmpty());
    }

    @Test
    void add() {
        assertTrue(myArray.add("Eleven"));
        assertEquals(11, myArray.size());
        assertFalse(myArray.add(null));
    }

    @Test
    public void testAddAll_nullArray() {
        String[] items = null;

        assertThrows(IllegalArgumentException.class, () -> myArray.addAll(items));
    }

    @Test
    public void testAddAll_emptyArray() {
        String[] items = new String[]{};

        assertFalse(myArray.addAll(items));
        assertEquals(10, myArray.size());
    }

    @Test
    public void testAddAll_nonEmptyArray() {
        String[] items = new String[]{"a", "b", "c"};

        assertTrue(myArray.addAll(items));
        assertEquals(13, myArray.size());
        assertEquals("a", myArray.get(10));
        assertEquals("b", myArray.get(11));
        assertEquals("c", myArray.get(12));
    }

    @Test
    public void testAddAll_nullCollection() {
        Collection<String> items = null;
        assertThrows(IllegalArgumentException.class, () -> myArray.addAll(items));
    }

    @Test
    public void testAddAll_emptyCollection() {
        Collection<String> items = new ArrayList<>();
        assertFalse(myArray.addAll(items));
        assertEquals(10, myArray.size());
    }

    @Test
    public void testAddAll_nonEmptyCollection() {

        boolean wasChanged = myArray.addAll(testCollection);

        assertTrue(wasChanged);
        assertEquals(15, myArray.size());
        assertEquals("One", myArray.get(10));
        assertEquals("Two", myArray.get(11));
        assertEquals("Three", myArray.get(12));
    }

    @Test
    public void testAddAll_index_nullArray() {
        String[] items = null;
        int index = 0;

        assertThrows(IllegalArgumentException.class, () -> myArray.addAll(index, items));
    }

    @Test
    public void testAddAll_index_emptyArray() {
        String[] items = new String[]{};
        int index = 0;

        assertFalse(myArray.addAll(index, items));
        assertEquals(10, myArray.size());
    }

    @Test
    public void testAddAll_index_nonEmptyArray() {
        String[] items = new String[]{"a", "b", "c"};
        int index = 7;

        boolean wasChanged = myArray.addAll(index, items);

        assertTrue(wasChanged);
        assertEquals("a", myArray.get(7));
        assertEquals("b", myArray.get(8));
        assertEquals("c", myArray.get(9));
    }

    @Test
    public void testAddAll_index_outOfBounds() {
        String[] items = new String[]{"a", "b", "c"};
        int index = 50;
        assertThrows(IndexOutOfBoundsException.class, () -> myArray.addAll(index, items));
    }

    @Test
    void get() {
        assertEquals("One", myArray.get(0));
        assertEquals("Two", myArray.get(1));
        assertEquals("Three", myArray.get(2));
    }

    @Test
    void get_index_outOfBound() {
        assertThrows(IndexOutOfBoundsException.class, () -> myArray.get(100));
    }

    @Test
    public void testSet_indexOutOfRange() {
        String item = "a";
        int index = myArray.getCapacity() + 10;

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> myArray.set(index, item));
    }

    @Test
    public void testSet_returnsOldElement() {
        String oldElement = myArray.set(0, "b");
        assertEquals("One", oldElement);
    }

    @Test
    public void testSet_replacesElement() {
        myArray.set(0, "b");
        assertEquals("b", myArray.get(0));
    }

    @Test
    void remove() {
        myArray.remove(5);
        assertEquals(9, myArray.size());
    }

    @Test
    void testRemove_Obj_return_true() {
        assertTrue(myArray.remove("Six"));
    }

    @Test
    void testRemove_Obj_return_false() {
        assertFalse(myArray.remove("Net"));
    }

    @Test
    void testContains_Obj_return_true() {
        assertTrue(myArray.contains("Six"));
    }

    @Test
    void testContains_Obj_return_false() {
        assertFalse(myArray.contains("Net"));
    }

    @Test
    void indexOf() {
        assertEquals(4, myArray.indexOf("Five"));
    }

    @Test
    void ensureCapacity() {
        myArray.ensureCapacity(19);
        assertEquals(19, myArray.getCapacity());
    }

    @Test
    void getCapacity() {
        assertEquals(16, myArray.getCapacity());
    }

    @Test
    void reverse() {
        myArray.reverse();
        assertEquals("Ten", myArray.get(0));
        assertEquals("One", myArray.get(9));
    }

    @Test
    void toArray() {
        Object[] objects = {"One", "Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten"};
        Object[] myObjects = myArray.toArray();

        for (int i = 0; i < objects.length; i++) {
            assertEquals(objects[i], myObjects[i]);
        }
    }
}