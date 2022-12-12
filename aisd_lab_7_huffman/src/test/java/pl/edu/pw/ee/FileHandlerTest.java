package pl.edu.pw.ee;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


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
        String pathToTheFile = "src/test/resources/FileHandler/compressingWithoutCompressedFile";
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
        String pathToTheFile = "src/test/resources/FileHandler/compressingWithoutCompressedFile";
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
        String pathToTheFile = "src/test/resources/FileHandler/compressingOneCharacter";
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
        String pathToTheFile = "src/test/resources/FileHandler/compressingWithTwoCharacters";
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
    public void should_correctlyReadFrequencyOfCharacters_when_compressingFileWithManyCharacters() throws IOException {
        //when
        boolean isCompressing = true;
        String pathToTheFile = "src/test/resources/FileHandler/compressingWithManyCharacters";
        fileHandler = new FileHandler(pathToTheFile, isCompressing);
        List<Node> nodeList = fileHandler.readFrequencyOfSingleCharacters();

        //then
        int expectedListSize = 4;
        int expectedFrequencyOfB = 1;
        int expectedFrequencyOfC = 6;
        int expectedFrequencyOfA = 5;
        int expectedFrequencyOfD = 3;

        assertEquals(expectedListSize, nodeList.size());
        assertEquals(expectedFrequencyOfB, nodeList.get(0).getFrequency());
        assertEquals(expectedFrequencyOfC, nodeList.get(1).getFrequency());
        assertEquals(expectedFrequencyOfA, nodeList.get(2).getFrequency());
        assertEquals(expectedFrequencyOfD, nodeList.get(3).getFrequency());
    }

    @Test
    public void should_correctlyReadCharactersKeysFromFile_when_fileIsEmpty() throws IOException {
        //when
        boolean isCompressing = false;
        String pathToTheFile = "src/test/resources/FileHandler/decompressingWithOneCharacter";
        fileHandler = new FileHandler(pathToTheFile, isCompressing);
        List<Character> characters = fileHandler.readCharactersFromFile();

        //then
        int expectedSizeOfList = 1;
        assertEquals(expectedSizeOfList, characters.size());
    }

    @Test
    public void should_correctlyReadKeyCharactersFromFile_when_fileHasOneCharacter() throws IOException {
        //when
        boolean isCompressing = false;
        String pathToTheFile = "src/test/resources/FileHandler/decompressingWithManyCharacters";
        fileHandler = new FileHandler(pathToTheFile, isCompressing);
        List<Character> characters = fileHandler.readCharactersFromFile();

        //then
        int expectedSizeOfList = 11;
        assertEquals(expectedSizeOfList, characters.size());
    }

    @Test
    public void should_correctlyReadCharactersFromKeysFile_when_fileHasManyCharacters() throws IOException {
        //when
        boolean isCompressing = false;
        String pathToTheFile = "src/test/resources/FileHandler/decompressingWithManyCharacters";
        fileHandler = new FileHandler(pathToTheFile, isCompressing);
        List<Character> characters = fileHandler.readCharactersFromFile();

        //then
        int expectedSizeOfList = 11;
        assertEquals(expectedSizeOfList, characters.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwThrowAnException_when_tryingToUseMethodDestinedForCompressingWhenDecompressing()
            throws IOException {
        //when
        boolean isCompressing = false;
        String pathToTheFile = "src/test/resources/FileHandler/compressingOneCharacter";
        fileHandler = new FileHandler(pathToTheFile, isCompressing);

        fileHandler.readFrequencyOfSingleCharacters();

        //then
        assert false;
    }

}