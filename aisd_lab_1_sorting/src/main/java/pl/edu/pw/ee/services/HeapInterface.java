package pl.edu.pw.ee.services;

public interface HeapInterface<T extends Comparable<T>> {

    void put(T item);

    T pop();

}
