package pl.edu.pw.ee;

public class HashQuadraticProbing<T extends Comparable<T>> extends HashOpenAddressing<T> {
    private double a = 0.5;
    private double b = 0.5;

    public HashQuadraticProbing() {
        super();
    }

    public HashQuadraticProbing(int size, double a, double b) {
        super(size);

        validateConstantValues(a, b);
        this.a = a;
        this.b = b;
    }

    @Override
    int hashFunc(int key, int i) {
        int m = getSize();

        int hash = (int) (helpHashFunction(key, m) + a * i + b * i * i) % m;

        hash = hash > 0 ? hash : -hash;

        return hash;
    }

    private int helpHashFunction(int key, int m) {
        return key % m;
    }

    private void validateConstantValues(double a, double b) {
        if (a <= 0 || b <= 0) {
            throw new IllegalArgumentException("Constant values a and b must be positive!");
        }
    }
}
