package pl.edu.pw.ee;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private final InputStreamReader reader;
    private final boolean isCompressing;

    public FileHandler(String pathToRootDir, boolean compress) {
        validateInput(pathToRootDir, compress);

        try {
            this.isCompressing = compress;
            String pathToFile = compress ? pathToRootDir + "/decompressedFile.txt" : pathToRootDir + "/keys.txt";
            this.reader = new InputStreamReader(new FileInputStream(pathToFile));
        } catch (IOException fileException) {
            throw new IllegalArgumentException("There is a problem with initializing the reader file!");
        }
    }

    public List<Node> readFrequencyOfSingleCharacters() {
        validateCompressionUsage();

        List<Node> currentChars = new ArrayList<>();
        int characterReader;

        try {
            while ((characterReader = reader.read()) != -1) {
                char singleChar = (char) characterReader;
                validateSingleCharacter(singleChar);
                Node properNode = findProperNodeForChar(singleChar, currentChars);

                if (properNode == null) {
                    Node newNode = new Node(singleChar, 1);
                    currentChars.add(newNode);
                } else {
                    properNode.increaseFrequency();
                }
            }

            reader.close();
        } catch (IOException fileException) {
            throw new IllegalStateException("There is a problem with file when reading the frequency of letters!");
        }

        return currentChars;
    }

    public List<Character> readCharactersFromFile() {
        validateDecompressionUsage();

        List<Character> listOfChars = new ArrayList<>();
        int characterReader;

        try {
            while ((characterReader = reader.read()) != -1) {
                char currentCharacter = (char) characterReader;
                listOfChars.add(currentCharacter);
            }
        } catch (IOException fileException) {
            throw new IllegalStateException("There is a problem with file when reading characters!");
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

    private void validateSingleCharacter(char singleCharacter) {
        if (singleCharacter >= 128) {
            throw new IllegalArgumentException("Characters in compression must be in " +
                    "basic ASCII table standard! In the file there is a " + singleCharacter + " character");
        }
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
