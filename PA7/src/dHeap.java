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

    private void bubbleUp(int index) {
        T item = heap[index];
        while (index > 0 && item.compareTo(heap[parent(index)]) > 0) {
            heap[index] = heap[parent(index)];
            index = parent(index);
        }
        heap[index] = item;
    }

    private void trickleDown(int index) {
        int child;
        T temp = heap[index];

        while (d * index + 1 < nelems) {
            child = maxChild(index);

            if (temp.compareTo(heap[child]) < 0) {
                heap[index] = heap[child];
            } else {
                break;
            }
            index = child;
        }
        heap[index] = temp;
    }

    private int maxChild(int index) {
        int bestChild = d * index + 1;
        int end = Math.min(d * index + d, nelems - 1);

        for (int k = bestChild + 1; k <= end; k++) {
            if (heap[k].compareTo(heap[bestChild]) > 0) {
                bestChild = k;
            }
        }
        return bestChild;
    }


    @SuppressWarnings("unchecked")
    private void resize() {
        T[] old = heap;
        heap = (T[]) new Comparable[heap.length * 2];
        System.arraycopy(old, 0, heap, 0, old.length);
    }

}
