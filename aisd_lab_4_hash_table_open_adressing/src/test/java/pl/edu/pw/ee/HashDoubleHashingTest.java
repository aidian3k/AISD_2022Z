package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HashDoubleHashingTest {

    private HashOpenAddressing<Integer> integerHashOpenAddressing;
    private HashOpenAddressing<String> stringHashOpenAddressing;
    private HashOpenAddressing<Elem> elemHashOpenAddressing;

    @Before
    public void setUp() {
        integerHashOpenAddressing = new HashDoubleHashing<>(1);
        stringHashOpenAddressing = new HashDoubleHashing<>(1);
        elemHashOpenAddressing = new HashDoubleHashing<>(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_initialSizeIsThree() {
        //given
        int initialSize = 3;

        //when
        integerHashOpenAddressing = new HashDoubleHashing<>(initialSize);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_initialSizeIsNegative() {
        //given
        int initialSize = -1;

        //when
        integerHashOpenAddressing = new HashDoubleHashing<>(initialSize);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_initialSizeIsZero() {
        //given
        int initialSize = 0;

        //when
        integerHashOpenAddressing = new HashDoubleHashing<>(initialSize);

        //then
        assert false;
    }

    @Test
    public void should_createHashCorrectly_when_initialSizeIsPositive() {
        //given
        int initialSize = 5;

        //when
        integerHashOpenAddressing = new HashDoubleHashing<>(initialSize);

        //then
        assert true;
    }

    @Test
    public void should_createHashCorrectly_when_baseConstructorIsCalled() {
        //when
        integerHashOpenAddressing = new HashDoubleHashing<>();

        //then
        int expectedSize = 2039;
        assertEquals(expectedSize, integerHashOpenAddressing.getSize());
    }

    @Test
    public void should_correctlyCalculateHashIndex_when_tryingToCalculateHashIndex() {
        //given
        int elem = 2;
        int key = Integer.hashCode(elem);
        int initialSize = 100;

        //when
        integerHashOpenAddressing = new HashDoubleHashing<>(initialSize);
        int hashIndex = integerHashOpenAddressing.hashFunc(key, 0);

        //then
        int expectedHashIndex = 2;
        assertEquals(expectedHashIndex, hashIndex);
    }

    @Test
    public void should_correctlyCalculateHashIndex_when_hashCodeEqualsInitialSizeOfHash() {
        //given
        int elem = 100;
        int key = Integer.hashCode(elem);
        int initialSize = 100;

        //when
        integerHashOpenAddressing = new HashDoubleHashing<>(initialSize);
        int hashIndex = integerHashOpenAddressing.hashFunc(key, 0);

        //then
        int expectedHashIndex = 0;
        assertEquals(expectedHashIndex, hashIndex);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_tryingToPutNullValue() {
        //given
        Integer newElement = null;

        //when
        integerHashOpenAddressing.put(newElement);

        //then
        assert false;
    }

    @Test
    public void should_putElementCorrectly_when_tryingToPutOneElement() {
        //given
        Integer newElement = 1;

        //when
        integerHashOpenAddressing.put(newElement);

        //then
        int expectedSize = 1;
        assertEquals(integerHashOpenAddressing.getSize(), expectedSize);
    }

    @Test
    public void should_correctlyAddTwoElements_when_elementsNotExistsInHash() {
        //given
        Integer[] newElements = {1, 2};

        //when
        for (Integer element : newElements) {
            integerHashOpenAddressing.put(element);
        }

        //then
        int expectedNumberOfElems = 2;
        assertEquals(expectedNumberOfElems, integerHashOpenAddressing.getNumberOfElems());
    }

    @Test
    public void should_putElementsCorrectly_when_tryingToAddManyDifferentElements() {
        //when
        for (int i = 0; i < 100; ++i) {
            integerHashOpenAddressing.put(i);
        }

        //then
        int expectedNumberOfElems = 100;
        assertEquals(expectedNumberOfElems, integerHashOpenAddressing.getNumberOfElems());
    }


    @Test
    public void should_putOneElement_when_tryingToPutManyEqualElements() {
        //given
        Integer newElement = 2;

        //when
        for (int i = 0; i < 100; ++i) {
            integerHashOpenAddressing.put(newElement);
        }

        //then
        int expectedNumberOfElems = 1;
        assertEquals(expectedNumberOfElems, integerHashOpenAddressing.getNumberOfElems());
    }

    @Test
    public void should_putOneElementToHash_when_elementIsString() {
        //given
        String newElement = "hashTesting";

        //when
        stringHashOpenAddressing.put(newElement);

        //then
        int expectedNumberOfElems = 1;
        assertEquals(expectedNumberOfElems, stringHashOpenAddressing.getNumberOfElems());
    }

    @Test
    public void should_putOneElementToHash_when_tryingToAddManyEqualStrings() {
        //given
        String newElement = "equalString";

        //when
        for (int i = 0; i < 100; ++i) {
            stringHashOpenAddressing.put(newElement);
        }

        //then
        int expectedNumberOfElems = 1;
        assertEquals(expectedNumberOfElems, stringHashOpenAddressing.getNumberOfElems());
    }

    @Test
    public void should_correctlyPutElements_when_tryingToPutManyDifferentStrings() {
        //given
        String currentString = "";

        //when
        for (int i = 0; i < 10_000; ++i) {
            stringHashOpenAddressing.put(currentString);
            currentString += "aba";
        }

        //then
        int expectedNumberOfElems = 10_000;
        assertEquals(expectedNumberOfElems, stringHashOpenAddressing.getNumberOfElems());
    }

    @Test
    public void should_correctlyAddElements_when_tryingToAddDifferentElementsWithTheSameHashCode() {
        //when
        for (int i = 0; i < 10_000; ++i) {
            Elem newElem = new Elem(i);
            elemHashOpenAddressing.put(newElem);
        }

        //then
        int expectedNumberOfElements = 10_000;
        assertEquals(expectedNumberOfElements, elemHashOpenAddressing.getNumberOfElems());
    }

    @Test
    public void should_correctlyDoubleSize_when_itIsNeededToResizeTheHashSize() {
        //given
        int testSize = 100;

        //when
        for (int i = 0; i < testSize; ++i) {
            integerHashOpenAddressing.put(i);
        }

        //then
        int expectedHashSize = 256;
        assertEquals(expectedHashSize, integerHashOpenAddressing.getSize());
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_tryingToGetNullElem() {
        //given
        Integer element = null;

        //when
        integerHashOpenAddressing.put(element);

        //then
        assert false;
    }

    @Test(expected = IllegalStateException.class)
    public void should_throwAnException_when_tryingToFindElementWhichIsNotInHash() {
        //given
        Integer element = 2115;

        //when
        integerHashOpenAddressing.get(element);

        //then
        assert false;
    }

    @Test
    public void should_correctlyGetElement_when_tryingToFindElementWhichIsInHash() {
        //given
        Integer element = 1;

        //when
        integerHashOpenAddressing.put(element);
        Integer searchedElement = integerHashOpenAddressing.get(element);

        //then
        Integer expectedSearchedElement = 1;
        assertEquals(expectedSearchedElement, searchedElement);
    }

    @Test
    public void should_correctlyGetElements_when_tryingToFindManyDifferentElements() {
        //when
        for (int i = 0; i < 100; ++i) {
            integerHashOpenAddressing.put(i);
        }

        List<Integer> elements = new ArrayList<>();

        for (int i = 0; i < 100; ++i) {
            elements.add(integerHashOpenAddressing.get(i));
        }

        //then
        int expectedNumberOfElements = 100;
        assertEquals(expectedNumberOfElements, elements.size());
    }

    @Test
    public void should_correctlyGetElements_when_tryingToFindManyEqualElements() {
        //given
        Integer element = 1;

        //when
        for (int i = 0; i < 100; ++i) {
            integerHashOpenAddressing.put(element);
        }

        Set<Integer> elements = new HashSet<>();

        for (int i = 0; i < 100; ++i) {
            elements.add(integerHashOpenAddressing.get(element));
        }

        //then
        int expectedNumberOfElements = 1;
        assertEquals(expectedNumberOfElements, elements.size());
    }

    @Test
    public void should_correctlyGetStringElement_when_tryingToGetExistingStringFromHash() {
        //given
        String elem = "string";

        //when
        stringHashOpenAddressing.put(elem);
        String actualElem = stringHashOpenAddressing.get(elem);

        //then
        String expectedString = "string";
        assertEquals(expectedString, actualElem);
    }

    @Test
    public void should_correctlyGetStringElements_when_tryingToGetManyDifferentStrings() {
        //given
        String currentString = "";

        //when
        for (int i = 0; i < 10_000; ++i) {
            stringHashOpenAddressing.put(currentString);
            currentString += "aba";
        }

        List<String> strings = new ArrayList<>();
        currentString = "";

        for (int i = 0; i < 10_000; ++i) {
            strings.add(stringHashOpenAddressing.get(currentString));
            currentString += "aba";
        }

        //then
        int expectedNumberOfElems = 10_000;
        assertEquals(expectedNumberOfElems, strings.size());
    }

    @Test
    public void should_correctlyGetStringElements_when_tryingToFindManyEqualStrings() {
        //given
        String element = "1";

        //when
        for (int i = 0; i < 100; ++i) {
            stringHashOpenAddressing.put(element);
        }

        Set<String> elements = new HashSet<>();

        for (int i = 0; i < 100; ++i) {
            elements.add(stringHashOpenAddressing.get(element));
        }

        //then
        int expectedNumberOfElements = 1;
        assertEquals(expectedNumberOfElements, elements.size());
    }

    @Test
    public void should_correctlyGetAllElements_when_tryingToGetDifferentElementsWithTheSameHashCode() {
        //when
        List<Elem> elements = new ArrayList<>();

        for (int i = 0; i < 100; ++i) {
            Elem newElem = new Elem(i);
            elements.add(newElem);
            elemHashOpenAddressing.put(newElem);
        }

        List<Elem> getElements = new ArrayList<>();

        for (Elem elem : elements) {
            getElements.add(elemHashOpenAddressing.get(elem));
        }

        //then
        int expectedNumberOfElements = 100;
        assertEquals(expectedNumberOfElements, getElements.size());
    }

    @Test
    public void should_correctlyReact_when_tryingToDeleteElementWhichIsNotInHash() {
        //given
        Integer elementToDelete = 2137;

        //when
        integerHashOpenAddressing.delete(elementToDelete);

        //then
        int expectedNumberOfElements = 0;
        assertEquals(expectedNumberOfElements, integerHashOpenAddressing.getNumberOfElems());
    }

    @Test
    public void should_correctlyDeleteElement_when_tryingToDeleteElementWhichIsInHash() {
        //given
        Integer firstElement = 1;
        Integer secondElement = 2;
        Integer thirdElement = 3;

        //when
        integerHashOpenAddressing.put(firstElement);
        integerHashOpenAddressing.put(secondElement);
        integerHashOpenAddressing.put(thirdElement);
        integerHashOpenAddressing.delete(firstElement);

        //then
        int expectedNumberOfElements = 2;
        assertEquals(expectedNumberOfElements, integerHashOpenAddressing.getNumberOfElems());

    }

    @Test
    public void should_correctlyDeleteElements_when_tryingToDeleteManyEqualElements() {
        //given
        Integer element = 1;

        //then
        for (int i = 0; i < 100; ++i) {
            integerHashOpenAddressing.put(element);
        }

        for (int i = 0; i < 100; ++i) {
            integerHashOpenAddressing.delete(element);
        }

        //then
        int expectedNumberOfElements = 0;
        assertEquals(expectedNumberOfElements, integerHashOpenAddressing.getNumberOfElems());
    }

    @Test
    public void should_correctlyDeleteElements_when_tryingToDeleteDifferentElements() {
        //then
        for (int i = 0; i < 100; ++i) {
            integerHashOpenAddressing.put(i);
        }

        for (int i = 0; i < 100; ++i) {
            integerHashOpenAddressing.delete(i);
        }

        //then
        int expectedNumberOfElements = 0;
        assertEquals(expectedNumberOfElements, integerHashOpenAddressing.getNumberOfElems());
    }

    @Test
    public void should_correctlyDeleteStringElement_when_tryingToDeleteExistingStringFromHash() {
        //given
        String elem = "string";

        //when
        stringHashOpenAddressing.put(elem);
        stringHashOpenAddressing.delete(elem);

        //then
        int expectedNumberOfElements = 0;
        assertEquals(expectedNumberOfElements, stringHashOpenAddressing.getNumberOfElems());
    }

    @Test
    public void should_correctlyDeleteStringElements_when_tryingToDeleteManyDifferentStrings() {
        //given
        String currentString = "";

        //when
        for (int i = 0; i < 10_000; ++i) {
            stringHashOpenAddressing.put(currentString);
            currentString += "aba";
        }

        currentString = "";

        for (int i = 0; i < 10_000; ++i) {
            stringHashOpenAddressing.delete(currentString);
            currentString += "aba";
        }

        //then
        int expectedNumberOfElems = 0;
        assertEquals(expectedNumberOfElems, stringHashOpenAddressing.getNumberOfElems());
    }

    @Test
    public void should_correctlyDeleteAllElements_when_tryingToDeleteDifferentElementsWithTheSameHashCode() {
        //when
        List<Elem> elements = new ArrayList<>();

        for (int i = 0; i < 10_000; ++i) {
            Elem newElem = new Elem(i);
            elements.add(newElem);
            elemHashOpenAddressing.put(newElem);
        }


        for (Elem elem : elements) {
            elemHashOpenAddressing.delete(elem);
        }

        //then
        int expectedNumberOfElements = 0;
        assertEquals(expectedNumberOfElements, elemHashOpenAddressing.getNumberOfElems());
    }

    @Test
    public void should_correctlyPutElements_when_allElementsInTheHashWereDeleted() {
        //given
        int testSize = 10_000;

        //when
        for (int i = 0; i < testSize; ++i) {
            integerHashOpenAddressing.put(i);
        }

        for (int i = 0; i < testSize; ++i) {
            integerHashOpenAddressing.delete(i);
        }

        for (int i = 0; i < testSize; ++i) {
            integerHashOpenAddressing.put(i);
        }

        //then
        int expectedNumberOfElements = 10_000;
        assertEquals(expectedNumberOfElements, integerHashOpenAddressing.getNumberOfElems());
    }

    @Test
    public void should_correctlyPutDifferentStringElements_when_allElementsInTheHashWereDeleted() {
        //given
        String currentString = "";
        int testSize = 10_000;

        //when
        for (int i = 0; i < testSize; ++i) {
            stringHashOpenAddressing.put(currentString);
            currentString += "aba";
        }

        currentString = "";

        for (int i = 0; i < testSize; ++i) {
            stringHashOpenAddressing.delete(currentString);
            currentString += "aba";
        }

        currentString = "";

        for (int i = 0; i < testSize; ++i) {
            stringHashOpenAddressing.put(currentString);
            currentString += "aba";
        }

        //then
        int expectedNumberOfElements = 10_000;
        assertEquals(expectedNumberOfElements, stringHashOpenAddressing.getNumberOfElems());
    }

    private static class Elem implements Comparable<Elem> {
        Integer key;

        Elem(Integer key) {
            this.key = key;
        }

        @Override
        public int hashCode() {
            return 1;
        }

        @Override
        public int compareTo(Elem o) {
            return this.key - o.key;
        }
    }
}