package pl.edu.pw.ee;

public class Node {
    private char nodeLabel;
    private boolean isVisited;

    public Node(char nodeLabel, boolean isVisited) {
        this.nodeLabel = nodeLabel;
        this.isVisited = isVisited;
    }

    public void setVisited() {
        this.isVisited = true;
    }
}
