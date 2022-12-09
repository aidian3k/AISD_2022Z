package pl.edu.pw.ee;

import java.io.IOException;
import java.util.List;

public class Huffman {
    public static void main(String... args) throws IOException {
        Huffman huffman = new Huffman();
        huffman.huffman("/Users/aidian3k/2022Z_AISD_git_lab_GR4_gr13/aisd_lab_7_huffman/src/main/java/pl/edu/pw/ee/results/compressedFile.txt", false);
    }

    public int huffman(String pathToRootDir, boolean compress) throws IOException {
        FileHandler reader = new FileHandler(pathToRootDir);
        HuffmanTree huffmanTree;

        if (compress) {
            List<Node> nodeList = reader.readFrequencyOfSingleCharacters();
            huffmanTree = new HuffmanTree(nodeList);
            huffmanTree.encodeFile(pathToRootDir);
        } else {
            huffmanTree = new HuffmanTree();
            huffmanTree.decodeFile(pathToRootDir);
        }

        return 0;
    }
}
