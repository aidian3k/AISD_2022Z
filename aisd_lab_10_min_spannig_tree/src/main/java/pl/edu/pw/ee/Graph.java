package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Graph {
    private HashMap<Character, List<Edge>> adjacencyList;
    private int numberOfNodes;

    public Graph(String pathToTheFile) {
        validateInput(pathToTheFile);

        this.adjacencyList = new HashMap<>();
        this.readGraphFromFile(pathToTheFile);
    }

    private void readGraphFromFile(String pathToTheFile) {
        int expectedLineLength = 5;
        int firstNodeLabelPosition = 0;
        int secondNodeLabelPosition = 2;
        int weightNumberPosition = 4;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathToTheFile));
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                validateLengthOfLine(expectedLineLength, currentLine);

                char firstNode = currentLine.charAt(firstNodeLabelPosition);
                char secondNode = currentLine.charAt(secondNodeLabelPosition);
                int weight = currentLine.charAt(weightNumberPosition) - '0';
                Edge newEdge = new Edge(firstNode, secondNode, weight);

                if (adjacencyList.containsKey(firstNode)) {
                    adjacencyList.get(firstNode).add(newEdge);
                } else {
                    this.numberOfNodes++;
                    adjacencyList.put(firstNode, new LinkedList<>());
                    adjacencyList.get(firstNode).add(newEdge);
                }
            }
        } catch (IOException fileException) {
            throw new IllegalStateException("There has been a problem while reading the graph!");
        }
    }

    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    private void validateLengthOfLine(int expectedLineLength, String currentLine) {
        if (currentLine.length() != expectedLineLength) {
            throw new IllegalArgumentException("There is wrong line in file [" + currentLine + "]");
        }
    }

    private void validateInput(String pathToTheFile) {
        if (pathToTheFile == null) {
            throw new IllegalArgumentException("Path to the file cannot be null!");
        }
    }
}
