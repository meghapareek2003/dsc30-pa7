/*
 * Name: TODO
 * PID:  TODO
 */

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Title: dHeap Description: This program creates a Heap with d branching factor
 *
 * @author TODO
 * @since TODO
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
        this.heap = (T[]) new Comparable[10];
        this.d = 2;
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
        this.heap = (T[]) new Comparable[heapSize];
        this.d = 2;
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
        if (d < 1) {
            throw new IllegalArgumentException("d must be greater than or equal to 1");
        }
        this.heap = (T[]) new Comparable[heapSize];
        this.d = d;
        this.nelems = 0;
        this.isMaxHeap = isMaxHeap;


    }

    @Override
    public int size() {
        return nelems;
    }

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

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        heap = (T[]) new Comparable[heap.length];
        nelems = 0;

//        for (int i = 0; i < nelems; i++) {
//            heap[i] = null;
//        }
//        nelems = 0;
    }

    @Override
    public T element() throws NoSuchElementException {
        if (nelems == 0) {
            throw new NoSuchElementException("Heap is empty");
        }

        return heap[0];
    }

    private int parent(int index) {
        if (index == 0) {
            return 0;
        }
        return (index - 1) / d;
    }

    private boolean isGreaterThan(T a, T b) {
        return a.compareTo(b) > 0;
    }

    private boolean isLessThan(T a, T b) {
        return a.compareTo(b) < 0;
    }

    private void bubbleUp(int index) {
        T item = heap[index];
        while (index > 0 && compare(item, heap[parent(index)])) {
            heap[index] = heap[parent(index)];
            index = parent(index);
        }
        heap[index] = item;
    }

    private boolean compare(T a, T b) {
        if (isMaxHeap) {
            return a.compareTo(b) > 0;
        }
        else {
            return a.compareTo(b) < 0;
        }
    }

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

    @SuppressWarnings("unchecked")
    private void resize() {
        T[] old = heap;
        heap = (T[]) new Comparable[heap.length * 2];
        System.arraycopy(old, 0, heap, 0, old.length);
    }

}
