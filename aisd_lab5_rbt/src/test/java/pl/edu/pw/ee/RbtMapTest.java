package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class RbtMapTest {

    private RbtMap<String, String> stringRbtMap;
    private RbtMap<Integer, Integer> integerRbtMap;

    @Before
    public void setUp() {
        this.stringRbtMap = new RbtMap<>();
        this.integerRbtMap = new RbtMap<>();
    }

    @Test
    public void should_correctlyInitializeRbtMap_when_wantingToCallConstructor() {
        //when
        integerRbtMap = new RbtMap<>();
        stringRbtMap = new RbtMap<>();

        //then
        assert true;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_tryingToPutNullKey() {
        //given
        Integer newKey = null;
        Integer newValue = 5;

        //when
        integerRbtMap.setValue(newKey, newValue);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_tryingToPutNullValue() {
        //given
        Integer newKey = 15;
        Integer newValue = null;

        //when
        integerRbtMap.setValue(newKey, newValue);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_tryingToPutNullKeyAndValue() {
        //given
        Integer newKey = null;
        Integer newValue = null;

        //when
        integerRbtMap.setValue(newKey, newValue);

        //then
        assert false;
    }

    @Test
    public void should_putElementCorrectly_when_tryingToPutOneElement() {
        //given
        Integer newKey = 1;
        Integer newElement = 5;

        //when
        integerRbtMap.setValue(newKey, newElement);

        //then
        String expectedInOrderString = "1:5";
        assertEquals(expectedInOrderString, integerRbtMap.getInOrder());
    }

    @Test
    public void should_correctlyAddTwoElements_when_elementsDoesNotExistInMap() {
        //given
        Integer firstKey = 1;
        Integer firstValue = 5;
        Integer secondKey = 3;
        Integer secondValue = 12;

        //when
        integerRbtMap.setValue(firstKey, firstValue);
        integerRbtMap.setValue(secondKey, secondValue);

        //then
        String expectedInOrderString = "1:5 3:12";
        assertEquals(expectedInOrderString, integerRbtMap.getInOrder());
    }

    @Test
    public void should_correctlyOverwriteValue_when_tryingToAddTwoValuesWithTheSameKey() {
        //given
        Integer key = 1;
        Integer firstValue = 21;
        Integer secondValue = 15;

        //when
        integerRbtMap.setValue(key, firstValue);
        integerRbtMap.setValue(key, secondValue);

        //then
        String expectedInOrderString = "1:15";
        assertEquals(expectedInOrderString, integerRbtMap.getInOrder());
    }

    @Test
    public void should_correctlyPutOneElement_when_tryingToAddManyEqualElements() {
        //given
        Integer key = 1;
        Integer value = 1;
        int numberOfTests = 100;

        //when
        for (int i = 0; i < numberOfTests; ++i) {
            integerRbtMap.setValue(key, value);
        }

        //then
        String expectedInOrderString = "1:1";
        assertEquals(expectedInOrderString, integerRbtMap.getInOrder());
    }

    @Test
    public void should_correctlyPutManyElements_when_tryingToAddManyDifferentElements() {
        //given
        int numberOfElements = 10_000;

        //when
        List<Node<Integer, Integer>> elements = new ArrayList<>();

        for (int i = 0; i < numberOfElements; ++i) {
            elements.add(new Node<>(i, i));
            integerRbtMap.setValue(i, i);
        }

        String expectedInOrderString = getElementsInOrder(elements);
        assertEquals(expectedInOrderString, integerRbtMap.getInOrder());
    }

    @Test
    public void should_correctlyPutOneElement_when_tryingToAddStringKeyElement() {
        //given
        String newKey = "1";
        String newValue = "10";

        //when
        stringRbtMap.setValue(newKey, newValue);

        //then
        String expectedInOrderString = "1:10";
        assertEquals(expectedInOrderString, stringRbtMap.getInOrder());
    }

    @Test
    public void should_correctlyPutManyElements_when_tryingToAddManyKeyStringsElements() {
        //given
        int numberOfElements = 1_000;

        //when
        List<Node<String, String>> elements = new ArrayList<>();
        String currentString = "";

        for (int i = 0; i < numberOfElements; ++i) {
            currentString += "a";
            elements.add(new Node<>(currentString, currentString));
            stringRbtMap.setValue(currentString, currentString);
        }

        String expectedInOrderString = getElementsInOrder(elements);
        assertEquals(expectedInOrderString, stringRbtMap.getInOrder());
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_tryingToGetNullKey() {
        //given
        Integer key = null;

        //when
        integerRbtMap.getValue(key);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_tryingToGetKeyWhichDoesNotExistInMap() {
        //given
        Integer key = 100;

        //when
        integerRbtMap.getValue(key);

        //then
        assert false;
    }

    @Test
    public void should_correctlyGetValue_when_tryingToFindExistingKeyInMap() {
        //given
        Integer newKey = 1;
        Integer newValue = 10;

        //when
        integerRbtMap.setValue(newKey, newValue);

        //then
        assertEquals(newValue, integerRbtMap.getValue(newKey));
    }

    @Test
    public void should_correctlyGetElements_when_tryingToFindManyDifferentElements() {
        //when
        for (int i = 100; i > 0; --i) {
            integerRbtMap.setValue(i, i);
        }

        List<Integer> elements = new ArrayList<>();

        for (int i = 100; i > 0; --i) {
            elements.add(integerRbtMap.getValue(i));
        }

        //then
        int expectedNumberOfElements = 100;
        assertEquals(expectedNumberOfElements, elements.size());
    }

    @Test
    public void should_correctlyGetStringElements_when_tryingToGetManyDifferentStrings() {
        //given
        String currentString = "";

        //when
        for (int i = 0; i < 10_000; ++i) {
            stringRbtMap.setValue(currentString, currentString);
            currentString += "aba";
        }

        List<String> strings = new ArrayList<>();
        currentString = "";

        for (int i = 0; i < 10_000; ++i) {
            strings.add(stringRbtMap.getValue(currentString));
            currentString += "aba";
        }

        //then
        int expectedNumberOfElems = 10_000;
        assertEquals(expectedNumberOfElems, strings.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_tryingToDeleteFromEmptyMap() {
        //when
        integerRbtMap.deleteMax();

        //then
        assert false;
    }

    @Test
    public void should_correctlyDeleteMaxElement_whenTryingToDeleteOneElement() {
        //given
        Integer newKey = 12;
        Integer newValue = 5;

        //when
        integerRbtMap.setValue(newKey, newValue);
        integerRbtMap.deleteMax();

        //then
        String expectedInOrderString = "";
        assertEquals(expectedInOrderString, stringRbtMap.getInOrder());
    }

    @Test
    public void should_correctlyDeleteMaxElement_when_twoKeysAreInHash() {
        //given
        Integer[] elements = {1, 2};

        //when
        for (Integer element : elements) {
            integerRbtMap.setValue(element, element);
        }

        integerRbtMap.deleteMax();

        //then
        String expectedInOrderString = "1:1";
        assertEquals(expectedInOrderString, integerRbtMap.getInOrder());
    }

    @Test
    public void should_correctlyDeleteMaxElements_when_tryingToDeleteAllThreeKeys() {
        //given
        Integer[] elements = {3, 2, 1};

        //when
        for (Integer element : elements) {
            integerRbtMap.setValue(element, element);
        }

        integerRbtMap.deleteMax();
        integerRbtMap.deleteMax();
        integerRbtMap.deleteMax();

        //then
        String expectedInOrderString = "";
        assertEquals(expectedInOrderString, integerRbtMap.getInOrder());
    }

    @Test
    public void should_correctlyDeleteAllElements_when_tryingToDeleteManyElementsInMap() {
        //given
        int numberOfElements = 100_000;
        Random rnd = new Random(1410);

        //when
        for (int i = 0; i < numberOfElements; ++i) {
            integerRbtMap.setValue(rnd.nextInt(), i);
        }

        for (int i = 0; i < numberOfElements; ++i) {
            integerRbtMap.deleteMax();
        }

        //then
        String expectedInOrderString = "";
        assertEquals(expectedInOrderString, integerRbtMap.getInOrder());
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_tryingToDeleteMinFromEmptyMap() {
        //when
        integerRbtMap.deleteMin();

        //then
        assert false;
    }

    @Test
    public void should_correctlyDelteMinElement_when_twoKeysAreInHash() {
        //given
        Integer[] elements = {3, 4};

        //when
        for (Integer element : elements) {
            integerRbtMap.setValue(element, element);
        }

        integerRbtMap.deleteMin();

        //then
        String expectedInOrderString = "4:4";
        assertEquals(expectedInOrderString, integerRbtMap.getInOrder());
    }

    @Test
    public void should_correctlyDeleteMinElements_when_tryingToDeleteTwoKeys() {
        //given
        Integer[] elements = {3, 2, 1};

        //when
        for (Integer element : elements) {
            integerRbtMap.setValue(element, element);
        }

        integerRbtMap.deleteMin();
        integerRbtMap.deleteMin();

        //then
        String expectedInOrderString = "3:3";
        assertEquals(expectedInOrderString, integerRbtMap.getInOrder());
    }

    @Test
    public void should_correctlyDeleteAllMinElements_when_tryingToDeleteManyElementsInMap() {
        //given
        int numberOfElements = 100_000;
        Random rnd = new Random(1410);

        //when
        for (int i = 0; i < numberOfElements; ++i) {
            integerRbtMap.setValue(rnd.nextInt(), i);
        }

        for (int i = 0; i < numberOfElements; ++i) {
            integerRbtMap.deleteMin();
        }

        //then
        String expectedInOrderString = "";
        assertEquals(expectedInOrderString, integerRbtMap.getInOrder());
    }

    @Test
    public void should_returnCorrectPostOrderString_when_mapIsEmpty() {
        //when
        String postOrderResult = integerRbtMap.getPostOrder();

        //then
        String expectedPostOrderString = "";
        assertEquals(expectedPostOrderString, postOrderResult);
    }

    @Test
    public void should_returnCorrectPostOrderString_when_mapHasFourElements() {
        //given
        Integer[] elements = {2, 1, 3, 4};

        //when
        for (Integer element : elements) {
            integerRbtMap.setValue(element, element);
        }

        String postOrderResult = integerRbtMap.getPostOrder();

        //then
        String expectedPostOrderString = "1:1 3:3 4:4 2:2";
        assertEquals(expectedPostOrderString, postOrderResult);
    }

    @Test
    public void should_returnCorrectInOrderString_when_mapIsEmpty() {
        //when
        String inOrderResult = integerRbtMap.getInOrder();

        //then
        String expectedInOrderString = "";
        assertEquals(expectedInOrderString, inOrderResult);
    }

    @Test
    public void should_returnCorrectInOrderString_when_mapHasFourElements() {
        //given
        Integer[] elements = {2, 1, 3, 4};

        //when
        for (Integer element : elements) {
            integerRbtMap.setValue(element, element);
        }

        String inOrderResult = integerRbtMap.getInOrder();

        //then
        String expectedInOrderResult = "1:1 2:2 3:3 4:4";
        assertEquals(expectedInOrderResult, inOrderResult);
    }

    @Test
    public void should_returnCorrectInPreOrderString_when_mapIsEmpty() {
        //when
        String inOrderResult = integerRbtMap.getInOrder();

        //then
        String expectedPreOrderString = "";
        assertEquals(expectedPreOrderString, inOrderResult);
    }

    @Test
    public void should_returnCorrectPreOrderString_when_mapHasFourElements() {
        //given
        Integer[] elements = {2, 1, 3, 4};

        //when
        for (Integer element : elements) {
            integerRbtMap.setValue(element, element);
        }

        String preOrderResult = integerRbtMap.getPreOrder();

        //then
        String expectedPreOrderString = "2:2 1:1 4:4 3:3";
        assertEquals(expectedPreOrderString, preOrderResult);
    }

    private <K extends Comparable<K>, V> String getElementsInOrder(List<Node<K, V>> elements) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Node<K, V> node : elements) {
            stringBuilder.append(" ").append(node.getKey()).append(":").append(node.getValue());
        }

        return stringBuilder.toString().trim();
    }
}
