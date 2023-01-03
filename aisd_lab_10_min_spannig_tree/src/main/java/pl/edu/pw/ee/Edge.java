package pl.edu.pw.ee;

public class Edge implements Comparable<Edge> {
    private final char startNode;
    private final char endNode;
    private final int weight;

    public Edge(char startNode, char endNode, int weight) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge comparingEdge) {
        return this.weight - comparingEdge.weight;
    }
}
