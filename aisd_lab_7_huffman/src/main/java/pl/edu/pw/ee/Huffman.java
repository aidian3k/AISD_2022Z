package pl.edu.pw.ee;

import pl.edu.pw.ee.huffmanCoding.HuffmanDecoder;
import pl.edu.pw.ee.huffmanCoding.HuffmanEncoder;
import pl.edu.pw.ee.huffmanCoding.HuffmanTree;

public class Huffman {
    public int huffman(String pathToRootDir, boolean compress) {
        HuffmanTree huffmanTree;
        int counter;

        if (compress) {
            huffmanTree = new HuffmanTree(pathToRootDir, compress);
            HuffmanEncoder huffmanEncoder = new HuffmanEncoder(huffmanTree);
            counter = huffmanEncoder.encodeFile();
        } else {
            huffmanTree = new HuffmanTree(pathToRootDir, compress);
            HuffmanDecoder huffmanDecoder = new HuffmanDecoder(huffmanTree);
            counter = huffmanDecoder.decodeFile();
        }

        return counter;
    }
}
