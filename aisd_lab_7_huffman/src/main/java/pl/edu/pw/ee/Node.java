package pl.edu.pw.ee;

public class Node implements Comparable<Node> {
    private Character sign;
    private int frequency;
    private Node left;
    private Node right;

    public Node() {
        this.sign = null;
        this.left = null;
        this.right = null;
        this.frequency = 0;
    }

    public Node(Character sign, int frequency) {
        validateLeafNode(sign, frequency);

        this.sign = sign;
        this.left = null;
        this.right = null;
        this.frequency = frequency;
    }

    public Node(int frequency, Node left, Node right) {
        validateInternalNode(frequency, left, right);

        this.sign = null;
        this.left = left;
        this.right = right;
        this.frequency = frequency;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node node) {
        validateNode(node);
        this.left = node;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node node) {
        validateNode(node);
        this.right = node;
    }

    public Character getSign() {
        return sign;
    }

    public int getFrequency() {
        return frequency;
    }

    public void increaseFrequency() {
        this.frequency = frequency + 1;
    }

    public boolean isLeaf() {
        return right == null && left == null;
    }

    @Override
    public int compareTo(Node comparedNode) {
        return this.frequency - comparedNode.frequency;
    }

    @Override
    public String toString() {
        return sign + " " + frequency + " " + isLeaf();
    }

    private void validateInternalNode(int frequency, Node left, Node right) {
        if (frequency < 1 || left == null || right == null) {
            throw new IllegalArgumentException("Nodes cannot be null and frequency cannot be less than 1");
        }
    }

    private void validateNode(Node node) {
        if (node == null) {
            throw new IllegalArgumentException("Node cannot be null");
        }
    }

    private void validateLeafNode(Character sign, int frequency) {
        if (sign == null || frequency < 1) {
            throw new IllegalArgumentException("Sign cannot be null and frequency cannot be less than 1");
        }
    }
}
