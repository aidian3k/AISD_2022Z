package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HashListChainingTest {
    private HashListChaining<Integer> hashListChaining;

    @Before
    public void setUp() {
        hashListChaining = new HashListChaining<>(100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_tryingToCreateHashListWithNegativeSize() {
        //when
        hashListChaining = new HashListChaining<>(-5);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_initialSizeIsZero() {
        //when
        hashListChaining = new HashListChaining<>(0);

        //then
        int expectedNumberOfElems = 0;
        assertEquals(expectedNumberOfElems, hashListChaining.getNumberOfElements());
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_tryingToAddNullValue() {
        //when
        hashListChaining.add(null);

        //then
        assert false;
    }

    @Test
    public void should_correctlyAddElementToHash_when_tryingToAddOneElement() {
        //when
        hashListChaining = new HashListChaining<>(1);
        hashListChaining.add(1);

        //then
        int expectedSize = 1;
        assertEquals(expectedSize, hashListChaining.getNumberOfElements());
    }

    @Test
    public void should_correctlyAddElementsToList_when_tryingToAddTwoElements() {
        //given
        Integer[] nums = {1, 2};

        //when
        hashListChaining = new HashListChaining<>(1);
        addElementsToHash(nums);

        List<Integer> numsList = hashListChaining.getListElementsAtIndex(0);

        //then
        int expectedSize = 2;
        List<Integer> expectedList = Arrays.asList(1, 2);

        assertEquals(expectedSize, hashListChaining.getNumberOfElements());
        assertEquals(expectedList, numsList);
    }

    @Test
    public void should_correctlyReact_when_tryingToAddTwoEqualElements() {
        //given
        Integer[] nums = {1, 1};

        //when
        hashListChaining = new HashListChaining<>(1);
        addElementsToHash(nums);

        List<Integer> numsList = hashListChaining.getListElementsAtIndex(0);

        //then
        int expectedSize = 1;
        List<Integer> expectedList = Collections.singletonList(1);

        assertEquals(expectedSize, hashListChaining.getNumberOfElements());
        assertEquals(expectedList, numsList);
    }

    @Test
    public void should_correctlyReact_when_tryingToAddManyEqualElements() {
        //given
        Integer[] nums = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};

        //when
        hashListChaining = new HashListChaining<>(1);
        addElementsToHash(nums);

        List<Integer> numsList = hashListChaining.getListElementsAtIndex(0);

        //then
        int expectedSize = 1;
        List<Integer> expectedList = Collections.singletonList(1);

        assertEquals(expectedSize, hashListChaining.getNumberOfElements());
        assertEquals(expectedList, numsList);
    }

    @Test
    public void should_correctlyAddElements_when_tryingToAddManyElements() {
        //given
        Integer[] nums = {1, 2, 3, 4, 5, 6, 6, 7, 8, 9, 10};

        //when
        hashListChaining = new HashListChaining<>(1);
        addElementsToHash(nums);

        List<Integer> numsList = hashListChaining.getListElementsAtIndex(0);

        //then
        int expectedSize = 10;
        List<Integer> expectedList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        assertEquals(expectedSize, hashListChaining.getNumberOfElements());
        assertEquals(expectedList, numsList);
    }

    @Test(expected = IllegalStateException.class)
    public void should_throwAnException_when_tryingToGetNullValue() {
        //when
        hashListChaining.get(null);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_tryingToGetValueFromEmptyHash() {
        //when
        hashListChaining.get(2);

        //then
        assert false;
    }

    @Test
    public void should_returnCorrectValue_when_tryingToGetExistingValueInHash() {
        //given
        Integer[] nums = {1, 2, 3, 4, 5, 6};

        //when
        hashListChaining = new HashListChaining<>(1);
        addElementsToHash(nums);

        //then
        Integer expectedValue = 3;
        assertEquals(expectedValue, hashListChaining.get(3));
    }

    @Test
    public void should_returnNullValue_when_elementDoesNotExistInHash() {
        //given
        Integer[] nums = {1, 3, 2};

        //when
        hashListChaining = new HashListChaining<>(1);
        addElementsToHash(nums);

        //then
        Integer expectedValue = null;
        assertEquals(expectedValue, hashListChaining.get(5));

    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_tryingToDeleteElementFromEmptyHash() {
        //when
        hashListChaining.delete(1);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_tryingToDeleteNullValue() {
        //when
        hashListChaining.add(1);
        hashListChaining.delete(null);

        //then
        assert false;
    }

    @Test
    public void should_deleteElementCorrectly_when_tryingToDeleteFirstElementInTheList() {
        //given
        Integer[] nums = {1, 2, 3, 4, 5};

        //when
        hashListChaining = new HashListChaining<>(1);
        addElementsToHash(nums);
        hashListChaining.delete(1);

        List<Integer> numsList = hashListChaining.getListElementsAtIndex(0);

        //then
        int expectedSize = 4;
        List<Integer> expectedList = Arrays.asList(2, 3, 4, 5);

        assertEquals(expectedSize, hashListChaining.getNumberOfElements());
        assertEquals(expectedList, numsList);
    }

    @Test
    public void should_deleteElementCorrectly_when_tryingToDeleteLastElementInTheList() {
        //given
        Integer[] nums = {5, 4, 3, 2, 1};

        //when
        hashListChaining = new HashListChaining<>(1);
        addElementsToHash(nums);
        hashListChaining.delete(1);

        List<Integer> numsList = hashListChaining.getListElementsAtIndex(0);

        //then
        int expectedSize = 4;
        List<Integer> expectedList = Arrays.asList(5, 4, 3, 2);

        assertEquals(expectedSize, hashListChaining.getNumberOfElements());
        assertEquals(expectedList, numsList);
    }

    @Test
    public void should_deleteElementCorrectly_when_tryingToDeleteMiddleElementInTheList() {
        //given
        Integer[] nums = {5, 4, 3, 2, 1};

        //when
        hashListChaining = new HashListChaining<>(1);
        addElementsToHash(nums);
        hashListChaining.delete(3);

        List<Integer> numsList = hashListChaining.getListElementsAtIndex(0);

        //then
        int expectedSize = 4;
        List<Integer> expectedList = Arrays.asList(5, 4, 2, 1);

        assertEquals(expectedSize, hashListChaining.getNumberOfElements());
        assertEquals(expectedList, numsList);
    }

    @Test
    public void should_reactCorrectly_when_tryingToDeleteElementWhichIsNotInHashList() {
        //given
        Integer[] nums = {5, 4, 3, 2, 1};

        //when
        hashListChaining = new HashListChaining<>(1);
        addElementsToHash(nums);
        hashListChaining.delete(2137);

        List<Integer> numsList = hashListChaining.getListElementsAtIndex(0);

        //then
        int expectedSize = 5;
        List<Integer> expectedList = Arrays.asList(5, 4, 3, 2, 1);

        assertEquals(expectedSize, hashListChaining.getNumberOfElements());
        assertEquals(expectedList, numsList);
    }

    @Test
    public void should_reactCorrectly_when_tryingToDeleteValueTwoTimes() {
        //given
        Integer[] nums = {2, 1, 3, 7};

        //when
        hashListChaining = new HashListChaining<>(1);
        addElementsToHash(nums);
        hashListChaining.delete(3);
        hashListChaining.delete(3);

        List<Integer> numsList = hashListChaining.getListElementsAtIndex(0);

        //then
        int expectedSize = 3;
        List<Integer> expectedList = Arrays.asList(2, 1, 7);

        assertEquals(expectedSize, hashListChaining.getNumberOfElements());
        assertEquals(expectedList, numsList);
    }

    @Test
    public void should_reactCorrectly_when_tryingToAddAndThenDeleteElement() {
        //when
        hashListChaining = new HashListChaining<>(1);
        hashListChaining.add(1);
        hashListChaining.delete(1);
        hashListChaining.add(1);

        List<Integer> numsList = hashListChaining.getListElementsAtIndex(0);

        //then
        int expectedSize = 1;
        List<Integer> expectedList = Collections.singletonList(1);

        assertEquals(expectedSize, hashListChaining.getNumberOfElements());
        assertEquals(expectedList, numsList);
    }

    @Test
    public void should_reactCorrectly_when_tryingToHaveStringHashList() {
        //given
        String[] strings = {"Adrian", "Jakub", "Pawel"};

        //when
        HashListChaining<String> stringHashListChaining = new HashListChaining<>(1);
        stringHashListChaining.add(strings[0]);
        stringHashListChaining.add(strings[1]);
        stringHashListChaining.add(strings[2]);

        List<String> stringList = stringHashListChaining.getListElementsAtIndex(0);

        //then
        int expectedSize = 3;
        List<String> expectedList = Arrays.asList("Adrian", "Jakub", "Pawel");

        assertEquals(expectedSize, stringHashListChaining.getNumberOfElements());
        assertEquals(expectedList, stringList);
    }

    @Test
    public void should_returnCorrectLoadFactor_when_fourElementsAreInTheHash() {
        //given
        Integer[] nums = {1, 2, 3, 4};

        //when
        hashListChaining = new HashListChaining<>(16);
        addElementsToHash(nums);

        //then
        double expected = 0.25;
        assertEquals(expected, hashListChaining.countLoadFactor(), 0);
    }

    private void addElementsToHash(Integer[] nums) {
        for (Integer item : nums) {
            hashListChaining.add(item);
        }
    }

}
