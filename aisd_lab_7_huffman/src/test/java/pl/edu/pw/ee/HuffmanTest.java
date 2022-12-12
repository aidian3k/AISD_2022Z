package pl.edu.pw.ee;

import org.junit.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HuffmanTest {
    private Huffman huffmanCoding;

    @Test
    public void should_correctlyCompressEmptyFile_when_fileIsCompletelyCorrect() {
        //given
        String pathRootToDir = "src/test/resources/FileHandler/decompressingWithEmptyFile";
        boolean isCompressing = true;

        //when
        huffmanCoding = new Huffman();
        int codedCharacters = huffmanCoding.huffman(pathRootToDir, isCompressing);
        File keysFile = new File(pathRootToDir + "/keys.txt");
        File compressedFile = new File(pathRootToDir + "/compressedFile.txt");

        //then
        int expectedCodedCharacters = 0;

        assertTrue(keysFile.exists());
        assertTrue(compressedFile.exists());
        assertEquals(expectedCodedCharacters, codedCharacters);
    }

    @Test
    public void should_correctlyDecompressEmptyFile_when_fileIsCompletelyCorrect() {
        //given
        String pathRootToDir = "src/test/resources/FileHandler/decompressingWithEmptyFile";
        boolean isCompressing = false;

        //when
        huffmanCoding = new Huffman();
        int codedCharacters = huffmanCoding.huffman(pathRootToDir, isCompressing);
        File keysFile = new File(pathRootToDir + "/keys.txt");
        File compressedFile = new File(pathRootToDir + "/compressedFile.txt");

        //then
        int expectedCodedCharacters = 0;

        assertTrue(keysFile.exists());
        assertTrue(compressedFile.exists());
        assertEquals(expectedCodedCharacters, codedCharacters);
    }

    @Test
    public void should_correctlyCompressFileWithOneCharacter_when_fileIsCompletelyCorrect() {
        //given
        String pathRootToDir = "src/test/resources/FileHandler/compressingOneCharacter";
        boolean isCompressing = true;

        //when
        huffmanCoding = new Huffman();
        int codedCharacters = huffmanCoding.huffman(pathRootToDir, isCompressing);
        File keysFile = new File(pathRootToDir + "/keys.txt");
        File compressedFile = new File(pathRootToDir + "/compressedFile.txt");

        //then
        int expectedCodedCharacters = 56;

        assertTrue(keysFile.exists());
        assertTrue(compressedFile.exists());
        assertEquals(expectedCodedCharacters, codedCharacters);
    }

    @Test
    public void should_correctlyDecompressFileWithOneCharacter_when_fileIsCompletelyCorrect() {
        //given
        String pathRootToDir = "src/test/resources/FileHandler/decompressingWithOneCharacter";
        boolean isCompressing = false;

        //when
        huffmanCoding = new Huffman();
        int codedCharacters = huffmanCoding.huffman(pathRootToDir, isCompressing);
        File keysFile = new File(pathRootToDir + "/keys.txt");
        File compressedFile = new File(pathRootToDir + "/compressedFile.txt");

        //then
        int expectedCodedCharacters = 0;

        assertTrue(keysFile.exists());
        assertTrue(compressedFile.exists());
        assertEquals(expectedCodedCharacters, codedCharacters);
    }

    @Test
    public void should_correctlyCompressSampleFile_when_fileIsCompletelyCorrect() {
        //given
        String pathRootToDir = "src/test/resources/Huffman/sample";
        boolean isCompressing = true;

        //when
        huffmanCoding = new Huffman();
        int codedCharacters = huffmanCoding.huffman(pathRootToDir, isCompressing);
        File keysFile = new File(pathRootToDir + "/keys.txt");
        File compressedFile = new File(pathRootToDir + "/compressedFile.txt");

        //then
        int expectedCodedCharacters = 28;

        assertTrue(keysFile.exists());
        assertTrue(compressedFile.exists());
        assertEquals(expectedCodedCharacters, codedCharacters);
    }

    @Test
    public void should_correctlyDecompressSampleFile_when_fileIsCompletelyCorrect() {
        //given
        String pathRootToDir = "src/test/resources/Huffman/sample";
        boolean isCompressing = false;

        //when
        huffmanCoding = new Huffman();
        int codedCharacters = huffmanCoding.huffman(pathRootToDir, isCompressing);
        File decompressedFile = new File(pathRootToDir + "/decompressedFile.txt");

        //then
        int expectedCodedCharacters = 15;

        assertTrue(decompressedFile.exists());
        assertEquals(expectedCodedCharacters, codedCharacters);
    }

    @Test
    public void should_correctlyCompressNiemanieFile_when_fileIsCompletelyCorrect() {
        //given
        String pathRootToDir = "src/test/resources/Huffman/niemanie";
        boolean isCompressing = true;

        //when
        huffmanCoding = new Huffman();
        int codedCharacters = huffmanCoding.huffman(pathRootToDir, isCompressing);
        File keysFile = new File(pathRootToDir + "/keys.txt");
        File compressedFile = new File(pathRootToDir + "/compressedFile.txt");

        //then
        int expectedCodedCharacters = 8900;

        assertTrue(keysFile.exists());
        assertTrue(compressedFile.exists());
        assertEquals(expectedCodedCharacters, codedCharacters);
    }

    @Test
    public void should_correctlyDecompressNiemanieFile_when_fileIsCompletelyCorrect() {
        //given
        String pathRootToDir = "src/test/resources/Huffman/niemanie";
        boolean isCompressing = false;

        //when
        huffmanCoding = new Huffman();
        int codedCharacters = huffmanCoding.huffman(pathRootToDir, isCompressing);
        File decompressedFile = new File(pathRootToDir + "/decompressedFile.txt");

        //then
        int expectedCodedCharacters = 1908;

        assertTrue(decompressedFile.exists());
        assertEquals(expectedCodedCharacters, codedCharacters);
    }

    @Test
    public void should_correctlyCompressPanTadeuszFile_when_fileIsCompletelyCorrect() {
        //given
        String pathRootToDir = "src/test/resources/Huffman/panTadeusz";
        boolean isCompressing = true;

        //when
        huffmanCoding = new Huffman();
        int codedCharacters = huffmanCoding.huffman(pathRootToDir, isCompressing);
        File keysFile = new File(pathRootToDir + "/keys.txt");
        File compressedFile = new File(pathRootToDir + "/compressedFile.txt");

        //then
        int expectedCodedCharacters = 2196449;

        assertTrue(keysFile.exists());
        assertTrue(compressedFile.exists());
        assertEquals(expectedCodedCharacters, codedCharacters);
    }

    @Test
    public void should_correctlyDecompressPanTadeuszFile_when_fileIsCompletelyCorrect() {
        //given
        String pathRootToDir = "src/test/resources/Huffman/panTadeusz";
        boolean isCompressing = false;

        //when
        huffmanCoding = new Huffman();
        int codedCharacters = huffmanCoding.huffman(pathRootToDir, isCompressing);
        File decompressedFile = new File(pathRootToDir + "/decompressedFile.txt");

        //then
        int expectedCodedCharacters = 447032;

        assertTrue(decompressedFile.exists());
        assertEquals(expectedCodedCharacters, codedCharacters);
    }
}
