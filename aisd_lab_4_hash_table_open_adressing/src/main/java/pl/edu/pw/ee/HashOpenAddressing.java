package pl.edu.pw.ee;

import pl.edu.pw.ee.heap.services.HashTable;

import java.util.Arrays;

public abstract class HashOpenAddressing<T extends Comparable<T>> implements HashTable<T> {

    private final T nil = null;
    private final T deleted = (T) (Comparable<T>) objectValue -> 0;
    private final double correctLoadFactor;
    private int size;
    private int nElems;
    private T[] hashElems;

    HashOpenAddressing() {
        this(2039);
    }

    HashOpenAddressing(int size) {
        validateHashInitSize(size);

        this.size = size;
        this.hashElems = (T[]) new Comparable[this.size];
        this.correctLoadFactor = 0.75;
    }

    @Override
    public void put(T newElem) {
        validateInputElem(newElem);
        resizeIfNeeded();

        int key = newElem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);

        while (hashElems[hashId] != nil && hashElems[hashId] != deleted) {
            i = checkForInfiniteLoop(i);

            if (hashElems[hashId] != nil && hashElems[hashId].equals(newElem)) {
                return;
            }

            i = (i + 1) % size;
            hashId = hashFunc(key, i);
        }

        hashElems[hashId] = newElem;
        nElems++;
    }

    @Override
    public T get(T elem) {
        validateInputElem(elem);

        int key = elem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);
        T searchedElement = nil;

        while (hashElems[hashId] != nil) {
            if (hashElems[hashId].equals(elem)) {
                searchedElement = hashElems[hashId];
                break;
            }

            i = (i + 1) % size;
            hashId = hashFunc(key, i);
        }

        if (searchedElement == nil) {
            throw new IllegalStateException("Getting element which is not in the hash!");
        } else {
            return searchedElement;
        }
    }

    @Override
    public void delete(T elem) {
        validateInputElem(elem);

        int key = elem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);

        while (hashElems[hashId] != nil) {
            if (hashElems[hashId].equals(elem)) {
                hashElems[hashId] = deleted;
                nElems--;
                break;
            }

            i = (i + 1) % size;
            hashId = hashFunc(key, i);
        }

    }

    private void validateHashInitSize(int initialSize) {
        if (initialSize < 1) {
            throw new IllegalArgumentException("Initial size of hash table cannot be lower than 1!");
        }
    }

    private void validateInputElem(T newElem) {
        if (newElem == null) {
            throw new IllegalArgumentException("Input elem cannot be null!");
        }
    }

    private int checkForInfiniteLoop(int i) {
        if (i + 1 == this.size) {
            doubleResize();
            return -1;
        } else {
            return i;
        }
    }

    abstract int hashFunc(int key, int i);

    int getSize() {
        return size;
    }

    public int getNumberOfElems() {
        return nElems;
    }

    private void resizeIfNeeded() {
        double loadFactor = countLoadFactor();

        if (loadFactor >= correctLoadFactor) {
            doubleResize();
        }
    }

    private double countLoadFactor() {
        return (double) nElems / size;
    }

    private void doubleResize() {
        T[] oldElems = Arrays.copyOf(hashElems, this.size);

        this.size *= 2;
        hashElems = (T[]) new Comparable[this.size];
        this.nElems = 0;

        for (T elem : oldElems) {
            if (elem != nil && elem != deleted) {
                this.put(elem);
            }
        }
    }
}
