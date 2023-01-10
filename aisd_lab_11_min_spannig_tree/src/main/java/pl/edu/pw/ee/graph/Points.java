package pl.edu.pw.ee.graph;

import java.util.List;

public class Points {
    private final int x;
    private final int y;

    public Points(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static double calculateNodeDistance(Node firstNode, Node secondNode, List<Node> nodes) {
        if (checkForFirstAndSecondNodeExistence(firstNode, secondNode, nodes)) {
            Node first = nodes.stream().filter(node -> node.equals(firstNode)).findFirst().get();
            Node second = nodes.stream().filter(node -> node.equals(secondNode)).findFirst().get();

            return calculateDistance(first.getCoordinates(), second.getCoordinates());
        } else {
            throw new IllegalArgumentException("One of nodes does not exist in nodes universe!");
        }
    }

    private static boolean checkForFirstAndSecondNodeExistence(Node firstNode, Node secondNode, List<Node> nodes) {
        int firstNodeExistence = 0;
        int secondNodeExistence = 0;

        for (Node node : nodes) {
            if (node.equals(firstNode)) {
                firstNodeExistence = 1;
            }

            if (node.equals(secondNode)) {
                secondNodeExistence = 1;
            }
        }

        return firstNodeExistence * secondNodeExistence == 1;
    }

    private static double calculateDistance(Points firstCoordinates, Points secondCoordinates) {
        int xDistance = firstCoordinates.getX() - secondCoordinates.getX();
        int yDistance = firstCoordinates.getY() - secondCoordinates.getY();

        return Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

