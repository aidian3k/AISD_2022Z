package pl.edu.pw.ee.huffmanCoding;

import pl.edu.pw.ee.Constants;
import pl.edu.pw.ee.fileHandling.FileHandler;
import pl.edu.pw.ee.heap.Heap;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;

public class HuffmanTree {
    private final String pathToRootDir;
    private final HashMap<Character, String> codes;
    private final Node root;
    private final Constants constants = Constants.getInstance();

    public HuffmanTree(String pathToRootDir, boolean compress) {
        validatePathToRootDir(pathToRootDir);

        FileHandler reader = new FileHandler(pathToRootDir, compress);

        this.pathToRootDir = pathToRootDir;
        this.codes = new HashMap<>();

        if (compress) {
            List<Node> nodeList = reader.readFrequencyOfSingleCharacters();
            this.root = createHuffmanTree(nodeList);

            if (nodeList.size() == 1) {
                this.codes.put(nodeList.get(0).getSign(), "0");
            } else {
                createHuffmanPrefixCodes(this.root, "");
            }
        } else {
            List<Character> listOfChars = reader.readCharactersFromFile();

            if (listOfChars.stream().distinct().count() == 2) {
                this.codes.put(listOfChars.get(1), "0");
                this.root = rebuildHuffmanTree(null, listOfChars);
            } else {
                this.root = rebuildHuffmanTree(null, listOfChars);
                createHuffmanPrefixCodes(this.root, "");
            }
        }
    }

    private Node createHuffmanTree(List<Node> listOfNodes) {
        if (listOfNodes.size() == 0) {
            return null;
        }

        if (listOfNodes.size() == 1) {
            char singleCharacter = listOfNodes.get(0).getSign();
            this.codes.put(singleCharacter, "0");
            return new Node(singleCharacter, listOfNodes.get(0).getFrequency());
        }

        Heap<Node> heap = new Heap<>();

        for (Node node : listOfNodes) {
            heap.put(node);
        }

        while (heap.getHeapSize() > 1) {
            Node firstMinFreqNode = heap.pop();
            Node secondMinFreqNode = heap.pop();

            Node newNode = new Node(firstMinFreqNode.getFrequency() + secondMinFreqNode.getFrequency(), firstMinFreqNode, secondMinFreqNode);

            heap.put(newNode);
        }

        return heap.pop();
    }

    private Node rebuildHuffmanTree(Node currentNode, List<Character> listOfChars) {
        if (listOfChars.size() > 0) {
            char currentCharacter = listOfChars.get(0);
            listOfChars.remove(0);

            if (currentCharacter == '0') {
                Node newInternalNode = new Node();

                newInternalNode.setLeft(rebuildHuffmanTree(newInternalNode, listOfChars));
                newInternalNode.setRight(rebuildHuffmanTree(newInternalNode, listOfChars));

                return newInternalNode;
            } else {
                char currentSign = listOfChars.get(0);
                listOfChars.remove(0);

                return new Node(currentSign, 1);
            }
        }

        return currentNode;
    }

    private void createHuffmanPrefixCodes(Node head, String currentCode) {
        if (head != null) {
            if (head.getSign() != null) {
                codes.put(head.getSign(), currentCode);
            }

            createHuffmanPrefixCodes(head.getLeft(), currentCode + "0");
            createHuffmanPrefixCodes(head.getRight(), currentCode + "1");
        }
    }

    public void saveHuffmanTreeToFile() {
        try {
            OutputStreamWriter treeWriter = new OutputStreamWriter(new FileOutputStream(constants.getKeysFilePath(pathToRootDir)));
            String preOrderTraversalResult = traverseHuffmanTreePreOrder();
            treeWriter.write(preOrderTraversalResult);

            treeWriter.close();
        } catch (IOException fileException) {
            throw new IllegalStateException("There is a problem with saving huffmanTree to keys.txt file!");
        }
    }

    public String traverseHuffmanTreePreOrder() {
        StringBuilder result = new StringBuilder();
        return preOderTraversal(result, root);
    }

    private String preOderTraversal(StringBuilder result, Node head) {
        if (head != null) {
            if (head.isLeaf()) {
                result.append("1").append(head.getSign());
            } else {
                result.append("0");
            }

            preOderTraversal(result, head.getLeft());
            preOderTraversal(result, head.getRight());
        }

        return result.toString();
    }

    public Node getRoot() {
        return root;
    }

    public String getPathToRootDir() {
        return this.pathToRootDir;
    }

    public HashMap<Character, String> getCodes() {
        return codes;
    }

    private void validatePathToRootDir(String pathToRootDir) {
        if (pathToRootDir == null) {
            throw new IllegalArgumentException("Path to root dir cannot be null!");
        }
    }

}
