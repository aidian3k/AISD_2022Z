package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.services.Sorting;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class SelectionSortTest {

    private Sorting selectionSort;

    @Before
    public void setUp() {
        selectionSort = new SelectionSort();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_givenArrayIsNull() {
        //given
        double[] testArray = null;

        //when
        selectionSort.sort(testArray);

        //then
        assert false;
    }

    @Test
    public void should_returnSortedArray_when_givenArrayHasOneElement() {
        //given
        double[] testArray = {1};

        
        //when
        double[] expectedArray = {1};
        int expectedArrayLength = 1;

        
        //then
        selectionSort.sort(testArray);
        assertArrayEquals(testArray, expectedArray, 0);
        assertEquals(expectedArrayLength, testArray.length);
    }

    @Test
    public void should_returnSortedArray_when_givenArrayIsSortedAscending() {
        //given
        double[] testArray = {1, 2, 3, 4, 5};

        
        //when
        double[] expectedArray = {1, 2, 3, 4, 5};
        int expectedArrayLength = 5;

        
        //then
        selectionSort.sort(testArray);
        assertArrayEquals(testArray, expectedArray, 0);
        assertEquals(expectedArrayLength, testArray.length);
    }

    @Test
    public void should_returnSortedArray_when_givenArrayIsSortedDescending() {
        //given
        double[] testArray = {5, 4, 3, 2, 1};

        
        //when
        double[] expectedArray = {1, 2, 3, 4, 5};
        int expectedArrayLength = 5;

        
        //then
        selectionSort.sort(testArray);
        assertArrayEquals(testArray, expectedArray, 0);
        assertEquals(expectedArrayLength, testArray.length);
    }
}
