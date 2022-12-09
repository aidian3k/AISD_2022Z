package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private BufferedReader decompressReader;
    private BufferedReader compressReader;
    private PrintWriter writer;

    public FileHandler(String pathToCompressFile) throws IOException {
        this.compressReader = new BufferedReader(new FileReader(pathToCompressFile, StandardCharsets.UTF_8));
    }

    public List<Node> readFrequencyOfSingleCharacters() throws IOException {
        List<Node> currentChars = new ArrayList<>();
        int characterReader;

        while ((characterReader = compressReader.read()) != -1) {
            char singleChar = (char) characterReader;
            Node properNode = findProperNodeForChar(singleChar, currentChars);

            if (properNode == null) {
                Node newNode = new Node(singleChar, 1);
                currentChars.add(newNode);
            } else {
                properNode.increaseFrequency();
            }
        }

        compressReader.close();

        return currentChars;
    }

    private Node findProperNodeForChar(char singleChar, List<Node> currentChars) {
        Node properNode = null;

        for (Node node : currentChars) {
            char currentChar = node.getSign();

            if (singleChar == currentChar) {
                properNode = node;
                break;
            }
        }

        return properNode;
    }
}
