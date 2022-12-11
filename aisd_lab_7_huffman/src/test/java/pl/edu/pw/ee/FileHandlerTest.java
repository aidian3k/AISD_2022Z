package pl.edu.pw.ee;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileHandlerTest {
    private FileHandler fileHandler;

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_leadingPathIsNotADirectory() throws IOException {
        //when
        boolean isCompressing = true;
        String pathToTheFile = "src/test/resources/FileHandler/leadingPathIsNotADirectoryTest.txt";
        fileHandler = new FileHandler(pathToTheFile, isCompressing);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_pathToRootDirIsNull() throws IOException {
        //when
        boolean isCompressing = true;
        String pathToTheFile = null;
        fileHandler = new FileHandler(pathToTheFile, isCompressing);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_rootDirectoryDoesNotExist() throws IOException {
        //when
        boolean isCompressing = true;
        String pathToTheFile = "";
        fileHandler = new FileHandler(pathToTheFile, isCompressing);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_rootDirectoryDoesNotHaveDecompressedFileWhenCompressing() throws IOException {
        //when
        boolean isCompressing = true;
        String pathToTheFile = "src/test/resources/FileHandler/emptyDirectory";
        fileHandler = new FileHandler(pathToTheFile, isCompressing);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_decompressingWithoutCompressedFile() throws IOException {
        //when
        boolean isCompressing = false;
        String pathToTheFile = "src/test/resources/FileHandler/decompressingWithoutCompressedFile";
        fileHandler = new FileHandler(pathToTheFile, isCompressing);

        //then
        assert false;
    }


    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_keyFileAndCompressedFileDoesNotExistInDirectory() throws IOException {
        //when
        boolean isCompressing = false;
        String pathToTheFile = "src/test/resources/FileHandler/emptyDirectory";
        fileHandler = new FileHandler(pathToTheFile, isCompressing);

        //then
        assert false;
    }

    @Test
    public void should_correctlyReadFrequencyOfCharacters_when_decompressedFileIsEmpty() throws IOException {
        //when
        boolean isCompressing = true;
        String pathToTheFile = "src/test/resources/FileHandler/decompressingWithoutCompressedFile";
        fileHandler = new FileHandler(pathToTheFile, isCompressing);
        List<Node> nodeList = fileHandler.readFrequencyOfSingleCharacters();

        //then
        int expectedListSize = 0;
        assertEquals(expectedListSize, nodeList.size());
    }

    @Test
    public void should_correctlyReadFrequencyOfCharacters_when_compressingFileWithOneCharacter() throws IOException {
        //when
        boolean isCompressing = true;
        String pathToTheFile = "src/test/resources/FileHandler/decompressingOneCharacter";
        fileHandler = new FileHandler(pathToTheFile, isCompressing);
        List<Node> nodeList = fileHandler.readFrequencyOfSingleCharacters();

        //then
        int expectedListSize = 1;
        int expectedFrequency = 56;

        assertEquals(expectedListSize, nodeList.size());
        assertEquals(expectedFrequency, nodeList.get(0).getFrequency());
    }

    @Test
    public void should_correctlyReadFrequencyOfCharacters_when_compressingFileWithTwoCharacters() throws IOException {
        //when
        boolean isCompressing = true;
        String pathToTheFile = "src/test/resources/FileHandler/decompressingWithTwoCharacters";
        fileHandler = new FileHandler(pathToTheFile, isCompressing);
        List<Node> nodeList = fileHandler.readFrequencyOfSingleCharacters();

        //then
        int expectedListSize = 3;
        int expectedFrequencyOfA = 20;
        int expectedFrequencyOfS = 19;
        int expectedFrequencyOfD = 1;

        assertEquals(expectedListSize, nodeList.size());
        assertEquals(expectedFrequencyOfA, nodeList.get(0).getFrequency());
        assertEquals(expectedFrequencyOfS, nodeList.get(1).getFrequency());
        assertEquals(expectedFrequencyOfD, nodeList.get(2).getFrequency());
    }

    @Test
    public void should_correctly_readCodes_when_keysFileIsEmpty() throws IOException {
        //when
        boolean isCompressing = false;
        String pathToTheFile = "src/test/resources/FileHandler/decompressingWithEmptyKeys";
        fileHandler = new FileHandler(pathToTheFile, isCompressing);

        HashMap<Character, String> mapOfCodes = fileHandler.readCharacterCodesFromFile(new ArrayList<>());

        //then
        int expectedListSize = 0;
        assertEquals(expectedListSize, mapOfCodes.size());
    }

    @Test
    public void should_correctly_readCodes_when_keysFileHasOnlyOneCode() throws IOException {
        //when
        boolean isCompressing = false;
        String pathToTheFile = "src/test/resources/FileHandler/decompressingWithOnlyOneCode";
        fileHandler = new FileHandler(pathToTheFile, isCompressing);

        HashMap<Character, String> mapOfCodes = fileHandler.readCharacterCodesFromFile(new ArrayList<>());

        //then
        int expectedListSize = 1;
        Character expectedCharacter = '_';
        String expectedCode = "101";

        assertEquals(expectedListSize, mapOfCodes.size());
        assertTrue(mapOfCodes.containsKey(expectedCharacter));
        assertEquals(expectedCode, mapOfCodes.get(expectedCharacter));
    }

    @Test
    public void should_correctly_readCodes_when_keysFileHasTwoCodes() throws IOException {
        //when
        boolean isCompressing = false;
        String pathToTheFile = "src/test/resources/FileHandler/decompressingWithTwoCodes";
        fileHandler = new FileHandler(pathToTheFile, isCompressing);

        HashMap<Character, String> mapOfCodes = fileHandler.readCharacterCodesFromFile(new ArrayList<>());

        //then
        int expectedListSize = 2;

        assertEquals(expectedListSize, mapOfCodes.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwThrowAnException_when_tryingToUseMethodDestinedForCompressingWhenDecompressing()
            throws IOException {
        //when
        boolean isCompressing = false;
        String pathToTheFile = "src/test/resources/FileHandler/decompressingWithOnlyOneCode";
        fileHandler = new FileHandler(pathToTheFile, isCompressing);

        fileHandler.readFrequencyOfSingleCharacters();

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwThrowAnException_when_tryingToUseMethodDestinedForDeCompressingWhenCompressing()
            throws IOException {
        //when
        boolean isCompressing = true;
        String pathToTheFile = "src/test/resources/FileHandler/decompressingWithTwoCharacters";
        fileHandler = new FileHandler(pathToTheFile, isCompressing);

        fileHandler.readCharacterCodesFromFile(new ArrayList<>());

        //then
        assert false;
    }

}