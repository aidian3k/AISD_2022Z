package pl.edu.pw.ee;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LongestCommonSubsequenceTest {
    private static final String PATH_TO_TEST_DIR = "src/test/resources";
    private String expectedMatrixFileName;
    private LongestCommonSubsequence longestCommonSubsequence;

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_topStringInConstructorIsNull() {
        //given
        String topStr = null;
        String leftStr = "a";

        //when
        longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_leftStringInConstructorIsNull() {
        //given
        String topStr = "b";
        String leftStr = null;

        //when
        longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_bothConstructorArgumentsAreNull() {
        //given
        String topStr = null;
        String leftStr = null;

        //when
        longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);

        //then
        assert false;
    }

    @Test
    public void should_correctlyFindLcs_when_bothStringsAreEmpty() {
        //given
        String topStr = "";
        String leftStr = "";

        //when
        longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);
        String lcsString = longestCommonSubsequence.findLCS();

        //then
        int expectedLcsLength = 0;
        String expectedLcsString = "";

        assertEquals(expectedLcsLength, lcsString.length());
        assertEquals(expectedLcsString, lcsString);
    }

    @Test
    public void should_correctlyFindLcs_when_leftStringIsEmpty() {
        //given
        String leftStr = "";
        String topStr = "abc";

        longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);
        String lcsString = longestCommonSubsequence.findLCS();

        //then
        int expectedLcsLength = 0;
        String expectedLcsString = "";

        assertEquals(expectedLcsLength, lcsString.length());
        assertEquals(expectedLcsString, lcsString);
    }

    @Test
    public void should_correctlyFindLcs_when_topStringIsEmpty() {
        //given
        String leftStr = "cbd";
        String topStr = "";

        longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);
        String lcsString = longestCommonSubsequence.findLCS();

        //then
        int expectedLcsLength = 0;
        String expectedLcsString = "";

        assertEquals(expectedLcsLength, lcsString.length());
        assertEquals(expectedLcsString, lcsString);
    }

    @Test
    public void should_correctlyFindLcs_when_leftStringHasOneLetter() {
        //given
        String leftStr = "a";
        String topStr = "abac";

        longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);
        String lcsString = longestCommonSubsequence.findLCS();

        //then
        int expectedLcsLength = 1;
        String expectedLcsString = "a";

        assertEquals(expectedLcsLength, lcsString.length());
        assertEquals(expectedLcsString, lcsString);
    }

    @Test
    public void should_correctlyFindLcs_when_topStringHasOneLetter() {
        //given
        String leftStr = "abac";
        String topStr = "b";

        longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);
        String lcsString = longestCommonSubsequence.findLCS();

        //then
        int expectedLcsLength = 1;
        String expectedLcsString = "b";

        assertEquals(expectedLcsLength, lcsString.length());
        assertEquals(expectedLcsString, lcsString);
    }

    @Test
    public void should_correctlyFindLcs_when_topAndLeftStringAreTheSame() {
        //given
        String leftStr = "aaaa";
        String topStr = "aaaa";

        longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);
        String lcsString = longestCommonSubsequence.findLCS();

        //then
        int expectedLcsLength = 4;
        String expectedLcsString = "aaaa";

        assertEquals(expectedLcsLength, lcsString.length());
        assertEquals(expectedLcsString, lcsString);
    }

    @Test
    public void should_correctlyFindLcs_when_topAndRightStringHasOneLetter() {
        //given
        String leftStr = "c";
        String topStr = "c";

        longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);
        String lcsString = longestCommonSubsequence.findLCS();

        //then
        int expectedLcsLength = 1;
        String expectedLcsString = "c";

        assertEquals(expectedLcsLength, lcsString.length());
        assertEquals(expectedLcsString, lcsString);
    }

    @Test
    public void should_correctlyFindLcs_when_stringHasNewLineCharacters() {
        //given
        String leftStr = "aba\na\n";
        String topStr = "abc\n\n";

        longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);
        String lcsString = longestCommonSubsequence.findLCS();

        //then
        int expectedLcsLength = 4;
        String expectedLcsString = "ab\n\n";

        assertEquals(expectedLcsLength, lcsString.length());
        assertEquals(expectedLcsString, lcsString);
    }

    @Test
    public void should_correctlyFindLcs_when_thereAreManySpecialCharacters() {
        //given
        String leftStr = "\t\t\naba\n\n\t";
        String topStr = "\t\ta\n\t";

        //when
        longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);
        String lcsString = longestCommonSubsequence.findLCS();

        //then
        int expectedLcsLength = 5;
        String expectedLcsString = "\t\ta\n\t";

        assertEquals(expectedLcsLength, lcsString.length());
        assertEquals(expectedLcsString, lcsString);
    }

    @Test
    public void should_correctlyFindLcs_when_oneStringContainsCarriageReturnSign() {
        //given
        String leftStr = "\t\t\r\ra";
        String topStr = "\t\n\r\ra";

        //when
        longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);
        String lcsString = longestCommonSubsequence.findLCS();

        //then
        int expectedLcsLength = 4;
        String expectedLcsString = "\t\r\ra";

        assertEquals(expectedLcsLength, lcsString.length());
        assertEquals(expectedLcsString, lcsString);
    }

    @Test
    public void should_correctlyFindLcs_when_thereIsOnlyOneLcs() {
        //given
        String leftStr = "RESZTA";
        String topStr = "PREZENTY";

        //when
        longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);
        String lcsString = longestCommonSubsequence.findLCS();

        //then
        int expectedLcsLength = 4;
        String expectedLcsString = "REZT";

        assertEquals(expectedLcsLength, lcsString.length());
        assertEquals(expectedLcsString, lcsString);
    }

    @Test
    public void should_correctlyFindLcs_when_thereAreManyLcs() {
        //given
        String leftStr = "POLITECHNIKA";
        String topStr = "TOALETA";
        String secondLeftStr = "TOALETA";
        String secondTopStr = "POLITECHNIKA";

        //when
        longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);
        String firstLcsString = longestCommonSubsequence.findLCS();

        longestCommonSubsequence = new LongestCommonSubsequence(secondTopStr, secondLeftStr);
        String secondLcsString = longestCommonSubsequence.findLCS();

        //then
        int expectedLcsLength = 4;
        String expectedFirstLcsString = "OLTA";
        String expectedSecondLcsString = "OLEA";

        assertEquals(expectedLcsLength, firstLcsString.length());
        assertEquals(expectedFirstLcsString, firstLcsString);
        assertEquals(expectedLcsLength, secondLcsString.length());
        assertEquals(expectedSecondLcsString, secondLcsString);
    }

    @Test
    public void should_correctlyFindLcs_when_leftStringContainsTopString() {
        //given
        String leftStr = "AB  AB AB";
        String topStr = "AB";

        //when
        longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);
        String lcsString = longestCommonSubsequence.findLCS();

        //then
        int expectedLcsLength = 2;
        String expectedLcsString = "AB";

        assertEquals(expectedLcsLength, lcsString.length());
        assertEquals(expectedLcsString, lcsString);
    }

    @Test
    public void should_correctlyDifferBigLetterFromSmallLetter_when_findingLcs() {
        String leftStr = "BbcBCBcC";
        String topStr = "BCcCc";

        //when
        longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);
        String lcsString = longestCommonSubsequence.findLCS();

        //then
        int expectedLcsLength = 4;
        String expectedLcsString = "BcCc";

        assertEquals(expectedLcsLength, lcsString.length());
        assertEquals(expectedLcsString, lcsString);
    }

    @Test
    public void should_correctlyGenerateDisplayMatrix_when_bothStringsConstructorAreEmpty() {
        //given
        expectedMatrixFileName = "bothStringConstructorEmpty.txt";
        String topStr = "";
        String leftStr = "";

        //when
        longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);
        char[][] displayMatrix = longestCommonSubsequence.getDisplayMatrix();

        //then
        assertTrue(isExpectedMatrixEqualsDisplayMatrix(displayMatrix));
    }

    @Test
    public void should_correctlyGenerateDisplayMatrix_when_leftStringIsEmpty() {
        //given
        expectedMatrixFileName = "leftStringIsEmpty.txt";
        String topStr = "abc";
        String leftStr = "";

        //when
        longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);
        char[][] displayMatrix = longestCommonSubsequence.getDisplayMatrix();

        //then
        assertTrue(isExpectedMatrixEqualsDisplayMatrix(displayMatrix));
    }

    @Test
    public void should_correctlyGenerateDisplayMatrix_when_topStringIsEmpty() {
        //given
        expectedMatrixFileName = "topStringIsEmpty.txt";
        String topStr = "";
        String leftStr = "abc";

        //when
        longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);
        char[][] displayMatrix = longestCommonSubsequence.getDisplayMatrix();

        //then
        assertTrue(isExpectedMatrixEqualsDisplayMatrix(displayMatrix));
    }

    @Test
    public void should_correctlyGenerateDisplayMatrix_when_leftStrHasOneLetter() {
        //given
        expectedMatrixFileName = "leftStrHasOneLetter.txt";
        String leftStr = "a";
        String topStr = "abac";

        //when
        longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);
        char[][] displayMatrix = longestCommonSubsequence.getDisplayMatrix();

        //then
        assertTrue(isExpectedMatrixEqualsDisplayMatrix(displayMatrix));
    }

    @Test
    public void should_correctlyGenerateDisplayMatrix_when_topStrHasOneLetter() {
        //given
        expectedMatrixFileName = "topStrHasOneLetter.txt";
        String leftStr = "bacaca";
        String topStr = "b";

        //when
        longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);
        char[][] displayMatrix = longestCommonSubsequence.getDisplayMatrix();

        //then
        assertTrue(isExpectedMatrixEqualsDisplayMatrix(displayMatrix));
    }

    @Test
    public void should_correctlyGenerateDisplayMatrix_when_topAndLeftStringAreTheSame() {
        //given
        expectedMatrixFileName = "topAndLeftStrAreTheSame.txt";
        String leftStr = "bababa";
        String topStr = "bababa";

        //when
        longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);
        char[][] displayMatrix = longestCommonSubsequence.getDisplayMatrix();

        //then
        assertTrue(isExpectedMatrixEqualsDisplayMatrix(displayMatrix));
    }

    @Test
    public void should_correctlyGenerateDisplayMatrix_when_topAndLeftStringHasOneLetter() {
        //given
        expectedMatrixFileName = "topAndLeftStrHasOneLetter.txt";
        String leftStr = "a";
        String topStr = "a";

        //when
        longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);
        char[][] displayMatrix = longestCommonSubsequence.getDisplayMatrix();

        //then
        assertTrue(isExpectedMatrixEqualsDisplayMatrix(displayMatrix));
    }

    @Test
    public void should_correctlyGenerateDisplayMatrix_when_thereAreManySpecialCharacters() {
        //given
        expectedMatrixFileName = "thereAreManySpecialCharacters.txt";
        String leftStr = "\t\t\naba\n\n\t\r ";
        String topStr = "\t\t a\n\t";

        //when
        longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);
        char[][] displayMatrix = longestCommonSubsequence.getDisplayMatrix();

        //then
        assertTrue(isExpectedMatrixEqualsDisplayMatrix(displayMatrix));
    }

    @Test
    public void should_correctlyGenerateMatrix_when_thereIsOnlyOneLcs() {
        //given
        expectedMatrixFileName = "thereIsOnlyOneLcs.txt";
        String leftStr = "RESZTA";
        String topStr = "PREZENTY";

        //when
        longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);
        char[][] displayMatrix = longestCommonSubsequence.getDisplayMatrix();

        //then
        assertTrue(isExpectedMatrixEqualsDisplayMatrix(displayMatrix));
    }

    @Test
    public void should_correctlyGenerateDisplayMatrix_when_leftStringContainsTopString() {
        //given
        expectedMatrixFileName = "leftStrContainsTopStr.txt";
        String leftStr = "AB  AB AB";
        String topStr = "AB";

        //when
        longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);
        char[][] displayMatrix = longestCommonSubsequence.getDisplayMatrix();

        //then
        assertTrue(isExpectedMatrixEqualsDisplayMatrix(displayMatrix));
    }

    @Test
    public void should_correctlyGenerateDisplayMatrix_when_findingLcsFromSample() {
        //given
        expectedMatrixFileName = "sampleFileLcs.txt";
        String leftStr = "rzeczy_nie_trzeba\n_się_spieszyć";
        String topStr = "często_z_odkrywaniem";

        //when
        longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);
        char[][] displayMatrix = longestCommonSubsequence.getDisplayMatrix();

        //then
        assertTrue(isExpectedMatrixEqualsDisplayMatrix(displayMatrix));
    }

    private boolean isExpectedMatrixEqualsDisplayMatrix(char[][] displayMatrix) {
        try {
            String pathToExpectedMatrixFile = PATH_TO_TEST_DIR + "/" + expectedMatrixFileName;
            InputStreamReader reader = new InputStreamReader(new FileInputStream(pathToExpectedMatrixFile));
            int characterReader;

            int currentRow = 0;
            int currentColumn = 0;

            while ((characterReader = reader.read()) != -1) {
                char singleCharacter = (char) characterReader;

                if (singleCharacter == '\n') {
                    currentRow++;
                    currentColumn = 0;

                    continue;
                }

                if (singleCharacter != displayMatrix[currentRow][currentColumn]) {
                    reader.close();
                    return false;
                }

                currentColumn++;
            }

            reader.close();

            return true;
        } catch (IOException | ArrayIndexOutOfBoundsException matrixException) {
            return false;
        }
    }

}
