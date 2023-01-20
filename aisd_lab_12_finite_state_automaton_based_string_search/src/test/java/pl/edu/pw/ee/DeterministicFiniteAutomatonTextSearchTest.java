package pl.edu.pw.ee;

import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DeterministicFiniteAutomatonTextSearchTest {

    @Test(expected = IllegalArgumentException.class)
    public void throwAnExceptionWhenPatternIsNull() {
        //given
        String pattern = null;

        //when
        DeterministicFiniteAutomatonTextSearch finder = new DeterministicFiniteAutomatonTextSearch(pattern);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwAnExceptionWhenTextIsNullFindFirst() {
        //given
        String pattern = "";
        String text = null;

        //when
        DeterministicFiniteAutomatonTextSearch finder = new DeterministicFiniteAutomatonTextSearch(pattern);
        finder.findFirst(text);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwAnExceptionWhenTextIsNullFindAll() {
        //given
        String pattern = "";
        String text = null;

        //when
        DeterministicFiniteAutomatonTextSearch finder = new DeterministicFiniteAutomatonTextSearch(pattern);
        finder.findAll(text);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwAnExceptionWhenPatternIsEmpty() {
        //given
        String pattern = "";
        String text = "abcacaca";

        //when
        DeterministicFiniteAutomatonTextSearch finder = new DeterministicFiniteAutomatonTextSearch(pattern);

        //then
        assert false;
    }

    @Test
    public void correctlyFindPatternWhenPatternHasOneLetter() {
        //given
        String pattern = "a";
        String text = "abababababab";

        //when
        DeterministicFiniteAutomatonTextSearch finder = new DeterministicFiniteAutomatonTextSearch(pattern);
        int foundIndex = finder.findFirst(text);

        //then
        int expectedIndex = 0;
        assertEquals(expectedIndex, foundIndex);
    }

    @Test
    public void correctlyFindPatternWhenPatternHasOneLetterFindAll() {
        //given
        String pattern = "a";
        String text = "ababa";

        //when
        DeterministicFiniteAutomatonTextSearch finder = new DeterministicFiniteAutomatonTextSearch(pattern);
        int[] foundArray = finder.findAll(text);

        //then
        int[] expectedArray = {0, 2, 4};
        Assert.assertArrayEquals(expectedArray, foundArray);
    }

    @Test
    public void correctlyFindPatternLessonExample() {
        //given
        String pattern = "ABACA";
        String text = "CABABACABCAAAC";

        //when
        DeterministicFiniteAutomatonTextSearch finder = new DeterministicFiniteAutomatonTextSearch(pattern);
        int foundIndex = finder.findFirst(text);

        //then
        int expectedIndex = 3;
        assertEquals(expectedIndex, foundIndex);
    }

    @Test
    public void correctlyFindPatternWhenPatternTwoLetterFindFirst() {
        //given
        String pattern = "AB";
        String text = "BBBBABBBBBAB";

        //when
        DeterministicFiniteAutomatonTextSearch finder = new DeterministicFiniteAutomatonTextSearch(pattern);
        int foundIndex = finder.findFirst(text);

        //then
        int expectedIndex = 4;
        assertEquals(expectedIndex, foundIndex);
    }

    @Test
    public void correctlyFindPatternWhenPatternTwoLetterFindAll() {
        //given
        String pattern = "AB";
        String text = "ABBBBBABBBBBAB";

        //when
        DeterministicFiniteAutomatonTextSearch finder = new DeterministicFiniteAutomatonTextSearch(pattern);
        int[] foundArray = finder.findAll(text);

        //then
        int[] expectedArray = {0, 6, 12};
        Assert.assertArrayEquals(expectedArray, foundArray);
    }

    @Test
    public void correctlyFindPatternWhenPatternDoesNotExistInTextFindFirst() {
        //given
        String pattern = "ABCD";
        String text = "AAAABCBABABBABABB";

        //when
        DeterministicFiniteAutomatonTextSearch finder = new DeterministicFiniteAutomatonTextSearch(pattern);
        int foundIndex = finder.findFirst(text);

        //then
        int expectedIndex = -1;
        assertEquals(expectedIndex, foundIndex);
    }

    @Test
    public void correctlyFindPatternWhenPatternDoesNotExistInFindAll() {
        //given
        String pattern = "ABCD";
        String text = "AAAABCBABABBABABB";

        //when
        DeterministicFiniteAutomatonTextSearch finder = new DeterministicFiniteAutomatonTextSearch(pattern);
        int[] foundIndex = finder.findAll(text);

        //then
        int[] expectedIndex = {};
        Assert.assertArrayEquals(foundIndex, expectedIndex);
    }

    @Test
    public void correctlyFindWhenPatternAlmostInStart() {
        //given
        String pattern = "ABCD";
        String text = "ABCAAACCDDABCD";

        //when
        DeterministicFiniteAutomatonTextSearch finder = new DeterministicFiniteAutomatonTextSearch(pattern);
        int[] foundIndex = finder.findAll(text);
        int firstIndex = 10;

        //then
        int[] expectedIndexArray = {10};
        int expectedIndex = 10;

        assertEquals(expectedIndex, firstIndex);
        Assert.assertArrayEquals(foundIndex, expectedIndexArray);
    }

    @Test
    public void correctlyFindWhenAllTextIsPattern() {
        //given
        String pattern = "A";
        String text = "AAAA";

        //when
        DeterministicFiniteAutomatonTextSearch finder = new DeterministicFiniteAutomatonTextSearch(pattern);
        int[] foundIndex = finder.findAll(text);
        int firstIndex = finder.findFirst(text);

        //then
        int[] expectedIndexArray = {0, 1, 2, 3};
        int expectedIndex = 0;

        assertEquals(expectedIndex, firstIndex);
        Assert.assertArrayEquals(foundIndex, expectedIndexArray);
    }
    
    @Test
    public void correctlyFindPatternWhenThereAreTwoA() {
        //given
        String pattern = "AA";
        String text = "AAAA";

        //when
        DeterministicFiniteAutomatonTextSearch finder = new DeterministicFiniteAutomatonTextSearch(pattern);
        int[] foundIndex = finder.findAll(text);
        int firstIndex = finder.findFirst(text);

        //then
        int[] expectedIndexArray = {0, 1, 2};
        int expectedIndex = 0;

        assertEquals(expectedIndex, firstIndex);
        Assert.assertArrayEquals(foundIndex, expectedIndexArray);
    }

    @Test
    public void correctlyFindWhenLongText() {
        //given
        String pattern = "swallowed";
        String text = "Some books are to be tasted, others to be swallowed, and some few to be chewed and digested.";

        //when
        DeterministicFiniteAutomatonTextSearch finder = new DeterministicFiniteAutomatonTextSearch(pattern);
        int[] foundIndex = finder.findAll(text);
        int firstIndex = finder.findFirst(text);

        //then
        int[] expectedIndexArray = {42};
        int expectedIndex = 42;

        assertEquals(expectedIndex, firstIndex);
        Assert.assertArrayEquals(foundIndex, expectedIndexArray);
    }
    
    @Test
    public void correctlyFindWhenLongTextNoFind() {
         //given
        String pattern = "sme";
        String text = "Some books are to be tasted, others to be swallowed, and some few to be chewed and digested.";

        //when
        DeterministicFiniteAutomatonTextSearch finder = new DeterministicFiniteAutomatonTextSearch(pattern);
        int[] foundIndex = finder.findAll(text);
        int firstIndex = finder.findFirst(text);

        //then
        int[] expectedIndexArray = {};
        int expectedIndex = -1;

        assertEquals(expectedIndex, firstIndex);
        Assert.assertArrayEquals(foundIndex, expectedIndexArray);
    }

}
