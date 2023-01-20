package pl.edu.pw.ee;

import pl.edu.pw.ee.heap.Heap;
import pl.edu.pw.ee.services.MinSpanningTree;

import java.util.List;

public class PrimAlgorithm implements MinSpanningTree {
    private Graph graph;

    public String findMST(String pathToFile) {
        this.graph = new Graph(pathToFile);
        StringBuilder minimumSpanningTreeResult = new StringBuilder();

        if (graph.isEmpty()) {
            return minimumSpanningTreeResult.toString();
        }

        Node startingNode = graph.getRoot();
        Heap<Edge> priorityQueue = new Heap<>();
        startingNode.setVisited();
        int minimumSpanningTreeNodes = 1;
        addNeighboursToPriorityQueue(priorityQueue, startingNode);

        while (priorityQueue.getHeapSize() != 0) {
            Edge minimumEdge = priorityQueue.pop();

            if (!minimumEdge.getEndNode().isVisited()) {
                Node edgeEndNode = minimumEdge.getEndNode();
                edgeEndNode.setVisited();
                addNeighboursToPriorityQueue(priorityQueue, edgeEndNode);

                minimumSpanningTreeNodes++;
                minimumSpanningTreeResult.append(minimumEdge).append("|");
            }
        }

        checkCohesion(minimumSpanningTreeNodes);

        int resultStringLength = minimumSpanningTreeResult.length();

        return minimumSpanningTreeResult.substring(0, resultStringLength - 1);
    }

    public void checkCohesion(int minimumSpanningTreeNodes) {
        if (minimumSpanningTreeNodes != graph.getNumberOfNodes()) {
            throw new IllegalStateException("In order to get minimumSpanningTree the graph must be coherent!");
        }
    }

    private void addNeighboursToPriorityQueue(Heap<Edge> priorityQueue, Node startingNode) {
        List<Edge> neighbourEdges = graph.getNeighbourEdges(startingNode);

        for (Edge edge : neighbourEdges) {
            priorityQueue.put(edge);
        }
    }

}
