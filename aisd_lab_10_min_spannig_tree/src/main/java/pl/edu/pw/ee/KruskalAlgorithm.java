package pl.edu.pw.ee;

import pl.edu.pw.ee.heap.Heap;
import pl.edu.pw.ee.services.MinSpanningTree;

import java.util.List;

public class KruskalAlgorithm implements MinSpanningTree {
    private Graph graph;

    public String findMST(String pathToFile) {
        this.graph = new Graph(pathToFile);
        StringBuilder minimumSpanningTreeResult = new StringBuilder();

        if (graph.isEmpty()) {
            return minimumSpanningTreeResult.toString();
        }

        Heap<Edge> priorityQueue = new Heap<>();
        int minimumSpanningTreeNodes = 1;
        addEdgesToPriorityQueue(priorityQueue);

        while (priorityQueue.getHeapSize() != 0) {
            Edge minimumWeightEdge = priorityQueue.pop();

            Node startingNodeParent = minimumWeightEdge.getStartNode().getParentNode();
            Node endingNodeParent = minimumWeightEdge.getEndNode().getParentNode();

            if (!startingNodeParent.equals(endingNodeParent)) {
                startingNodeParent.setParent(endingNodeParent);

                minimumSpanningTreeNodes++;
                minimumSpanningTreeResult.append(minimumWeightEdge).append("|");
            }
        }

        checkCohesion(minimumSpanningTreeNodes);

        int resultStringLength = minimumSpanningTreeResult.length();

        return minimumSpanningTreeResult.substring(0, resultStringLength - 1);
    }

    public void checkCohesion(int minimumSpanningTreeNodes) {
        if (minimumSpanningTreeNodes != graph.getNumberOfNodes()) {
            throw new IllegalStateException("In order to get minimumSpanningTree  the graph must be coherent!");
        }
    }

    public void addEdgesToPriorityQueue(Heap<Edge> priorityQueue) {
        for (List<Edge> adjacencyList : graph.getAdjacencyList().values()) {
            for (Edge edge : adjacencyList) {
                priorityQueue.put(edge);
            }
        }
    }
}
