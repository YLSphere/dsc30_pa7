/*
 * Name: Yin Lam Lai
 * PID:  A15779757
 */

import java.util.*;

/**
 * Creates a min/max array heap with a custom branching factor and size
 * 
 * @param <T> Generic type
 */
public class dHeap<T extends Comparable<? super T>> implements dHeapInterface<T> {

    private T[] heap; // heap array
    private int d; // branching factor
    private int nelems; // number of elements
    private boolean isMaxHeap; // boolean to indicate whether heap is max or min
    private static int DEFAULT_SIZE = 6;
    private static int BINARY_D = 2;
    private static int RESIZE_FACTOR = 2;

    /**
     * Initializes a binary max heap with capacity = 6
     */
    @SuppressWarnings("unchecked")
    public dHeap() {
        heap = (T[]) new Comparable[DEFAULT_SIZE];
        isMaxHeap = true;
        nelems = 0;
        d = BINARY_D;
    }

    /**
     * Initializes a binary max heap with a given initial capacity.
     *
     * @param heapSize The initial capacity of the heap.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int heapSize) {
        heap = (T[]) new Comparable[heapSize];
        isMaxHeap = true;
        nelems = 0;
        d = BINARY_D;
    }

    /**
     * Initializes a d-ary heap (with a given value for d), with a given initial
     * capacity.
     *
     * @param d         The number of child nodes each node in the heap should have.
     * @param heapSize  The initial capacity of the heap.
     * @param isMaxHeap indicates whether the heap should be max or min
     * @throws IllegalArgumentException if d is less than one.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int d, int heapSize, boolean isMaxHeap) throws IllegalArgumentException {
        if (d < 1) {
            throw new IllegalArgumentException();
        }
        heap = (T[]) new Comparable[heapSize];
        this.isMaxHeap = isMaxHeap;
        nelems = 0;
        this.d = d;
    }

    /**
     * Returns the current size of the heap
     */
    @Override
    public int size() {
        return nelems;
    }

    /**
     * Adds an element into the heap
     *
     * @param data data to be added
     * @throws NullPointerException if data is null
     */
    @Override
    public void add(T data) throws NullPointerException {
        if (data == null) {
            throw new NullPointerException();
        }
        if (nelems == 0) {
            heap[0] = data;
            nelems++;
        } else {
            if (nelems == heap.length) {
                resize();
            }
            heap[nelems] = data;
            nelems++;
            bubbleUp(nelems - 1);
        }
    }

    /**
     * Removes the root element from the heap
     *
     * @throws NoSuchElementException if the heap if empty
     */
    @Override
    public T remove() throws NoSuchElementException {
        if (nelems == 0) {
            throw new NoSuchElementException();
        }
        T temp = heap[0];
        heap[0] = heap[nelems - 1];
        heap[nelems - 1] = null;
        nelems--;
        trickleDown(0);

        return temp;
    }

    /**
     * Clears the heap
     */
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        for (int n = 0; n < heap.length; n++) {
            heap[n] = null;
        }
        nelems = 0;
    }

    /**
     * Peeks the root element of the heap
     */
    public T element() throws NoSuchElementException {
        if (nelems == 0) {
            throw new NoSuchElementException();
        }
        return heap[0];
    }

    /**
     * Compares the element at given index with its children and moves it downwards depending on the heap type
     *
     * @param index index of element
     */
    private void trickleDown(int index) {
        ArrayList<T> children = new ArrayList<>();

        if (isMaxHeap) {
            for (int n = 1; n <= d; n++) {
                if (((index * d) + n) > heap.length) {
                    continue;
                }
                if (heap[(index * d) + n] != null) {
                    children.add(heap[(index * d) + n]);
                }
            }

            if (children.isEmpty()) {
                return;
            }

            T maxValue = children.get(0);


            for (int n = 1; n < children.size(); n++) {
                if (maxValue.compareTo(children.get(n)) <= 0) {
                    maxValue = children.get(n);
                }
            }

            int newIndex = children.indexOf(maxValue) + 1;

            if (heap[index].compareTo(maxValue) > 0) {
                return;
            }
            if (heap[index].compareTo(maxValue) <= 0) {
                T temp = heap[index];
                heap[index] = maxValue;
                heap[(index * d) + newIndex] = temp;
                trickleDown((index * d) + newIndex);
            }
        } else {
            for (int n = 1; n <= d; n++) {
                if (((index * d) + n) >= heap.length) {
                    continue;
                }
                if (heap[(index * d) + n] != null) {
                    children.add(heap[(index * d) + n]);
                }
            }

            if (children.isEmpty()) {
                return;
            }

            T minValue = children.get(0);


            for (int n = 1; n < children.size(); n++) {
                if (minValue.compareTo(children.get(n)) > 0) {
                    minValue = children.get(n);
                }
            }

            int newIndex = children.indexOf(minValue) + 1;

            if (heap[index].compareTo(minValue) < 0) {
                return;
            }
            if (heap[index].compareTo(minValue) >= 0) {
                T temp = heap[index];
                heap[index] = minValue;
                heap[(index * d) + newIndex] = temp;
                trickleDown((index * d) + newIndex);
            }
        }
    }

    /**
     * Compares the element at given index with its parent and moves it upwards depending on the heap type
     *
     * @param index index of element
     */
    private void bubbleUp(int index) {
        if (isMaxHeap) {
            while (heap[parent(index)].compareTo(heap[index]) < 0) {
                T temp = heap[parent(index)];
                heap[parent(index)] = heap[index];
                heap[index] = temp;
                index = parent(index);
            }
        } else {
            while (heap[parent(index)].compareTo(heap[index]) > 0) {
                T temp = heap[parent(index)];
                heap[parent(index)] = heap[index];
                heap[index] = temp;
                index = parent(index);
            }
        }
    }

    /**
     * Doubles the size of the array
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        T[] temp = (T[]) new Comparable[ nelems * RESIZE_FACTOR];
        for (int n = 0; n < nelems; n++) {
            temp[n] = heap[n];
        }
        heap = temp;
    }
    /**
     * Returns the parent index of an element
     *
     * @param index index of element
     */
    private int parent(int index) {
        return (index - 1) / d;
    }

}
