package pl.edu.pw.ee;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Huffman {

    public int huffman(String pathToRootDir, boolean compress) throws IOException {
        FileHandler reader;
        HuffmanTree huffmanTree;
        int counter;

        if (compress) {
            reader = new FileHandler(pathToRootDir, compress);
            List<Node> nodeList = reader.readFrequencyOfSingleCharacters();

            huffmanTree = new HuffmanTree(nodeList, pathToRootDir);
            counter = huffmanTree.encodeFile();
        } else {
            reader = new FileHandler(pathToRootDir, compress);
            List<Character> listOfChars = new ArrayList<>();

            HashMap<Character, String> codes = reader.readCharacterCodesFromFile(listOfChars);
            huffmanTree = new HuffmanTree(codes, listOfChars, pathToRootDir);
            counter = huffmanTree.decodeFile();
        }

        return counter;
    }

    public static void main(String ... args) throws IOException {
        Huffman huffman = new Huffman();
        System.out.println(huffman.huffman("aisd_lab_7_huffman/src/main/resources/results", true));
    }
}
