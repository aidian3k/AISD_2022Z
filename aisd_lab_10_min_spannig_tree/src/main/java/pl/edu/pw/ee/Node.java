package pl.edu.pw.ee;

public class Node {
    private final String nodeLabel;
    private Node parentNode;
    private boolean isVisited;

    public Node(String nodeLabel) {
        this.nodeLabel = nodeLabel;
        this.parentNode = this;
    }

    public String getNodeLabel() {
        return nodeLabel;
    }

    public Node getParentNode() {
        if (this.equals(parentNode)) {
            return this;
        } else {
            return this.parentNode.getParentNode();
        }
    }

    public boolean isVisited() {
        return this.isVisited;
    }

    public void setVisited() {
        this.isVisited = true;
    }

    public void setParent(Node parentNode) {
        this.parentNode = parentNode;
    }

    @Override
    public boolean equals(Object comparingObject) {
        return comparingObject instanceof Node && ((Node) comparingObject).nodeLabel.equals(this.nodeLabel);
    }

}
