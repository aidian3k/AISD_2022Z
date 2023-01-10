package pl.edu.pw.ee.graph;

import java.util.Comparator;

import static java.lang.String.format;

public class Edge implements Comparable<Edge>{
    private Node src;
    private Node dest;
    private final double weight;

    public Edge(Node src, Node dest, double weight) {
        validateInput(src, dest);

        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    public Node getSrc() {
        return src;
    }

    public Node getDest() {
        return dest;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return format("Edge[src: %s, dest: %s, weight: %s]", src, dest, Double.toString(weight));
    }

    @Override
    public boolean equals(Object comparingEdge) {
        return comparingEdge instanceof Edge && (((Edge) comparingEdge).src.equals(this.src) && ((Edge) comparingEdge).dest.equals(this.dest))
                || (((Edge) comparingEdge).dest.equals(this.src) && ((Edge) comparingEdge).src.equals(this.dest));
    }

    private void validateInput(Node start, Node end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Input params (start or end) cannot be null!");
        }
    }

    @Override
    public int compareTo(Edge comparingEdge) {
        Comparator<Edge> comparator = new EdgeWeightComparator();

        return comparator.compare(this, comparingEdge);
    }

}