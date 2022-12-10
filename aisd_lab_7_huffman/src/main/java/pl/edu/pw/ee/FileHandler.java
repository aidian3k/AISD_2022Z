package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileHandler {
    private final BufferedReader reader;

    public FileHandler(String pathToRootDir, boolean compress) throws IOException {
        String nameOfFile = compress ? pathToRootDir + "/sampleFile.txt" : pathToRootDir + "/keys.txt";
        this.reader = new BufferedReader(new FileReader(nameOfFile, StandardCharsets.UTF_8));
    }

    public List<Node> readFrequencyOfSingleCharacters() throws IOException {
        List<Node> currentChars = new ArrayList<>();
        int characterReader;

        while ((characterReader = reader.read()) != -1) {
            char singleChar = (char) characterReader;
            Node properNode = findProperNodeForChar(singleChar, currentChars);

            if (properNode == null) {
                Node newNode = new Node(singleChar, 1);
                currentChars.add(newNode);
            } else {
                properNode.increaseFrequency();
            }
        }

        reader.close();

        return currentChars;
    }

    public HashMap<Character, String> readCharacterCodesFromFile(List<Character> listOfChars) throws IOException {
        String line;
        HashMap<Character, String> codes = new HashMap<>();

        while ((line = reader.readLine()) != null) {
            String[] splitLine = line.split(" ");

            char currentCharacter = (char) Integer.parseInt(splitLine[0]);
            String correspondingCode = splitLine[1];

            listOfChars.add(currentCharacter);
            codes.put(currentCharacter, correspondingCode);
        }

        return codes;
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
