package pl.edu.pw.ee;

import java.util.List;
import pl.edu.pw.ee.services.HeapExtension;
import pl.edu.pw.ee.services.HeapInterface;

public class Heap<T extends Comparable<T>> implements HeapInterface<T>, HeapExtension {

    private List<T> data;

    public Heap(List<T> data) {
        this.data = data;
    }

    @Override
    public void put(T item) {
        int currentIndex = getHeapSize() - 1;

        while (currentIndex > 0) {

        }
    }

    @Override
    public T pop() {
        int heapSize = getHeapSize();

        if (heapSize == 0) {
            throw new ArrayIndexOutOfBoundsException("Cannot pop from empty heap");
        }

        T maxValue = data.get(0);
        swap(0, heapSize - 1);

        data.remove(heapSize - 1);
        heapify(0, heapSize - 1);

        return maxValue;
    }

    @Override
    public void build() {
        int n = data.size();

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(i, n);
        }
    }

    @Override
    public void heapify(int startId, int endId) {
        // TODO
    }

    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private int getHeapSize() {
        return this.data.size();
    }

    private int compareValues(int firstId, int secondId) {
        return data.get(firstId).compareTo(data.get(secondId));
    }

    private void swap(int firstId, int secondId) {
        if (firstId != secondId) {
            T firstValue = data.get(firstId);
            data.set(firstId, data.get(secondId));
            data.set(secondId, firstValue);
        }
    }
}
