package pl.edu.pw.ee.graph;

import java.util.List;

public class Graph {

    private final String pathToGraphFile;
    private List<Edge> edges;
    private boolean reading;
    
    public Graph(String pathToGraphFile, boolean reading) {
        this.pathToGraphFile = pathToGraphFile;
        this.reading = reading;
        
        readGraphFromFile();
    }

    @Override
    public String toString() {
        StringBuilder graphAsStr = new StringBuilder();

        for (Edge edge : edges) {
            graphAsStr.append(edge).append("\n");
        }

        return graphAsStr.toString();
    }

    private void validateParams(String pathToGraphFile) {
        if (pathToGraphFile == null) {
            throw new IllegalArgumentException("Path to graph file cannot be null!");
        }
    }

    private void readGraphFromFile() {
        GraphReader reader = new GraphReader(pathToGraphFile, reading);
        edges = reader.getEdges();
    }

}
