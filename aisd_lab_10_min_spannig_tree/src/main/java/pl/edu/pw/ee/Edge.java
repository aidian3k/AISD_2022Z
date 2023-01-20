package pl.edu.pw.ee;

public class Edge implements Comparable<Edge> {
    private final Node startNode;
    private final Node endNode;
    private final int weight;

    public Edge(Node startNode, Node endNode, int weight) {
        validateInput(startNode, endNode, weight);

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

    private void validateInput(Node startNode, Node endNode, int weight) {
        if (startNode == null || endNode == null) {
            throw new IllegalArgumentException("StartNode and endNode cannot be null values!");
        }

        if (weight < 0) {
            throw new IllegalArgumentException("Weight between the nodes cannot be negative!");
        }
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
