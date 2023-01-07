package pl.edu.pw.ee;

public class Edge implements Comparable<Edge> {
    private final Node startNode;
    private final Node endNode;
    private final int weight;

    public Edge(Node startNode, Node endNode, int weight) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.weight = weight;
    }

    public Node getStartNode() {
        return this.startNode;
    }

    public Node getEndNode() {
        return this.endNode;
    }

    public int getWeight() {
        return this.weight;
    }

    @Override
    public int compareTo(Edge comparingEdge) {
        return this.weight - comparingEdge.weight;
    }

    @Override
    public String toString() {
        return this.startNode.getNodeLabel() + "_" + this.weight + "_" + this.endNode.getNodeLabel();
    }
}
