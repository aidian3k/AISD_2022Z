package pl.edu.pw.ee.graph;

import pl.edu.pw.ee.exception.ReadingGraphFromFileException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;

public class GraphReader {

    private final String lineRegex = "^\\S+ \\S+ \\d+$";
    private final Pattern pattern = Pattern.compile(lineRegex);
    private final String fileToPath;
    private List<Edge> edges;
    private List<Node> nodes;

    public GraphReader(String fileToPath, boolean isNormal) {
        validateData(fileToPath);

        this.fileToPath = fileToPath;
        this.edges = new ArrayList<>();
        this.nodes = new ArrayList<>();

        readAndSort(isNormal);
    }

    public List<Edge> getEdges() {
        return edges;
    }

    private void validateData(String fileToPath) {
        if (fileToPath == null) {
            throw new IllegalArgumentException("File to path arg cannot be null!");
        }
    }

    private void readAndSort(boolean isNormal) {
        if(isNormal) {
            readGraphFromFile();
        } else {
            readExtendedGraph();
        }
       
        sortEdgesByPriority();
    }

    private void readExtendedGraph() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileToPath));
            readNodesFromFile(reader);
            createEdgesFromNodes(reader);
            reader.close();
        } catch (IOException exception) {
            throw new IllegalArgumentException("There is file exception!");
        }
    }
    
    private void readGraphFromFile() {
        Edge edge;
        int i = 1;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileToPath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                edge = parseToEdge(line, i);

                checkForEdgeExistence(edge);

                edges.add(edge);
                i++;
            }
        } catch (IOException e) {
            throw new ReadingGraphFromFileException("Cannot read file from path!", e);
        }
    }

    private void createEdgesFromNodes(BufferedReader bufferedReader) throws IOException {
        String line;
        int lineNumber = 1;

        while ((line = bufferedReader.readLine()) != null) {
            Edge edge = parseExtendedEdge(line, lineNumber);

            checkForEdgeExistence(edge);

            edges.add(edge);
            lineNumber++;
        }
    }

    private Edge parseExtendedEdge(String line, int lineNumber) {
        try {
            String[] args = line.split(" ");
            String firstNodeName = args[0];
            String secondNodeName = args[1];

            Node firstNode = new Node(firstNodeName);
            Node secondNode = new Node(secondNodeName);

            return new Edge(firstNode, secondNode, Points.calculateNodeDistance(firstNode, secondNode, nodes));
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new IllegalArgumentException("There is a wrong line in line number:" + lineNumber);
        }
    }

    private void readNodesFromFile(BufferedReader bufferedReader) throws IOException {
        String line;
        int lineNumber = 1;

        while ((line = bufferedReader.readLine()) != null) {
            if (line.equals("")) {
                break;
            }

            Node node = parseLineToNode(line, lineNumber);

            if (checkForNodeExistence(node)) {
                nodes.remove(node);
            }

            nodes.add(node);
            lineNumber++;
        }

    }

    public int getNodesNames(List<String> nodeNames) {
        HashSet<String> hashSet = new HashSet<>();

        for(Edge edge : edges) {
            String currentSrcName = edge.getSrc().getName();
            String currentDstName = edge.getDest().getName();
            hashSet.add(currentDstName);
            hashSet.add(currentSrcName);
        }

        nodeNames.addAll(hashSet);

        return hashSet.size();
    }

    private boolean checkForNodeExistence(Node comparingNode) {
        for (Node node : nodes) {
            if (node.equals(comparingNode)) {
                return true;
            }
        }

        return false;
    }

    private Node parseLineToNode(String line, int lineNumber) {
        String[] args;
        int x;
        int y;
        String name;

        Matcher matcher = pattern.matcher(line);

        if (matcher.find()) {
            args = line.split(" ");
            x = Integer.parseInt(args[1]);
            y = Integer.parseInt(args[2]);
            name = args[0];

            return new Node(name, new Points(x, y));

        } else {
            throw new ReadingGraphFromFileException(format("Cannot correctly parse line %d from file", lineNumber));
        }
    }

    private void checkForEdgeExistence(Edge comparingEdge) {
        for(int i = 0; i < edges.size(); ++i) {
            if(edges.get(i).equals(comparingEdge)) {
                edges.remove(i);
            }
        }
    }

    private Edge parseToEdge(String line, int lineNumber) {
        String[] args;
        Node start;
        Node end;
        int weight;

        Matcher matcher = pattern.matcher(line);

        if (matcher.find()) {
            args = line.split(" ");
            start = new Node(args[0]);
            end = new Node(args[1]);
            weight = Integer.parseInt(args[2]);

            return new Edge(start, end, weight);

        } else {
            throw new ReadingGraphFromFileException(format("Cannot correctly parse line %d from file", lineNumber));
        }
    }

    private void sortEdgesByPriority() {
        EdgeWeightComparator weightComparator = new EdgeWeightComparator();

        edges.sort(weightComparator);
    }

}
