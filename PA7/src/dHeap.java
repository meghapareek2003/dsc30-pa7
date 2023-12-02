/*
 * Name: TODO
 * PID:  TODO
 */

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Title: dHeap Description: This program creates a Heap with d branching factor
 *
 * @author Megha Pareek
 * @since 11/25/2023
 *
 * @param <T> the type of elements held in this collection
 */

public class dHeap<T extends Comparable<? super T>> implements HeapInterface<T> {

    private T[] heap;   // backing array
    private int d;      // branching factor
    private int nelems; // number of elements
    private boolean isMaxHeap; // indicates whether heap is max or min

    /**
     * Initializes a binary max heap with capacity = 10
     */
    @SuppressWarnings("unchecked")
    public dHeap() {
        final int currentBranchingFactor = 2;
        int currentHeapSize = 10;
        this.heap = (T[]) new Comparable[currentHeapSize];
        this.d = currentBranchingFactor;
        this.nelems = 0;
        this.isMaxHeap = true;
    }

    /**
     * Initializes a binary max heap with a given initial capacity.
     *
     * @param heapSize The initial capacity of the heap.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int heapSize) {
        int currentBranchingFactor = 2;
        this.heap = (T[]) new Comparable[heapSize];
        this.d = currentBranchingFactor;
        this.nelems = 0;
        this.isMaxHeap = true;
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
        int minSize = 1;
        if (d < minSize) {
            throw new IllegalArgumentException("d must be greater than or equal to 1");
        }
        this.heap = (T[]) new Comparable[heapSize];
        this.d = d;
        this.nelems = 0;
        this.isMaxHeap = isMaxHeap;


    }
    /**
     * Returns the number of elements in the heap.
     *
     * @return The number of elements in the heap.
     */
    @Override
    public int size() {
        return nelems;
    }

    /**
     * Removes and returns the highest priority element in the heap.
     * Throws NoSuchElementException if the heap is empty.
     *
     * @return The highest priority element in the heap.
     * @throws NoSuchElementException if the heap is empty.
     */

    @Override
    public T remove() throws NoSuchElementException {
        if (nelems == 0) {
            throw new NoSuchElementException("Heap is empty");
        }
        T removedItem = heap[0];
        heap[0] = heap[nelems - 1];
        heap[nelems - 1] = null;
        nelems--;
        if (nelems > 0) {
            trickleDown(0);
        }
        return removedItem;
    }

    /**
     * Adds the specified item to the heap.
     *
     * @param item The item to be added to the heap.
     * @throws NullPointerException if the item is null.
     */

    @Override
    public void add(T item) throws NullPointerException {
        if (item == null) {
            throw new NullPointerException("Cannot add null to the heap");
        }
        if (nelems == heap.length) {
            resize();
        }

        heap[nelems] = item;
        nelems++;
        bubbleUp(nelems - 1);
    }
    /**
     * Removes all elements from the heap, making it empty.
     */

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        heap = (T[]) new Comparable[heap.length];
        nelems = 0;
    }

    /**
     * Retrieves, but does not remove, the highest priority element in the heap.
     *
     * @return The highest priority element.
     * @throws NoSuchElementException if the heap is empty.
     */

    @Override
    public T element() throws NoSuchElementException {
        if (nelems == 0) {
            throw new NoSuchElementException("Heap is empty");
        }

        return heap[0];
    }

    /**
     * Calculates the parent index of a given index in the heap.
     *
     * @param index The index for which to find the parent.
     * @return The index of the parent.
     */

    private int parent(int index) {
        if (index == 0) {
            return 0;
        }
        return (index - 1) / d;
    }

    /**
     * Moves the item up the heap until its order is restored.
     *
     * @param index The index of the item to be moved.
     */

    private void bubbleUp(int index) {
        T item = heap[index];
        while (index > 0 && compare(item, heap[parent(index)])) {
            heap[index] = heap[parent(index)];
            index = parent(index);
        }
        heap[index] = item;
    }

    /**
     * Compares two elements based on whether the heap is a max heap or min heap.
     *
     * @param a The first element.
     * @param b The second element.
     * @return True if the elements need to be swapped, false otherwise.
     */

    private boolean compare(T a, T b) {
        if (isMaxHeap) {
            return a.compareTo(b) > 0;
        }
        else {
            return a.compareTo(b) < 0;
        }
    }

    /**
     * Moves the item down the heap until its order is restored.
     *
     * @param index The index of the item to be moved.
     */

    private void trickleDown(int index) {
        T item = heap[index];
        int child;

        while (d * index + 1 < nelems) {
            child = d * index + 1;
            int end = Math.min(d * index + d, nelems - 1);

            int chosenChild = child;
            for (int k = child + 1; k <= end; k++) {
                if (k < nelems && compare(heap[k], heap[chosenChild])) {
                    chosenChild = k;
                }
            }

            if (compare(heap[chosenChild], item)) {
                heap[index] = heap[chosenChild];
            } else {
                break;
            }

            index = chosenChild;
        }

        heap[index] = item;
    }

    /**
     * Resizes the heap array by creating a new array with double the capacity and copying
     * elements to it.
     * Used when the current heap is full and needs more space to accommodate additional elements.
     */

    @SuppressWarnings("unchecked")
    private void resize() {
        int doubleSize = 2;
        T[] newArray = (T[]) new Comparable[heap.length * doubleSize];
        for (int i = 0; i < heap.length; i++) {
            newArray[i] = heap[i];
        }
        heap = newArray;
    }

}
