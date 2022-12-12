package pl.edu.pw.ee;

import java.io.IOException;

public class Huffman {
    public int huffman(String pathToRootDir, boolean compress) {
        HuffmanTree huffmanTree;
        int counter;

        try {
            if (compress) {
                huffmanTree = new HuffmanTree(pathToRootDir, compress);
                counter = huffmanTree.encodeFile();
            } else {
                huffmanTree = new HuffmanTree(pathToRootDir, compress);
                counter = huffmanTree.decodeFile();
            }

            return counter;
        } catch (IOException fileException) {
            throw new IllegalArgumentException("There is a problem with files in given path!");
        }
    }
}
