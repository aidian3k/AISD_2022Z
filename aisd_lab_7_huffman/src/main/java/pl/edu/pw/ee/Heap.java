package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HeapExtension;
import pl.edu.pw.ee.services.HeapInterface;

import java.util.ArrayList;
import java.util.List;

public class Heap<T extends Comparable<T>> implements HeapInterface<T>, HeapExtension {

    private final List<T> data;

    public Heap() {
        this.data = new ArrayList<>();
    }

    public Heap(List<T> data) {
        this.data = data;
        validateData(data);
        this.build();
    }

    @Override
    public void put(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Given item cannot be null!");
        }

        data.add(item);
        int heapSize = getHeapSize();

        heapifyUp(heapSize - 1);
    }

    @Override
    public T pop() {
        int heapSize = getHeapSize();

        if (heapSize == 0) {
            throw new IllegalStateException("Cannot pop element from empty heap");
        }

        T minValue = data.get(0);
        swap(0, heapSize - 1);

        data.remove(heapSize - 1);
        heapify(0, heapSize - 1);

        return minValue;
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
        validateHeapifyData(startId, endId);

        int currentId = startId;

        while (currentId < endId) {
            int leftChild = getLeftChildIndex(currentId);
            int rightChild = getRightChildIndex(currentId);
            int minimumChild = currentId;

            minimumChild = getMinimumChild(endId, leftChild, rightChild, minimumChild);

            if (minimumChild == currentId) {
                return;
            } else {
                swap(currentId, minimumChild);
                currentId = minimumChild;
            }
        }
    }

    public void heapifyUp(int startIndex) {
        if (startIndex < 0 || startIndex >= getHeapSize()) {
            throw new IllegalArgumentException("StartId cannot be negative or greater than heapSize");
        }

        int parentIndex = getParentIndex(startIndex);
        int currentIndex = startIndex;

        while (currentIndex > 0 && data.get(currentIndex).compareTo(data.get(parentIndex)) < 0) {
            swap(currentIndex, parentIndex);
            currentIndex = parentIndex;
            parentIndex = getParentIndex(currentIndex);
        }
    }

    public int getHeapSize() {
        return this.data.size();
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

    private void validateData(List<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Given list cannot be null!");
        }

        for (T item : data) {
            if (item == null) {
                throw new IllegalArgumentException("Element in the list cannot be null!");
            }
        }
    }

    private void validateHeapifyData(int startId, int endId) {
        int heapSize = getHeapSize();

        if (startId < 0 || endId < 0) {
            throw new IllegalArgumentException("The startId and endId must be positive values");
        } else if (endId > heapSize) {
            throw new IllegalArgumentException("EndId cannot be greater than heapSize!");
        } else if (startId > endId) {
            throw new IllegalArgumentException("EndId must be greater than startId");
        }
    }

    private int getMinimumChild(int endId, int leftChild, int rightChild, int minimumChild) {
        if (leftChild < endId && data.get(leftChild).compareTo(data.get(minimumChild)) < 0) {
            minimumChild = leftChild;
        }

        if (rightChild < endId && data.get(rightChild).compareTo(data.get(minimumChild)) < 0) {
            minimumChild = rightChild;
        }

        return minimumChild;
    }

    public T getMinimumElement() {
        if (data.size() == 0) {
            throw new IllegalArgumentException("Cannot get minimum element from empty heap!");
        }

        return data.get(0);
    }

    private void swap(int firstId, int secondId) {
        if (firstId != secondId) {
            T firstValue = data.get(firstId);
            data.set(firstId, data.get(secondId));
            data.set(secondId, firstValue);
        }
    }

}
