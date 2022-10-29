package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HashTable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class HashListChaining<T extends Comparable<T>> implements HashTable<T> {

    private final Elem nil = null;
    private final Elem[] hashElems;
    private final int hashSize;
    private int nElem;

    private class Elem {
        private Elem next;
        private T value;

        Elem(T value, Elem nextElem) {
            this.value = value;
            this.next = nextElem;
        }
    }

    public HashListChaining(int size) {
        validateConstructorData(size);
        this.hashElems = (Elem[]) Array.newInstance(Elem.class, size);
        this.hashSize = size;
        initializeHash();
    }

    @Override
    public void add(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null!");
        }

        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem oldElem = hashElems[hashId];

        if (oldElem == nil) {
            hashElems[hashId] = new Elem(value, nil);
            nElem++;
        } else {
            while (oldElem.next != nil && !oldElem.value.equals(value)) {
                oldElem = oldElem.next;
            }

            if (oldElem.value.equals(value)) {
                oldElem.value = value;
            } else {
                oldElem.next = new Elem(value, nil);
                nElem++;
            }
        }
    }

    @Override
    public T get(T value) {
        if (value == null) {
            throw new IllegalStateException("Value cannot be null!");
        } else if (nElem == 0) {
            throw new IllegalArgumentException("Cannot get element from the hash. Hash is empty!");
        }

        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem elem = hashElems[hashId];

        while (elem != nil && !elem.value.equals(value)) {
            elem = elem.next;
        }

        return elem != nil ? elem.value : null;
    }

    @Override
    public void delete(T value) {
        if (nElem == 0) {
            throw new IllegalArgumentException("Cannot delete from the empty hashList!");
        } else if (value == null) {
            throw new IllegalArgumentException("Cannot delete null value!");
        }

        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem currentElem = hashElems[hashId];

        if (currentElem != nil && currentElem.value.equals(value)) {
            hashElems[hashId] = currentElem.next;
            nElem--;
            return;
        }

        if (currentElem != nil) {
            Elem prevElem = currentElem;

            while (currentElem != nil && !currentElem.value.equals(value)) {
                prevElem = currentElem;
                currentElem = currentElem.next;
            }

            if (currentElem != nil) {
                prevElem.next = currentElem.next;
                nElem--;
            }
        }
    }

    public double countLoadFactor() {
        return (double) nElem / hashSize;
    }

    public int getNumberOfElements() {
        return nElem;
    }

    public List<T> getListElementsAtIndex(int index) {
        if (index < 0 || index >= hashSize) {
            throw new IllegalArgumentException("Cannot get list from negative or greater than listSize Index!");
        }

        Elem currentList = hashElems[index];

        if (currentList == null) {
            return null;
        } else {
            List<T> list = new ArrayList<>();

            while (currentList != nil) {
                list.add(currentList.value);
                currentList = currentList.next;
            }

            return list;
        }
    }

    private int countHashId(int hashCode) {
        return Math.abs(hashCode) % hashSize;
    }

    private void initializeHash() {
        for (int i = 0; i < hashSize; i++) {
            hashElems[i] = nil;
        }
    }

    private void validateConstructorData(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Cannot create hashList with negative size!");
        }
    }

}
