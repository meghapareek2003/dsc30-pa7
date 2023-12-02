import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class dHeapTester {

    @Test
    void testDefaultConstructor() {
        dHeap<Integer> heap = new dHeap<>();
        assertEquals(0, heap.size());
        heap.add(5);
        assertEquals(1, heap.size());
        assertEquals(5, heap.element());

        dHeap<Integer> heap2 = new dHeap<>(15);
        assertEquals(0, heap2.size());
        heap2.add(10);
        assertEquals(1, heap2.size());
        assertEquals(10, heap2.element());

        dHeap<Integer> maxHeap = new dHeap<>(2, 10, true);
        maxHeap.add(10);
        maxHeap.add(20);
        maxHeap.add(5);
        assertEquals(3, maxHeap.size());
        assertEquals(20, maxHeap.remove());
        assertEquals(2, maxHeap.size());
        assertEquals(10, maxHeap.remove());
        assertEquals(1, maxHeap.size());

        // Testing a min heap
        dHeap<Integer> minHeap = new dHeap<>(2, 10, false);
        minHeap.add(10);
        minHeap.add(5);
        minHeap.add(20);
        assertEquals(3, minHeap.size());
        assertEquals(5, minHeap.remove());
        assertEquals(2, minHeap.size());
        assertEquals(10, minHeap.remove());
        assertEquals(1, minHeap.size());

    }

    @Test
    void testSize() {
        dHeap<Integer> heap = new dHeap<>();
        assertEquals(0, heap.size());
        heap.add(5);
        assertEquals(1, heap.size());
        heap.add(10);
        assertEquals(2, heap.size());
        heap.remove();
        assertEquals(1, heap.size());
        heap.clear();
        assertEquals(0, heap.size());
    }

    @Test
    void testRemove() {
        dHeap<Integer> maxHeap = new dHeap<>(3, 10, true);
        maxHeap.add(50);
        maxHeap.add(30);
        maxHeap.add(70);
        maxHeap.add(20);
        maxHeap.add(40);
        assertEquals(70, maxHeap.remove());
        assertEquals(50, maxHeap.remove());
        assertEquals(40, maxHeap.remove());
        assertEquals(2, maxHeap.size());
        assertEquals(30, maxHeap.element());

        dHeap<Integer> minHeap = new dHeap<>(3, 10, false);
        minHeap.add(50);
        minHeap.add(30);
        minHeap.add(70);
        minHeap.add(20);
        minHeap.add(40);
        assertEquals(20, minHeap.remove());
        assertEquals(30, minHeap.remove());
        assertEquals(40, minHeap.remove());
        assertEquals(2, minHeap.size());
        assertEquals(50, minHeap.element());
    }

    @Test
    void testAdd() {
        dHeap<Integer> maxHeap = new dHeap<>(3, 10, true);
        assertEquals(0, maxHeap.size());
        maxHeap.add(50);
        assertEquals(1, maxHeap.size());
        assertEquals(50, maxHeap.element());
        maxHeap.add(30);
        maxHeap.add(70);
        maxHeap.add(20);
        assertEquals(4, maxHeap.size());
        assertEquals(70, maxHeap.element());

        dHeap<Integer> minHeap = new dHeap<>(3, 10, false);
        assertEquals(0, minHeap.size());
        minHeap.add(50);
        assertEquals(1, minHeap.size());
        assertEquals(50, minHeap.element());
        minHeap.add(30);
        minHeap.add(70);
        minHeap.add(20);
        assertEquals(4, minHeap.size());
        assertEquals(20, minHeap.element());
    }
    @Test
    void testClear() {
        dHeap<Integer> maxHeap = new dHeap<>(3, 10, true);
        maxHeap.add(50);
        maxHeap.add(30);
        maxHeap.add(70);
        maxHeap.add(20);
        assertEquals(4, maxHeap.size());
        maxHeap.clear();
        assertEquals(0, maxHeap.size());
    }

    @Test
    void testElement() {
        dHeap<Integer> maxHeap = new dHeap<>(3, 10, true);
        maxHeap.add(50);
        maxHeap.add(30);
        maxHeap.add(70);
        maxHeap.add(20);
        assertEquals(70, maxHeap.element());
    }
}
