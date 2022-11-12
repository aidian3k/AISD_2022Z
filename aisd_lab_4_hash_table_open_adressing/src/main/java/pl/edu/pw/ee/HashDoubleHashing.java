package pl.edu.pw.ee;

public class HashDoubleHashing<T extends Comparable<T>> extends HashOpenAddressing<T> {

    public HashDoubleHashing() {
        super();
    }

    public HashDoubleHashing(int size) {
        super(size);
        validateInputSize(size);
    }

    @Override
    int hashFunc(int key, int i) {
        int m = getSize();

        int hash = (firstHashFunction(key, m) + i * secondHashFunction(key, m)) % m;

        hash = hash > 0 ? hash : -hash;

        return hash;
    }

    private int firstHashFunction(int key, int m) {
        return key % m;
    }

    private int secondHashFunction(int key, int m) {
        return 1 + (key % (m - 3));
    }

    private void validateInputSize(int size) {
        if(size < 1  || size == 3) {
            throw new IllegalArgumentException("Initial size of hash table cannot be lower than 1 or equal 3!");
        }
    }
}
