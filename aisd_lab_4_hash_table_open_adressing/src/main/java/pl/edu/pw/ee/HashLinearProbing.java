package pl.edu.pw.ee;

public class HashLinearProbing<T extends Comparable<T>> extends HashOpenAddressing<T> {

    public HashLinearProbing() {
        super();
    }

    public HashLinearProbing(int size) {
        super(size);
    }

    @Override
    int hashFunc(int key, int i) {
        int m = getSize();

        int hash = (helpHashFunction(key, m) + i) % m;

        hash = hash < 0 ? -hash : hash;

        return hash;
    }

    private int helpHashFunction(int key, int m) {
        return key % m;
    }
}