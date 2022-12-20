package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private final BufferedReader reader;
    private final boolean isCompressing;

    public FileHandler(String pathToRootDir, boolean compress) throws IOException {
        validateInput(pathToRootDir, compress);

        this.isCompressing = compress;
        String pathToFile = compress ? pathToRootDir + "/decompressedFile.txt" : pathToRootDir + "/keys.txt";
        this.reader = new BufferedReader(new FileReader(pathToFile, StandardCharsets.ISO_8859_1));
    }

    public List<Node> readFrequencyOfSingleCharacters() throws IOException {
        validateCompressionUsage();

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

    public List<Character> readCharactersFromFile() throws IOException {
        validateDecompressionUsage();

        List<Character> listOfChars = new ArrayList<>();
        int characterReader;

        while ((characterReader = reader.read()) != -1) {
            char currentCharacter = (char) characterReader;
            listOfChars.add(currentCharacter);
        }

        return listOfChars;
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

    private void validateCompressionUsage() {
        if (!isCompressing) {
            throw new IllegalArgumentException("This method is only accessible when compressingFile!");
        }
    }

    private void validateDecompressionUsage() {
        if (isCompressing) {
            throw new IllegalArgumentException("This method is only accessible when decompressingFile!");
        }
    }

    private void validateInput(String pathToRootDir, boolean compress) {
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

    private void validateCompression(String pathToRootDir) {
        File decompressedFile = new File(pathToRootDir + "/decompressedFile.txt");

        if (!decompressedFile.exists()) {
            throw new IllegalArgumentException("DecompressedFile.txt does not exist in leading directory!");
        }

        if (!decompressedFile.isFile()) {
            throw new IllegalArgumentException("DecompressedFile.txt must be a file!");
        }

        if (!decompressedFile.canRead()) {
            throw new IllegalArgumentException("Cannot compress file, which you cannot read from!");
        }

    }

    private void validateDecompression(String pathToRootDir) {
        File compressedFile = new File(pathToRootDir + "/compressedFile.txt");
        File keysFile = new File(pathToRootDir + "/keys.txt");

        if (!keysFile.exists() || !compressedFile.exists()) {
            throw new IllegalArgumentException("Decompression cannot be done without keys and compressed files");
        }

        if (!keysFile.canRead() || !compressedFile.canRead()) {
            throw new IllegalArgumentException("Cannot read from keys and compressedFile when decompressing!");
        }
    }
}
