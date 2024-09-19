package telran.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.jupiter.api.Test;

//{3, -10, 20, 1, 10, 8, 100 , 17}
public abstract class SortedSetTest extends SetTest {
    SortedSet<Integer> sortedSet;

    @Override
    void setUp() {
        super.setUp();
        sortedSet = (SortedSet<Integer>) collection;

    }

    @Test
    void floorTest() {
        assertEquals(10, sortedSet.floor(10));
        assertNull(sortedSet.floor(-11));
        assertEquals(10, sortedSet.floor(11));
        assertEquals(100, sortedSet.floor(101));
    }

    @Test
    void ceilingTest() {
        assertEquals(10, sortedSet.ceiling(10));
        assertNull(sortedSet.ceiling(101));
        assertEquals(17, sortedSet.ceiling(11));
        assertEquals(-10, sortedSet.ceiling(-11));
    }

    @Test
    void firstTest() {
        assertEquals(-10, sortedSet.first());
        sortedSet.clear();
        assertThrowsExactly(NoSuchElementException.class,
        () -> sortedSet.first());
    }

    @Test
    void lastTest() {
        assertEquals(100, sortedSet.last());
    }

    @Test
    void subSetTest() {
        Integer[] expected = { 10, 17 };
        Integer[] actual = getActualSubSet(10, 20);
        assertArrayEquals(expected, actual);
        actual = getActualSubSet(9, 18);
        assertArrayEquals(expected, actual);
        actual = getActualSubSet(100, 100);
        assertEquals(0, actual.length);
        assertThrowsExactly(IllegalArgumentException.class,
         ()->sortedSet.subSet(10, 5));
       

    }

    private Integer[] getActualSubSet(int keyFrom, int keyTo) {
        return sortedSet.subSet(keyFrom, keyTo).stream().toArray(Integer[]::new);
    }
    @Override
    protected void fillBigCollection(){
        Integer[] array = getBigArrayCW();
        Arrays.stream(array).forEach(collection::add);
    }

    protected Integer[] getBigArrayCW() {
       return new Random().ints().distinct().limit(N_ELEMENTS).boxed().toArray(Integer[]::new);

    }

    protected Integer[] getBigArrayHW() {
        Integer[] sortedArray = Arrays.stream(getBigArrayCW()).sorted().toArray(Integer[]::new);
        java.util.ArrayList<Integer> list = new java.util.ArrayList<>();
        addBalance(list, 0, sortedArray.length - 1, sortedArray);
        return list.toArray(new Integer[0]);
    }

    private void addBalance(java.util.ArrayList<Integer> list, Integer left, Integer right, Integer[] sortedArray) {
        if(left <= right) {
            Integer middle = (left + right) / 2;
            list.add(sortedArray[middle]);
            addBalance(list, left, middle - 1, sortedArray);
            addBalance(list, middle + 1, right, sortedArray);        
        }
    }

    @Override
    protected void runTest(Integer[] expected) {
        Integer[] expectedSorted = Arrays.copyOf(expected, expected.length);
        Arrays.sort(expectedSorted);
        Integer[] actual = collection.stream().toArray(Integer[]::new);
        
        assertArrayEquals(expectedSorted, actual);
        assertEquals(expected.length, collection.size());
    }
}
