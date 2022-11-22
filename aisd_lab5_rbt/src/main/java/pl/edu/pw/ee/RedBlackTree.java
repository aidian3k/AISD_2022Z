package pl.edu.pw.ee;

import static pl.edu.pw.ee.Color.BLACK;
import static pl.edu.pw.ee.Color.RED;

public class RedBlackTree<K extends Comparable<K>, V> {
    private Node<K, V> root;

    public V get(K key) {
        validateKey(key);
        Node<K, V> node = root;

        V result = null;

        while (node != null) {

            if (shouldCheckOnTheLeft(key, node)) {
                node = node.getLeft();
            } else if (shouldCheckOnTheRight(key, node)) {
                node = node.getRight();
            } else {
                result = node.getValue();
                break;
            }
        }

        if (result == null) {
            throw new IllegalArgumentException("Searched value cannot be null!");
        } else {
            return result;
        }
    }

    public void put(K key, V value) {
        validateParams(key, value);
        root = put(root, key, value);
        root.setColor(BLACK);
    }

    private Node<K, V> put(Node<K, V> node, K key, V value) {
        if (node == null) {
            return new Node<>(key, value);
        }

        if (isKeyBiggerThanNode(key, node)) {
            putOnTheRight(node, key, value);

        } else if (isKeySmallerThanNode(key, node)) {
            putOnTheLeft(node, key, value);

        } else {
            node.setValue(value);
        }

        node = reorganizeTree(node);

        return node;
    }

    public void deleteMax() {
        root = deleteMax(root);

        if (root != null) {
            root.setColor(BLACK);
        }
    }

    private Node<K, V> deleteMax(Node<K, V> node) {
        validateDeletion();

        if (isRed(node.getLeft())) {
            node = rotateRight(node);
        }

        if (node.getRight() == null) {
            return null;
        }

        if (!isRed(node.getRight()) && !isRed(node.getRight().getLeft())) {
            node = reorganizeRedToRight(node);
        }

        Node<K, V> deleteResult = deleteMax(node.getRight());
        node.setRight(deleteResult);

        return reorganizeTree(node);
    }

    private Node<K, V> reorganizeRedToRight(Node<K, V> node) {
        changeColors(node);

        if (isRed(node.getLeft().getLeft())) {
            node = rotateRight(node);
            changeColors(node);
        }

        return node;
    }
    
    public void deleteMin() {
        root = deleteMin(root);

        if (root != null) {
            root.setColor(BLACK);
        }
    }
    
    private Node<K, V> deleteMin(Node<K, V> node) {
        validateDeletion();
        
        if(isRed(node.getRight())) {
            node = rotateLeft(node);
        }
        
        if(node.getLeft() == null) {
            return null;
        }
        
        if (!isRed(node.getLeft()) && !isRed(node.getLeft().getLeft())) {
            node = reorganizeRedToLeft(node);
        }

        Node<K, V> deleteResult = deleteMax(node.getLeft());
        node.setLeft(deleteResult);

        return reorganizeTree(node);
    }
    
    private Node<K, V> reorganizeRedToLeft(Node<K, V> node) {
        changeColors(node);

        if (isRed(node.getRight().getLeft())) {
            node.setRight(rotateRight(node.getRight()));
            node = rotateLeft(node);
            changeColors(node);
        }

        return node;
    }

    public String getPreOrder() {
        StringBuilder result = new StringBuilder();
        return preOderTraversal(result, root);
    }

    private String preOderTraversal(StringBuilder result, Node<K, V> node) {
        if (node != null) {
            result.append(" ").append(node.getKey()).append(":").append(node.getValue());
            preOderTraversal(result, node.getLeft());
            preOderTraversal(result, node.getRight());
        }

        return result.toString().trim();
    }

    public String getInOrder() {
        StringBuilder result = new StringBuilder();
        return inOrderTraversal(result, root);
    }

    private String inOrderTraversal(StringBuilder result, Node<K, V> node) {
        if (node != null) {
            inOrderTraversal(result, node.getLeft());
            result.append(" ").append(node.getKey()).append(":").append(node.getValue());
            inOrderTraversal(result, node.getRight());
        }

        return result.toString().trim();
    }

    public String getPostOrder() {
        StringBuilder result = new StringBuilder();
        return postOrderTraversal(result, root);
    }

    private String postOrderTraversal(StringBuilder result, Node<K, V> node) {
        if (node != null) {
            postOrderTraversal(result, node.getLeft());
            postOrderTraversal(result, node.getRight());
            result.append(" ").append(node.getKey()).append(":").append(node.getValue());
        }

        return result.toString().trim();
    }

    private void putOnTheLeft(Node<K, V> node, K key, V value) {
        Node<K, V> leftChild = put(node.getLeft(), key, value);
        node.setLeft(leftChild);
    }

    private void putOnTheRight(Node<K, V> node, K key, V value) {
        Node<K, V> rightChild = put(node.getRight(), key, value);
        node.setRight(rightChild);
    }

    private boolean isKeyBiggerThanNode(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) > 0;
    }

    private boolean isKeySmallerThanNode(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) < 0;
    }

    private boolean shouldCheckOnTheLeft(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) < 0;
    }

    private boolean shouldCheckOnTheRight(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) > 0;
    }

    private void validateKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null!");
        }
    }

    private void validateParams(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Input params (key, value) cannot be null!");
        }
    }

    private void validateDeletion() {
        if (root == null) {
            throw new IllegalArgumentException("Cannot delete anything from empty tree!");
        }
    }

    private Node<K, V> reorganizeTree(Node<K, V> node) {
        node = rotateLeftIfNeeded(node);
        node = rotateRightIfNeeded(node);
        changeColorsIfNeeded(node);

        return node;
    }

    private Node<K, V> rotateLeftIfNeeded(Node<K, V> node) {
        if (isBlack(node.getLeft()) && isRed(node.getRight())) {
            node = rotateLeft(node);
        }
        return node;
    }

    private Node<K, V> rotateRightIfNeeded(Node<K, V> node) {
        if (isRed(node.getLeft()) && isRed(node.getLeft().getLeft())) {
            node = rotateRight(node);
        }
        return node;
    }

    private Node<K, V> rotateLeft(Node<K, V> node) {
        Node<K, V> head = node.getRight();
        node.setRight(head.getLeft());
        head.setLeft(node);
        head.setColor(node.getColor());
        node.setColor(RED);

        return head;
    }

    private Node<K, V> rotateRight(Node<K, V> node) {
        Node<K, V> head = node.getLeft();
        node.setLeft(head.getRight());
        head.setRight(node);
        head.setColor(node.getColor());
        node.setColor(RED);

        return head;
    }

    private void changeColors(Node<K, V> node) {
        node.changeColor();
        node.getLeft().changeColor();
        node.getRight().changeColor();
    }

    private void changeColorsIfNeeded(Node<K, V> node) {
        if (isRed(node.getLeft()) && isRed(node.getRight())) {
            changeColors(node);
        }
    }

    private boolean isBlack(Node<K, V> node) {
        return !isRed(node);
    }

    private boolean isRed(Node<K, V> node) {
        return node != null && node.isRed();
    }
}
