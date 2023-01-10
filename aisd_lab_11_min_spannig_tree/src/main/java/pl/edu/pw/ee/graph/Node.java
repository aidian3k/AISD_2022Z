package pl.edu.pw.ee.graph;

import static java.lang.String.format;

public class Node {

    private String name;
    private Points coordinates;

    public Node(String name) {
        this.name = name;
    }

    public Node(String name, Points coordinates) {
        this.name = name;
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }
    
    public Points getCoordinates() {
        return coordinates;
    }

    @Override
    public String toString() {
        return format("Node: %s", name);
    }

    @Override
    public boolean equals(Object comparingNode) {
        return comparingNode instanceof Node && ((Node) comparingNode).name.equals(this.name);
    }
}
