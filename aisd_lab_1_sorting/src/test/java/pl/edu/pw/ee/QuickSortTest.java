package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.services.Sorting;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;

public class QuickSortTest {

    private Sorting quickSort;

    @Before
    public void setUp() {
        quickSort = new QuickSort();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_ArrayIsNull() {
        //given
        double[] nums = null;

        //when
        quickSort.sort(nums);

        //then
        assert false;
    }

    @Test
    public void should_sortArrayCorrectly_when_ArrayHasNoElements() {
        //given
        double[] nums = {};

        //when
        quickSort.sort(nums);

        //then
        double[] expected = {};
        assertArrayEquals(expected, nums, 0);
    }

    @Test
    public void should_sortArrayCorrectly_when_ArrayHasOneElement() {
        //given
        double[] nums = {1};

        //when
        quickSort.sort(nums);

        //then
        double[] expected = {1};
        assertArrayEquals(expected, nums, 0);
    }

    @Test
    public void should_sortArrayCorrectly_when_ArrayIsSortedAscending() {
        //given
        double[] nums = {1, 2, 3, 4, 5};

        //when
        quickSort.sort(nums);

        //then
        double[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, nums, 0);
    }

    @Test
    public void should_sortArrayCorrectly_when_ArrayIsSortedDescending() {
        //given
        double[] nums = {5, 4, 3, 2, 1};

        //when
        quickSort.sort(nums);

        //then
        double[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, nums, 0);
    }

    @Test
    public void should_sortArrayCorrectly_when_ArrayHasManyEqualElements() {
        //given
        double[] nums = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};

        //when
        quickSort.sort(nums);

        //then
        double[] expected = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        assertArrayEquals(expected, nums, 0);
    }

    @Test
    public void should_sortArrayCorrectly_when_ArrayHasNegativeElements() {
        //given
        double[] nums = {-5, -6, -8, -10, -12, -13, -10};

        //when
        quickSort.sort(nums);

        //then
        double[] expected = {-13, -12, -10, -10, -8, -6, -5};
        assertArrayEquals(expected, nums, 0);
    }

    @Test
    public void should_sortArrayCorrectly_when_ArrayHasRandomElements() {
        //given
        double[] nums = {10, -5, -2, -3, 3, 9, 15, -13};

        //when
        quickSort.sort(nums);

        //then
        double[] expected = {-13, -5, -3, -2, 3, 9, 10, 15};
        assertArrayEquals(expected, nums, 0);
    }

    @Test
    public void should_sortArrayCorrectly_when_ArrayHasManyPositiveElements() {
        //given
        double[] nums = {10, -5, -2, -3, 3, 9, 15, -13};

        //when
        quickSort.sort(nums);

        //then
        double[] expected = {-13, -5, -3, -2, 3, 9, 10, 15};
        assertArrayEquals(expected, nums, 0);
    }

    @Test
    public void should_sortArrayCorrectly_when_ArrayIsOptimistic() {
        //given
        double[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        //when
        quickSort.sort(nums);

        //then
        double[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertArrayEquals(expected, nums, 0);
    }

    @Test
    public void should_sortArrayCorrectly_when_ArrayIsPesimistic() {
        //given
        double[] nums = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

        //when
        quickSort.sort(nums);

        //then
        double[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertArrayEquals(expected, nums, 0);
    }

    @Test
    public void should_sortArrayCorrectly_when_ArrayHasManyRandomElements() {
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

        quickSort.sort(nums);
        Arrays.sort(expected);

        //then
        assertArrayEquals(expected, nums, 0);
    }
}
