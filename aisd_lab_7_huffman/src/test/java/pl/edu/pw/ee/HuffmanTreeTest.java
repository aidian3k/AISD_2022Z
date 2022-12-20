package pl.edu.pw.ee;

import org.junit.Test;
import pl.edu.pw.ee.huffmanCoding.HuffmanTree;

import static org.junit.Assert.assertEquals;

public class HuffmanTreeTest {
    private HuffmanTree huffmanTree;

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_pathToRootDirIsNull() {
        //when;
        String pathToRootDir = null;

        huffmanTree = new HuffmanTree(pathToRootDir, true);

        //then
        assert false;
    }

    @Test
    public void should_correctlyCreateHuffmanTree_when_thereIsOnlyOneCharToCompress() {
        //given
        String pathToRootDir = "src/test/resources/HuffmanTree/oneCharacterCompression";
        boolean isCompressing = true;

        //when
        huffmanTree = new HuffmanTree(pathToRootDir, isCompressing);
        String preOrderResult = huffmanTree.traverseHuffmanTreePreOrder();

        //then
        String expectedPreOrderResult = "1a";
        String expectedCodeForA = "0";

        assertEquals(expectedPreOrderResult, preOrderResult);
        assertEquals(expectedCodeForA, huffmanTree.getCodes().get('a'));
    }

    @Test
    public void should_correctlyCreateHuffmanTree_when_thereIsEmptyFileToCompress() {
        //given
        String pathToRootDir = "src/test/resources/HuffmanTree/emptyFileCompression";
        boolean isCompressing = true;

        //when
        huffmanTree = new HuffmanTree(pathToRootDir, isCompressing);
        String preOrderResult = huffmanTree.traverseHuffmanTreePreOrder();

        //then
        String expectedPreOrderResult = "";
        int expectedCodeSize = 0;

        assertEquals(expectedPreOrderResult, preOrderResult);
        assertEquals(expectedCodeSize, huffmanTree.getCodes().size());
    }

    @Test
    public void should_correctlyCreateHuffmanTree_when_thereIsOnlyTwoCharToCompress() {
        //given
        boolean isCompressing = true;
        String pathToRootDir = "src/test/resources/HuffmanTree/twoCharacterCompression";

        //when
        huffmanTree = new HuffmanTree(pathToRootDir, isCompressing);
        String preOrderResult = huffmanTree.traverseHuffmanTreePreOrder();

        //then
        String expectedPreOrderResult = "01a1b";
        String expectedCodeForA = "0";
        String expectedCodeForB = "1";

        assertEquals(expectedPreOrderResult, preOrderResult);
        assertEquals(expectedCodeForA, huffmanTree.getCodes().get('a'));
        assertEquals(expectedCodeForB, huffmanTree.getCodes().get('b'));
    }

    @Test
    public void should_correctlyCreateHuffmanTree_when_thereAreManyCharacters() {
        //given
        boolean isCompressing = true;
        String pathToRootDir = "src/test/resources/HuffmanTree/manyCharacterCompression";

        //when
        huffmanTree = new HuffmanTree(pathToRootDir, isCompressing);
        String preOrderResult = huffmanTree.traverseHuffmanTreePreOrder();

        //then
        String expectedPreOrderResult = "01C001B1D1A";
        String expectedCodeForD = "101";
        String expectedCodeForA = "11";

        assertEquals(expectedPreOrderResult, preOrderResult);
        assertEquals(expectedCodeForA, huffmanTree.getCodes().get('A'));
        assertEquals(expectedCodeForD, huffmanTree.getCodes().get('D'));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_correctlyCreateHuffmanTree_when_thereAreOnlyDiacriticalCharacters() {
        //given
        boolean isCompressing = true;
        String pathToRootDir = "src/test/resources/HuffmanTree/diacriticalCharacterCompression";

        //when
        huffmanTree = new HuffmanTree(pathToRootDir, isCompressing);

        //then
        assert false;
    }

    @Test
    public void should_correctlyRebuildHuffmanTree_when_thereIsZeroCharactersToDecompress() {
        //given
        boolean isCompressing = false;
        String pathToRootDir = "src/test/resources/HuffmanTree/emptyFileCompression";

        //when
        huffmanTree = new HuffmanTree(pathToRootDir, isCompressing);
        String preOrderResult = huffmanTree.traverseHuffmanTreePreOrder();

        //then
        String expectedPreOrderResult = "";
        int expectedCodeSize = 0;

        assertEquals(expectedPreOrderResult, preOrderResult);
        assertEquals(expectedCodeSize, huffmanTree.getCodes().size());
    }

    @Test
    public void should_correctlyRebuildHuffmanTree_when_thereIsOnlyOneCharacterToDecompress() {
        //given
        boolean isCompressing = false;
        String pathToRootDir = "src/test/resources/HuffmanTree/oneCharacterCompression";

        //when
        huffmanTree = new HuffmanTree(pathToRootDir, isCompressing);
        String preOrderResult = huffmanTree.traverseHuffmanTreePreOrder();

        //then
        String expectedPreOrderResult = "1a";
        int expectedCodeSize = 1;
        String expectedCodeForA = "0";

        assertEquals(expectedPreOrderResult, preOrderResult);
        assertEquals(expectedCodeSize, huffmanTree.getCodes().size());
        assertEquals(expectedCodeForA, huffmanTree.getCodes().get('a'));
    }
<<<<<<< HEAD
    
=======

>>>>>>> 77466e65f972e1238b5d71e10f883f3f94861098
    @Test
    public void should_correctlyRebuildHuffmanTree_when_thereAreOnlyTwoCharToDecompress() {
        //given
        boolean isCompressing = false;
        String pathToRootDir = "src/test/resources/HuffmanTree/twoCharacterCompression";

        //when
        huffmanTree = new HuffmanTree(pathToRootDir, isCompressing);
        String preOrderResult = huffmanTree.traverseHuffmanTreePreOrder();

        //then
        String expectedPreOrderResult = huffmanTree.traverseHuffmanTreePreOrder();
        String expectedCodeForA = "0";
        String expectedCodeForB = "1";

        assertEquals(expectedPreOrderResult, preOrderResult);
        assertEquals(expectedCodeForA, huffmanTree.getCodes().get('a'));
        assertEquals(expectedCodeForB, huffmanTree.getCodes().get('b'));
    }

    @Test
    public void should_correctlyRebuildHuffmanTree_when_thereAreManyCharactersToDecompress() {
        //given
        boolean isCompressing = false;
        String pathToRootDir = "src/test/resources/HuffmanTree/manyCharacterCompression";

        //when
        huffmanTree = new HuffmanTree(pathToRootDir, isCompressing);
        String preOrderResult = huffmanTree.traverseHuffmanTreePreOrder();

        //then
        String expectedPreOrderResult = "01C001B1D1A";
        String expectedCodeForD = "101";
        String expectedCodeForA = "11";

        assertEquals(expectedPreOrderResult, preOrderResult);
        assertEquals(expectedCodeForA, huffmanTree.getCodes().get('A'));
        assertEquals(expectedCodeForD, huffmanTree.getCodes().get('D'));
    }

}