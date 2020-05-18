import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class dHeapTester {
    dHeap testMax;
    dHeap testMin;


    @org.junit.Before
    public void setUp() throws Exception {
        testMax = new dHeap();
        testMin= new dHeap(3,6, false);
    }

    @org.junit.Test
    public void size() {
        assertEquals(0, testMax.size());
        testMin.add(1);
        testMax.add(1);
        testMax.add(1);
        assertEquals(2, testMax.size());
        testMin.remove();
        assertEquals(0, testMin.size());
    }

    @org.junit.Test
    public void add() {
        Integer[] arrayTest = new Integer[] {6,2,9,3,5,7,7};

        for (Integer n : arrayTest) {
            testMax.add(n);
            testMin.add(n);
        }
        assertEquals(9, testMax.remove());
        assertEquals(2, testMin.remove());
        testMax.remove();
        testMax.remove();
        assertEquals(6,  testMax.remove());

    }

    @org.junit.Test
    public void remove() {
        Integer[] arrayTest = new Integer[] {1,4,4,6,3,3,33,65,78,481,15,90,5};

        for (Integer n : arrayTest) {
            testMax.add(n);
            testMin.add(n);
        }
        assertEquals(481, testMax.remove());
        assertEquals(1, testMin.remove());
        testMax.remove();
        testMax.remove();
        assertEquals(65,  testMax.remove());

    }

    @org.junit.Test
    public void clear() {
        Integer[] arrayTest = new Integer[] {1,4};

        for (Integer n : arrayTest) {
            testMax.add(n);
            testMin.add(n);
        }
        testMax.clear();
        testMin.clear();
        assertEquals(0, testMax.size());
        assertEquals(0, testMin.size());
        try{
            testMin.remove();
        } catch (NoSuchElementException e) {
            System.out.print("Array Empty");
        }
    }

    @org.junit.Test
    public void element() {

        Integer[] arrayTest = new Integer[] {1,4,4,6,3,3,33,65,78,481,15,90,5};

        for (Integer n : arrayTest) {
            testMax.add(n);
            testMin.add(n);
        }

        assertEquals(481, testMax.element());
        assertEquals(1, testMin.element());
        testMax.remove();
        testMax.remove();
        assertEquals(78,  testMax.element());

    }
}