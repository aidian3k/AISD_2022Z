package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Graph {
    private final HashMap<Node, List<Edge>> adjacencyList;
    private int numberOfNodes;

    public Graph(String pathToTheFile) {
        validateInput(pathToTheFile);

        this.adjacencyList = new HashMap<>();
        this.readGraphFromFile(pathToTheFile);
    }

    private void readGraphFromFile(String pathToTheFile) {
        int lineCounter = 0;
        int positionOfFirstNodeLabel = 0;
        int positionOfSecondNodeLabel = 1;
        int positionOfEdgeWeight = 2;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathToTheFile));
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                lineCounter++;
                String[] splitLine = currentLine.split(" ");
                validateReadingLine(splitLine, currentLine, lineCounter);

                String firstNodeLabel = splitLine[positionOfFirstNodeLabel];
                String secondNodeLabel = splitLine[positionOfSecondNodeLabel];
                int edgeWeight = Integer.parseInt(splitLine[positionOfEdgeWeight]);

                validateEdge(firstNodeLabel, secondNodeLabel, currentLine, lineCounter);

                addEdge(firstNodeLabel, secondNodeLabel, edgeWeight);
            }

            reader.close();
        } catch (IOException fileException) {
            throw new IllegalStateException("There has been a problem while reading the graph!");
        }
    }

    private void addEdge(String firstNodeLabel, String secondNodeLabel, int edgeWeight) {
        Node secondNode = null;
        Node firstNode = null;
        boolean isFirstNodeFound = false;
        boolean isSecondNodeFound = false;

        for (Node node : adjacencyList.keySet()) {
            if (node.getNodeLabel().equals(firstNodeLabel)) {
                isFirstNodeFound = true;
                firstNode = node;
            } else if (node.getNodeLabel().equals(secondNodeLabel)) {
                isSecondNodeFound = true;
                secondNode = node;
            }
        }

        if (!isFirstNodeFound) {
            firstNode = addNodeToListIfNotExist(firstNodeLabel);
        }

        if (!isSecondNodeFound) {
            secondNode = addNodeToListIfNotExist(secondNodeLabel);
        }

        Edge firstEdge = new Edge(firstNode, secondNode, edgeWeight);
        Edge secondEdge = new Edge(secondNode, firstNode, edgeWeight);

        this.adjacencyList.get(firstNode).add(firstEdge);
        this.adjacencyList.get(secondNode).add(secondEdge);
    }

    private Node addNodeToListIfNotExist(String nodeLabel) {
        this.numberOfNodes++;
        Node node = new Node(nodeLabel);
        adjacencyList.put(node, new LinkedList<>());

        return node;
    }

    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    public HashMap<Node, List<Edge>> getAdjacencyList() {
        return adjacencyList;
    }

    public List<Edge> getNeighbourEdges(Node node) {
        return this.adjacencyList.get(node);
    }

    private void validateEdge(String firstNodeLabel, String secondNodeLabel, String line, int lineCounter) {
        if (firstNodeLabel.equals(secondNodeLabel)) {
            String exceptionMessage = String.format("The graph cannot contain loops!. Error in line : %d : [%s]", lineCounter, line);

            throw new IllegalStateException(exceptionMessage);
        }
    }

    private void validateReadingLine(String[] splitLine, String currentLine, int lineCounter) {
        if (splitLine.length != 3) {
            String exceptionMessage = String.format("There is wrong line in file [%s] in line: %d", currentLine, lineCounter);
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    private void validateInput(String pathToTheFile) {
        if (pathToTheFile == null) {
            throw new IllegalArgumentException("Path to the file cannot be null!");
        }
    }

    public Node getRoot() {
        return this.adjacencyList.keySet().stream().findFirst().orElseThrow(() -> new IllegalArgumentException("Cannot getRoot vertex from empty graph!"));
    }

    public boolean isEmpty() {
        return this.adjacencyList.isEmpty();
    }
}
