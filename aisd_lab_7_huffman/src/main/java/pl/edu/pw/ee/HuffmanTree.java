package pl.edu.pw.ee;

import java.io.*;
import java.util.HashMap;
import java.util.List;

public class HuffmanTree {
    private final String pathToRootDir;
    private final HashMap<Character, String> codes;
    private final Node root;

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
            } else if (listOfChars.size() == 1) {
                this.codes.put(listOfChars.get(0), "0");
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

        InputStreamReader reader = new InputStreamReader(new FileInputStream(pathToRootDir + "/decompressedFile.txt"));
        BitOutputStream compressWriter = new BitOutputStream(new FileOutputStream(pathToRootDir + "/compressedFile.txt"));

        int characterReader;
        StringBuilder compressedCode = new StringBuilder();

        while ((characterReader = reader.read()) != -1) {
            char singleChar = (char) characterReader;
            String codeForSingleChar = codes.get(singleChar);

            compressedCode.append(codeForSingleChar);
            counter += codeForSingleChar.length();
        }

        int remainingBits = 8 - (counter + 3) % 8;

        if (remainingBits == 8) {
            remainingBits = 0;
        }

        String remainingBitsBinary = String.format("%3s", Integer.toBinaryString(remainingBits)).replaceAll(" ", "0");

        writeBinaryCodeToFile(compressWriter, remainingBitsBinary);
        writeBinaryCodeToFile(compressWriter, compressedCode.toString());

        reader.close();
        compressWriter.close();

        return counter + remainingBits + 3;
    }

    private void writeBinaryCodeToFile(BitOutputStream compressWriter, String codeForSingleChar) {
        try {
            for (char character : codeForSingleChar.toCharArray()) {
                if (character == '1') {
                    compressWriter.write(1);
                } else {
                    compressWriter.write(0);
                }
            }
        } catch (IOException fileException) {
            throw new IllegalStateException("There is a problem with byte file when decoding!");
        }
    }

    public int decodeFile() throws IOException {
        BitInputStream reader = new BitInputStream(new FileInputStream(pathToRootDir + "/compressedFile.txt"));
        OutputStreamWriter decodeWriter = new OutputStreamWriter(new FileOutputStream(pathToRootDir + "/decompressedFile.txt"));

        String code = readByteCodeFromFile(reader);
        int counter = 0;
        int remainingBits = Integer.parseInt(code.substring(0, 3), 2);

        Node currentNode = this.root;
        code = code.substring(3, code.length() - remainingBits);

        if (codes.size() == 1) {
            counter = handleOneCharacterFile(code, decodeWriter, counter);
            return counter;
        }

        for (char singleChar : code.toCharArray()) {
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

    private String readByteCodeFromFile(BitInputStream reader) {
        StringBuilder encodedFile = new StringBuilder();

        int characterReader;

        try {
            while ((characterReader = reader.read()) != -1) {
                if (characterReader == 0) {
                    encodedFile.append('0');
                } else {
                    encodedFile.append('1');
                }
            }
        } catch (IOException fileException) {
            throw new IllegalArgumentException("There is a problem with reading bytes from the file!");
        }

        return encodedFile.toString();
    }

    private void saveHuffmanTreeToFile() {
        try {
            OutputStreamWriter treeWriter = new OutputStreamWriter(new FileOutputStream(pathToRootDir + "/keys.txt"));
            String preOrderTraversalResult = traverseHuffmanTreePreOrder();
            treeWriter.write(preOrderTraversalResult);

            treeWriter.close();
        } catch (IOException fileException) {
            throw new IllegalArgumentException("There is a problem with saving huffmanTree to keys.txt file!");
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

        return result.toString().trim();
    }

    public Node getRoot() {
        return root;
    }

    public HashMap<Character, String> getCodes() {
        return codes;
    }

    private int handleOneCharacterFile(String code, OutputStreamWriter decodeWriter, int counter) {
        try {
            for (int i = 0; i < code.length(); i++) {
                char decompressedCharacter = root.getSign();

                decodeWriter.write(decompressedCharacter);
                counter++;
            }
        } catch (IOException exception) {
            throw new IllegalArgumentException("There is a problem with reading characters in the file!");
        }

        return counter;
    }

    private void validatePathToRootDir(String pathToRootDir) {
        if (pathToRootDir == null) {
            throw new IllegalArgumentException("Path to root dir cannot be null!");
        }
    }

}
