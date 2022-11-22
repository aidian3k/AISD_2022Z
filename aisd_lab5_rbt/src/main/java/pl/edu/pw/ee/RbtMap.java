package pl.edu.pw.ee;

import pl.edu.pw.ee.services.MapInterface;

public class RbtMap<K extends Comparable<K>, V> implements MapInterface<K, V> {

    private final RedBlackTree<K, V> tree;

    public RbtMap() {
        tree = new RedBlackTree<>();
    }

    @Override
    public void setValue(K key, V value) {
        tree.put(key, value);
    }

    @Override
    public V getValue(K key) {
        return tree.get(key);
    }

    public void deleteMax() {
        tree.deleteMax();
    }

    public void deleteMin() {
        tree.deleteMin();
    }

    public String getInOrder() {
        return tree.getInOrder();
    }

    public String getPostOrder() {
        return tree.getPostOrder();
    }

    public String getPreOrder() {
        return tree.getPreOrder();
    }

}
