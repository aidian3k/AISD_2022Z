package pl.edu.pw.ee.huffmanCoding;

import pl.edu.pw.ee.Constants;
import pl.edu.pw.ee.fileHandling.BitOutputStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class HuffmanEncoder {
    private final HuffmanTree huffmanTree;
    private final Constants constants = Constants.getInstance();

    public HuffmanEncoder(HuffmanTree huffmanTree) {
        validateHuffmanTree(huffmanTree);
        this.huffmanTree = huffmanTree;
    }

    public int encodeFile() {
        int counter = 0;

        HashMap<Character, String> codes = huffmanTree.getCodes();
        String pathToRootDir = huffmanTree.getPathToRootDir();
        String readerFilePath = constants.getDecompressedFilePath(pathToRootDir);
        String compressWriterFilePath = constants.getCompressedFilePath(pathToRootDir);

        huffmanTree.saveHuffmanTreeToFile();

        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(readerFilePath));
            BitOutputStream compressWriter = new BitOutputStream(new FileOutputStream(compressWriterFilePath));

            int characterReader;
            StringBuilder compressedCode = new StringBuilder();


            while ((characterReader = reader.read()) != -1) {
                char singleChar = (char) characterReader;
                String codeForSingleChar = codes.get(singleChar);

                compressedCode.append(codeForSingleChar);
                counter += codeForSingleChar.length();
            }

            int remainingBits = calculateRemainingBits(counter);

            String remainingBitsBinary = getNumberOfRemainingBitsInBinarySystem(remainingBits);

            writeBinaryCodeToFile(compressWriter, remainingBitsBinary);
            writeBinaryCodeToFile(compressWriter, compressedCode.toString());

            reader.close();
            compressWriter.close();

            return counter + remainingBits + constants.BITS_TO_CODE_REMAINING_BITS;

        } catch (IOException fileException) {
            throw new IllegalStateException("There has been a problem with file while encoding the file!");
        }
    }

    private int calculateRemainingBits(int counter) {
        int remainingBits = constants.NUMBER_OF_BITS_IN_BYTE
                - (counter + constants.BITS_TO_CODE_REMAINING_BITS) % Constants.getInstance().NUMBER_OF_BITS_IN_BYTE;

        if (remainingBits == 8) {
            remainingBits = 0;
        }

        return remainingBits;
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

    private String getNumberOfRemainingBitsInBinarySystem(int remainingBits) {
        return String.format("%3s", Integer.toBinaryString(remainingBits)).replaceAll(" ", "0");
    }

    private void validateHuffmanTree(HuffmanTree huffmanTree) {
        if (huffmanTree == null) {
            throw new IllegalArgumentException("Huffman tree cannot be null!");
        }
    }
}
