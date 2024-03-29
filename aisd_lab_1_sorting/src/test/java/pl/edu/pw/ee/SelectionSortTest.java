package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.heap.services.Sorting;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;

public class SelectionSortTest {

    private Sorting sortingMethod;

    @Before
    public void setUp() {
        sortingMethod = new SelectionSort();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_arrayIsNull() {
        //given
        double[] nums = null;

        //when
        sortingMethod.sort(nums);

        //then
        assert false;
    }

    @Test
    public void should_sortArrayCorrectly_when_arrayHasNoElements() {
        //given
        double[] nums = {};

        //when
        sortingMethod.sort(nums);

        //then
        double[] expected = {};
        assertArrayEquals(expected, nums, 0);
    }

    @Test
    public void should_sortArrayCorrectly_when_arrayHasOneElement() {
        //given
        double[] nums = {1};

        //when
        sortingMethod.sort(nums);

        //then
        double[] expected = {1};
        assertArrayEquals(expected, nums, 0);
    }

    @Test
    public void should_sortArrayCorrectly_when_arrayHasTwoElements() {
        //given
        double[] nums = {2, 1};

        //when
        sortingMethod.sort(nums);

        //then
        double[] expected = {1, 2};
        assertArrayEquals(expected, nums, 0);
    }

    @Test
    public void should_sortArrayCorrectly_when_arrayHasThreeElements() {
        //given
        double[] nums = {3, 1, 4};

        //when
        sortingMethod.sort(nums);

        //then
        double[] expected = {1, 3, 4};
        assertArrayEquals(expected, nums, 0);
    }

    @Test
    public void should_sortArrayCorrectly_when_arrayIsSortedAscending() {
        //given
        double[] nums = {1, 2, 3, 4, 5};

        //when
        sortingMethod.sort(nums);

        //then
        double[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, nums, 0);
    }

    @Test
    public void should_sortArrayCorrectly_when_arrayIsSortedDescending() {
        //given
        double[] nums = {5, 4, 3, 2, 1};

        //when
        sortingMethod.sort(nums);

        //then
        double[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, nums, 0);
    }

    @Test
    public void should_sortArrayCorrectly_when_arrayHasManyEqualElements() {
        //given
        double[] nums = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};

        //when
        sortingMethod.sort(nums);

        //then
        double[] expected = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        assertArrayEquals(expected, nums, 0);
    }

    @Test
    public void should_sortArrayCorrectly_when_firstElementIsNotSorted() {
        //given
        double[] nums = {35, 21, 21, 21, 21, 21, 21, 21};

        //when
        sortingMethod.sort(nums);

        //then
        double[] expected = {21, 21, 21, 21, 21, 21, 21, 35};
        assertArrayEquals(expected, nums, 0);
    }

    @Test
    public void should_sortArrayCorrectly_when_lastElementIsNotSorted() {
        //given
        double[] nums = {3, 3, 3, 3, 3, 3, 1};

        //when
        sortingMethod.sort(nums);

        //then
        double[] expected = {1, 3, 3, 3, 3, 3, 3};
        assertArrayEquals(expected, nums, 0);
    }

    @Test
    public void should_sortArrayCorrectly_when_firstAndLastElementAreNotSorted() {
        //given
        double[] nums = {4, 1, 2, 3, 0};

        //when
        sortingMethod.sort(nums);

        //then
        double[] expected = {0, 1, 2, 3, 4};
        assertArrayEquals(expected, nums, 0);
    }

    @Test
    public void should_sortArrayCorrectly_when_arrayHasNegativeElements() {
        //given
        double[] nums = {-5, -6, -8, -10, -12, -13, -10};

        //when
        sortingMethod.sort(nums);

        //then
        double[] expected = {-13, -12, -10, -10, -8, -6, -5};
        assertArrayEquals(expected, nums, 0);
    }

    @Test
    public void should_sortArrayCorrectly_when_arrayHasRandomElements() {
        //given
        double[] nums = {10, -5, -2, -3, 3, 9, 15, -13};

        //when
        sortingMethod.sort(nums);

        //then
        double[] expected = {-13, -5, -3, -2, 3, 9, 10, 15};
        assertArrayEquals(expected, nums, 0);
    }

    @Test
    public void should_sortArrayCorrectly_when_arrayIsOptimistic() {
        //given
        double[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        //when
        sortingMethod.sort(nums);

        //then
        double[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertArrayEquals(expected, nums, 0);
    }

    @Test
    public void should_sortArrayCorrectly_when_arrayIsPessimistic() {
        //given
        double[] nums = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

        //when
        sortingMethod.sort(nums);

        //then
        double[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertArrayEquals(expected, nums, 0);
    }

    @Test
    public void should_sortArrayCorrectly_when_arrayHasManyRandomElements() {
        //given
        final int SEED = 1000;
        final int arrayLength = 100_000;

        double[] nums = new double[arrayLength];
        double[] expected = new double[arrayLength];
        final Random rnd = new Random(SEED);

        //when
        for (int i = 0; i < arrayLength; ++i) {
            double generatedValue = rnd.nextDouble();
            nums[i] = generatedValue;
            expected[i] = generatedValue;
        }

        sortingMethod.sort(nums);
        Arrays.sort(expected);

        //then
        assertArrayEquals(expected, nums, 0);
    }

}
