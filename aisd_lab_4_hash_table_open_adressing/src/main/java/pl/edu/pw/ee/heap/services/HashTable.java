package pl.edu.pw.ee.heap.services;

public interface HashTable<T extends Comparable<T>> {

    void put(T elem);

    T get(T elem);

    void delete(T elem);
}
