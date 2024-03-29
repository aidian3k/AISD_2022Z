package pl.edu.pw.ee.heap.services;

public interface HashTable<T extends Comparable<T>> {

    void add(T value);

    T get(T value);
    
    void delete(T value);

}