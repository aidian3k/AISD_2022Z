package pl.edu.pw.ee;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class HuffmanTree {
    private final HashMap<Character, String> codes;
    private final String pathToRootDir;
    private Node root;

    public HuffmanTree(List<Node> listOfNodes, String pathToRootDir) {
        validateConstructorInput(listOfNodes, pathToRootDir);

        this.pathToRootDir = pathToRootDir;
        codes = new HashMap<>();
        createHuffmanTree(listOfNodes);

        createHuffmanPrefixCodes(this.root, "");
    }

    public HuffmanTree(HashMap<Character, String> codes, List<Character> listOfChars, String pathToRootDir) {
        validatePathToRootDir(pathToRootDir);

        this.codes = codes;
        this.pathToRootDir = pathToRootDir;

        rebuildHuffmanTree(listOfChars);
    }

    private void createHuffmanTree(List<Node> listOfNodes) {
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

        this.root = heap.pop();
    }

    private void rebuildHuffmanTree(List<Character> listOfChars) {
        validateHuffmanRebuild(listOfChars);

        this.root = new Node();
        Node currentNode = root;

        for (char singleChar : listOfChars) {
            String correspondingCode = codes.get(singleChar);
            char[] codeCharArray = correspondingCode.toCharArray();
            char lastCodeCharacter = codeCharArray[codeCharArray.length - 1];

            for (int i = 0; i < codeCharArray.length - 1; ++i) {
                char codeCharacter = codeCharArray[i];

                if (codeCharacter == '0' && currentNode.getLeft() == null) {
                    currentNode.setLeft(new Node());
                    currentNode = currentNode.getLeft();
                } else if (codeCharacter == '0' && currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else if (codeCharacter == '1' && currentNode.getRight() == null) {
                    currentNode.setRight(new Node());
                    currentNode = currentNode.getRight();
                } else {
                    currentNode = currentNode.getRight();
                }
            }

            Node newLeaf = new Node(singleChar, 1);

            if (lastCodeCharacter == '0') {
                currentNode.setLeft(newLeaf);
            } else {
                currentNode.setRight(newLeaf);
            }

            currentNode = root;
        }

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
        validateRootNode(root);

        int counter = 0;

        saveHuffmanTreeToFile();
        BufferedReader reader = new BufferedReader(new FileReader(pathToRootDir + "/sampleFile.txt", StandardCharsets.UTF_8));
        PrintWriter compressWriter = new PrintWriter(new FileWriter(pathToRootDir + "/compressedFile.txt", StandardCharsets.UTF_8));
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
        validateRootNode(root);

        BufferedReader reader = new BufferedReader(new FileReader(pathToRootDir + "/compressedFile.txt", StandardCharsets.UTF_8));
        PrintWriter decodeWriter = new PrintWriter(new FileWriter(pathToRootDir + "/decompressedFile.txt", StandardCharsets.UTF_8));

        int characterReader;
        int counter = 0;

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
        validateRootNode(root);
        PrintWriter treeWriter = new PrintWriter(new FileWriter(pathToRootDir + "/keys.txt"));

        for (Character character : codes.keySet()) {
            String code = codes.get(character);
            treeWriter.println((int) character + " " + code);
        }

        treeWriter.close();
    }

    public Node getRoot() {
        return root;
    }

    public HashMap<Character, String> getCodes() {
        return codes;
    }

    private void validatePathToRootDir(String pathToRootDir) {
        if (pathToRootDir == null) {
            throw new IllegalArgumentException("Path to root dir cannot be null!");
        }
    }

    private void validateRootNode(Node root) {
        if (root == null) {
            throw new IllegalArgumentException("Root node cannot be null!");
        }
    }

    private void validateConstructorInput(List<Node> listOfNodes, String pathToRootDir) {
        validatePathToRootDir(pathToRootDir);
        if (listOfNodes == null || listOfNodes.size() == 0) {
            throw new IllegalArgumentException("List of nodes and pathToRootDir cannot be null or have size of 0");
        }
    }

    private void validateHuffmanRebuild(List<Character> listOfChars) {
        if (codes == null || listOfChars == null) {
            throw new IllegalArgumentException("Codes and listOfChars cannot be null when rebuilding the tree!");
        }
    }
}
