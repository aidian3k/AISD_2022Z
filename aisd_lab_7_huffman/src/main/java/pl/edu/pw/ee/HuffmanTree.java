package pl.edu.pw.ee;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class HuffmanTree {
    private final String pathToRootDir;
    private final HashMap<Character, String> codes;
    private final Node root;

    public HuffmanTree(String pathToRootDir, boolean compress) {
        validatePathToRootDir(pathToRootDir);

        try {
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
                } else if (listOfChars.size() == 1) {
                    this.codes.put(listOfChars.get(0), "0");
                    this.root = rebuildHuffmanTree(null, listOfChars);
                } else {
                    this.root = rebuildHuffmanTree(null, listOfChars);
                    createHuffmanPrefixCodes(this.root, "");
                }
            }
        } catch (IOException fileException) {
            throw new IllegalArgumentException("There is a problem with files in given path!");
        }
    }

    private Node createHuffmanTree(List<Node> listOfNodes) {
        if (listOfNodes.isEmpty()) {
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
        if (listOfChars.size() == 1 && listOfChars.get(0) == '1') {
            return new Node('\n', 1);
        }

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

    public int encodeFile() throws IOException {
        int counter = 0;

        saveHuffmanTreeToFile();

        BufferedReader reader = new BufferedReader(new FileReader(pathToRootDir + "/decompressedFile.txt", StandardCharsets.ISO_8859_1));
        PrintWriter compressWriter = new PrintWriter(new FileWriter(pathToRootDir + "/compressedFile.txt", StandardCharsets.ISO_8859_1));
        int characterReader;

        while ((characterReader = reader.read()) != -1) {
            char singleChar = (char) characterReader;
            String codeForSingleChar = codes.get(singleChar);

            compressWriter.print(codeForSingleChar);
            counter += codeForSingleChar.length();
        }

        reader.close();
        compressWriter.close();

        return counter;
    }

    public int decodeFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(pathToRootDir + "/compressedFile.txt", StandardCharsets.ISO_8859_1));
        PrintWriter decodeWriter = new PrintWriter(new FileWriter(pathToRootDir + "/decompressedFile.txt", StandardCharsets.ISO_8859_1));

        int characterReader;
        int counter = 0;

        if (codes.size() == 1) {
            counter = handleOneCharacterFile(reader, decodeWriter, counter);
            return counter;
        }

        Node currentNode = this.root;

        while ((characterReader = reader.read()) != -1) {
            char singleChar = (char) characterReader;

            if (singleChar == '0') {
                currentNode = currentNode.getLeft();
            } else if (singleChar == '1') {
                currentNode = currentNode.getRight();
            }

            if (currentNode.isLeaf()) {
                char decompressedCharacter = currentNode.getSign();

                decodeWriter.write(decompressedCharacter);
                counter++;
                currentNode = this.root;
            }
        }

        decodeWriter.close();
        reader.close();

        return counter;
    }

    private void saveHuffmanTreeToFile() throws IOException {
        PrintWriter treeWriter = new PrintWriter(new FileWriter(pathToRootDir + "/keys.txt", StandardCharsets.ISO_8859_1));
        String preOrderTraversalResult = traverseHuffmanTreePreOrder();
        treeWriter.print(preOrderTraversalResult);

        treeWriter.close();
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

        return result.toString().trim();
    }

    public Node getRoot() {
        return root;
    }

    public HashMap<Character, String> getCodes() {
        return codes;
    }

    private int handleOneCharacterFile(BufferedReader reader, PrintWriter decodeWriter, int counter) throws IOException {
        while (reader.read() != -1) {
            char decompressedCharacter = root.getSign();

            decodeWriter.write(decompressedCharacter);
            counter++;
        }
        return counter;
    }

    private void validatePathToRootDir(String pathToRootDir) {
        if (pathToRootDir == null) {
            throw new IllegalArgumentException("Path to root dir cannot be null!");
        }
    }

}
