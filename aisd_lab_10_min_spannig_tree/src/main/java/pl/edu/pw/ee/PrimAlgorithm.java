package pl.edu.pw.ee;

import pl.edu.pw.ee.services.MinSpanningTree;

public class PrimAlgorithm implements MinSpanningTree {
    public String findMST(String pathToFile){
        Graph graph = new Graph(pathToFile);
        boolean[] visitedLabels = new boolean[graph.getNumberOfNodes()];

        return null;
    }

}
