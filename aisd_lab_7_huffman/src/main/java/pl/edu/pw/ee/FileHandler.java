package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileHandler {
    private final BufferedReader reader;

    public FileHandler(String pathToRootDir, boolean compress) throws IOException {
        validateInput(pathToRootDir, compress);

        String pathToFile = compress ? pathToRootDir + "/decompressedFile.txt" : pathToRootDir + "/keys.txt";
        this.reader = new BufferedReader(new FileReader(pathToFile, StandardCharsets.UTF_8));
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

    private void validateInput(String pathToRootDir, boolean compress) throws IOException {
        if (pathToRootDir == null) {
            throw new IllegalArgumentException("PathToRootDir argument cannot be null!");
        }

        File directoryToRead = new File(pathToRootDir);

        if (!directoryToRead.exists()) {
            throw new IllegalArgumentException("Cannot read from directory, which does not exist!");
        }

        if (!directoryToRead.isDirectory()) {
            throw new IllegalArgumentException("Given path must lead to the directory!");
        }

        if (!directoryToRead.canRead()) {
            throw new IllegalArgumentException("Cannot do anything from directory, from which you cannot read!");
        }

        if (compress) {
            validateCompression(pathToRootDir);
        } else {
            validateDecompression(pathToRootDir);
        }
    }

    private void validateCompression(String pathToRootDir) throws IOException {
        File decompressedFile = new File(pathToRootDir + "/decompressedFile.txt");
        File compressedFile = new File(pathToRootDir + "/compressedFile.txt");
        File keysFile = new File(pathToRootDir + "/keys.txt");

        if (!decompressedFile.exists()) {
            throw new IllegalArgumentException("DecompressedFile.txt does not exist in leading directory!");
        }

        if (!decompressedFile.isFile()) {
            throw new IllegalArgumentException("DecompressedFile.txt must be a file!");
        }

        if (!decompressedFile.canRead()) {
            throw new IllegalArgumentException("Cannot compress file, which you cannot read from!");
        }

        if (!keysFile.exists() || !compressedFile.exists()) {
            if (!keysFile.createNewFile() || !compressedFile.createNewFile()) {
                throw new IllegalArgumentException("Cannot create keysFile.txt and compressedFile.txt!");
            }
        }

        if (!keysFile.canWrite() && !compressedFile.canWrite()) {
            throw new IllegalArgumentException("Cannot write to vital files when compressing!");
        }
    }

    private void validateDecompression(String pathToRootDir) throws IOException {
        File decompressedFile = new File(pathToRootDir + "/decompressedFile.txt");
        File compressedFile = new File(pathToRootDir + "/compressedFile.txt");
        File keysFile = new File(pathToRootDir + "/keys.txt");


        if(!keysFile.exists() || !compressedFile.exists()) {
            throw new IllegalArgumentException("Decompression cannot be done without keys and decompressed files");
        }

        if(!keysFile.canRead() || !compressedFile.canRead()) {
            throw new IllegalArgumentException("Cannot read from keys and compressedFile when decompressing!");
        }

        if(!decompressedFile.exists()) {
            if(decompressedFile.createNewFile()) {
                throw new IllegalArgumentException("Cannot create decompressedFile.txt!");
            }
        }

    }
}
