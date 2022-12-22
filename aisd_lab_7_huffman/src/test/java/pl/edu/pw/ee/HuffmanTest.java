package pl.edu.pw.ee;

import org.junit.Test;
import pl.edu.pw.ee.fileHandling.BitInputStream;
import pl.edu.pw.ee.fileHandling.BitOutputStream;
import pl.edu.pw.ee.huffmanCoding.HuffmanDecoder;
import pl.edu.pw.ee.huffmanCoding.HuffmanEncoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

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
        int expectedCodedCharacters = 8;

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
        int expectedCodedCharacters = 16;

        assertTrue(keysFile.exists());
        assertTrue(compressedFile.exists());
        assertEquals(expectedCodedCharacters, codedCharacters);
    }

    @Test
    public void should_correctlyDecompressFileWithOneCharacter_when_fileIsCompletelyCorrect() {
        //given
        String pathRootToDir = "src/test/resources/FileHandler/compressingOneCharacter";
        boolean isCompressing = false;

        //when
        huffmanCoding = new Huffman();
        int codedCharacters = huffmanCoding.huffman(pathRootToDir, isCompressing);
        File keysFile = new File(pathRootToDir + "/keys.txt");
        File compressedFile = new File(pathRootToDir + "/compressedFile.txt");

        //then
        int expectedCodedCharacters = 7;

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
        int expectedCodedCharacters = 32;

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
        int expectedCodedCharacters = 8432;

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
        int expectedCodedCharacters = 1901;

        assertTrue(decompressedFile.exists());
        assertEquals(expectedCodedCharacters, codedCharacters);
    }

    @Test
    public void should_correctlyCompressLoremIpsumFile_when_fileIsCompletelyCorrect() {
        //given
        String pathRootToDir = "src/test/resources/Huffman/loremIpsum";
        boolean isCompressing = true;

        //when
        huffmanCoding = new Huffman();
        int codedCharacters = huffmanCoding.huffman(pathRootToDir, isCompressing);
        File keysFile = new File(pathRootToDir + "/keys.txt");
        File compressedFile = new File(pathRootToDir + "/compressedFile.txt");

        //then
        int expectedCodedCharacters = 13656;

        assertTrue(keysFile.exists());
        assertTrue(compressedFile.exists());
        assertEquals(expectedCodedCharacters, codedCharacters);
    }

    @Test
    public void should_correctlyDecompressLoremIpsumFile_when_fileIsCompletelyCorrect() {
        //given
        String pathRootToDir = "src/test/resources/Huffman/loremIpsum";
        boolean isCompressing = false;

        //when
        huffmanCoding = new Huffman();
        int codedCharacters = huffmanCoding.huffman(pathRootToDir, isCompressing);
        File decompressedFile = new File(pathRootToDir + "/decompressedFile.txt");

        //then
        int expectedCodedCharacters = 3192;

        assertTrue(decompressedFile.exists());
        assertEquals(expectedCodedCharacters, codedCharacters);
    }

    @Test
    public void should_correctlyCompressFile_when_thereAreOnlyNewLinesInFile() throws IOException {
        //given
        String pathRootToDir = "src/test/resources/HuffmanTree/newLinesCompression";
        boolean isCompressing = true;
        PrintWriter writer = new PrintWriter(pathRootToDir + "/decompressedFile.txt");
        writer.print("\n");
        writer.close();

        //when
        huffmanCoding = new Huffman();
        int codedCharacters = huffmanCoding.huffman(pathRootToDir, isCompressing);
        File keysFile = new File(pathRootToDir + "/keys.txt");
        File compressedFile = new File(pathRootToDir + "/compressedFile.txt");

        //then
        int expectedCodedCharacters = 8;

        assertTrue(keysFile.exists());
        assertTrue(compressedFile.exists());
        assertEquals(expectedCodedCharacters, codedCharacters);
    }

    @Test
    public void should_correctlyDecompressFile_when_thereAreOnlyNewLinesInFile() throws IOException {
        //given
        String pathRootToDir = "src/test/resources/HuffmanTree/newLinesCompression";
        boolean isCompressing = false;
        PrintWriter writer = new PrintWriter(pathRootToDir + "/decompressedFile.txt");
        writer.print("\n");
        writer.close();

        //when
        huffmanCoding = new Huffman();
        int codedCharacters = huffmanCoding.huffman(pathRootToDir, isCompressing);
        File keysFile = new File(pathRootToDir + "/keys.txt");
        File compressedFile = new File(pathRootToDir + "/compressedFile.txt");

        //then
        int expectedCodedCharacters = 1;

        assertTrue(keysFile.exists());
        assertTrue(compressedFile.exists());
        assertEquals(expectedCodedCharacters, codedCharacters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_tryingToCreateHuffmanEncoderWithNull() {
        //when
        HuffmanEncoder huffmanEncoder = new HuffmanEncoder(null);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_tryingToCreateHuffmanDecoderWithNull() {
        //when
        HuffmanDecoder huffmanEncoder = new HuffmanDecoder(null);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_tryingToCreateBitInputStreamWithNull() {
        //when
        BitInputStream bitInputStream = new BitInputStream(null);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_tryingToCreateBitOutputStreamWithNull() {
        //when
        BitOutputStream bitOutputStream = new BitOutputStream(null);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_tryingToWriteBitNotAsOneOrZero() throws IOException {
        //when
        String pathToTheFile = "src/test/resources/Huffman/sample/compressedFile.txt";
        BitOutputStream bitOutputStream = new BitOutputStream(new FileOutputStream(pathToTheFile));
        bitOutputStream.write(3);

        //then
        assert false;
    }

}
