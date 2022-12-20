package pl.edu.pw.ee.huffmanCoding;

import pl.edu.pw.ee.Constants;
import pl.edu.pw.ee.fileHandling.BitInputStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class HuffmanDecoder {
    private final HuffmanTree huffmanTree;
    private final Constants constants = Constants.getInstance();

    public HuffmanDecoder(HuffmanTree huffmanTree) {
        validateHuffmanTree(huffmanTree);
        this.huffmanTree = huffmanTree;
    }

    public int decodeFile() {
        if (huffmanTree.getRoot() == null) {
            return 0;
        }

        String pathToRootDir = huffmanTree.getPathToRootDir();
        String readerFilePath = constants.getCompressedFilePath(pathToRootDir);
        String decodeWriterFilePath = constants.getDecompressedFilePath(pathToRootDir);
        HashMap<Character, String> codes = huffmanTree.getCodes();

        try {
            BitInputStream reader = new BitInputStream(new FileInputStream(readerFilePath));
            OutputStreamWriter decodeWriter = new OutputStreamWriter(new FileOutputStream(decodeWriterFilePath));

            String code = readByteCodeFromFile(reader);
            int counter = 0;
            int remainingBits = Integer.parseInt(code.substring(0, constants.BITS_TO_CODE_REMAINING_BITS), 2);

            Node currentNode = this.huffmanTree.getRoot();
            code = code.substring(constants.BITS_TO_CODE_REMAINING_BITS, code.length() - (remainingBits + 1));

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
                    currentNode = huffmanTree.getRoot();
                }
            }

            decodeWriter.close();
            reader.close();

            return counter;
        } catch (IOException fileException) {
            throw new IllegalStateException("There has been problem with files when decoding!");
        }
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

    private int handleOneCharacterFile(String code, OutputStreamWriter decodeWriter, int counter) throws IOException{
        for (int i = 0; i < code.length(); i++) {
            char decompressedCharacter = huffmanTree.getRoot().getSign();

            decodeWriter.write(decompressedCharacter);
            counter++;
        }

        decodeWriter.close();

        return counter;
    }

    private void validateHuffmanTree(HuffmanTree huffmanTree) {
        if (huffmanTree == null) {
            throw new IllegalArgumentException("Huffman tree cannot be null!");
        }
    }
}
