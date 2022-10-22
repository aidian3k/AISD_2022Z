package pl.edu.pw.ee.PerformanceTests;

import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.InsertionSort;
import pl.edu.pw.ee.services.Sorting;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;

public class InsertionSortPerformanceTest {

    private final int SEED = 1000;
    private final int MAX_ELEMENTS_TO_SORT = 250_000;
    private final int NUMBER_OF_TIMES_TO_REPEAT = 2;
    private final int STEP = 500;
    private final PerformanceUtils performanceUtils = new PerformanceUtils();
    private int currentLength = 0;
    private Sorting sortingMethod;
    private Random randomNums;
    private Random randomExpected;

    private double[] optimisticArray;
    private double[] expectedOptimisticArray;

    private double[] averageArray;
    private double[] expectedAverageArray;

    private double[] pessimisticArray;
    private double[] expectedPessimisticArray;

    @Before
    public void setUpSortingTests() {
        sortingMethod = new InsertionSort();
    }

    @Before
    public void setUpOptimisticCase() {
        randomNums = new Random(SEED);
        randomExpected = new Random(SEED);

        optimisticArray = performanceUtils.createRandomArray(currentLength, randomNums);
        expectedOptimisticArray = performanceUtils.createRandomArray(currentLength, randomExpected);

        Arrays.sort(optimisticArray);
        Arrays.sort(expectedOptimisticArray);
    }

    @Before
    public void setUpAverageCase() {
        randomNums = new Random(SEED);
        randomExpected = new Random(SEED);

        averageArray = performanceUtils.createRandomArray(currentLength, randomNums);
        expectedAverageArray = performanceUtils.createRandomArray(currentLength, randomExpected);

        Arrays.sort(expectedAverageArray);
    }

    @Before
    public void setUpPessimisticCase() {
        randomNums = new Random(SEED);
        randomExpected = new Random(SEED);

        pessimisticArray = performanceUtils
                .createRandomArray(currentLength, randomNums);
        expectedPessimisticArray = performanceUtils
                .createRandomArray(currentLength, randomExpected);

        Arrays.sort(pessimisticArray);
        performanceUtils.reverseArray(pessimisticArray);
        Arrays.sort(expectedPessimisticArray);
    }

    @Test
    public void should_sortArrayCorrectly_when_optimisticCase() {
        //when
        sortingMethod.sort(optimisticArray);

        //then
        assertArrayEquals(expectedOptimisticArray, optimisticArray, 0);
    }

    @Test
    public void should_sortArrayCorrectly_when_pessimisticCase() {
        //when
        sortingMethod.sort(pessimisticArray);

        //then
        assertArrayEquals(expectedPessimisticArray, pessimisticArray, 0);
    }

    @Test
    public void should_sortArrayCorrectly_when_averageCase() {
        //when
        sortingMethod.sort(averageArray);

        //then
        assertArrayEquals(expectedAverageArray, averageArray, 0);
    }

    @Test
    public void should_saveSortingTimeToTheFile_when_sortingOptimisticCase() throws IOException {
        String filePath
                = "src/test/java/pl/edu/pw/ee/PerformanceTests/TimeData/insertionSort/insertionSortOptimistic.txt";
        PrintWriter pw = new PrintWriter(new FileWriter(filePath));

        for (int i = 0; i <= MAX_ELEMENTS_TO_SORT; i += STEP) {
            currentLength = i;
            long[] repeatTimeSaver = new long[NUMBER_OF_TIMES_TO_REPEAT];

            for(int j = 0 ; j < NUMBER_OF_TIMES_TO_REPEAT; j++) {
                setUpOptimisticCase();
                repeatTimeSaver[j] = performanceUtils.calculateSortingTime(sortingMethod, optimisticArray);
            }

            long meanTime = performanceUtils.calculateMeanTime(repeatTimeSaver);
            pw.println(currentLength + " " + meanTime);
        }

        pw.close();
        assert true;
    }

    @Test
    public void should_saveSortingTimeToTheFile_when_sortingAverageCase() throws IOException {
        String filePath
                = "src/test/java/pl/edu/pw/ee/PerformanceTests/TimeData/insertionSort/insertionSortAverage.txt";
        PrintWriter pw = new PrintWriter(new FileWriter(filePath));

        for (int i = 0; i <= MAX_ELEMENTS_TO_SORT; i += STEP) {
            currentLength = i;
            long[] repeatTimeSaver = new long[NUMBER_OF_TIMES_TO_REPEAT];

            for(int j = 0 ; j < NUMBER_OF_TIMES_TO_REPEAT; j++) {
                setUpAverageCase();
                repeatTimeSaver[j] = performanceUtils.calculateSortingTime(sortingMethod, averageArray);
            }

            long meanTime = performanceUtils.calculateMeanTime(repeatTimeSaver);
            pw.println(currentLength + " " + meanTime);
        }

        pw.close();
        assert true;
    }

    @Test
    public void should_saveSortingTimeToTheFile_when_sortingPessimisticCase() throws IOException {
        String filePath
                = "src/test/java/pl/edu/pw/ee/PerformanceTests/TimeData/insertionSort/insertionSortPessimistic.txt";
        PrintWriter pw = new PrintWriter(new FileWriter(filePath));

        for (int i = 0; i <= MAX_ELEMENTS_TO_SORT; i += STEP) {
            currentLength = i;
            long[] repeatTimeSaver = new long[NUMBER_OF_TIMES_TO_REPEAT];

            for(int j = 0 ; j < NUMBER_OF_TIMES_TO_REPEAT; j++) {
                setUpPessimisticCase();
                repeatTimeSaver[j] = performanceUtils.calculateSortingTime(sortingMethod, pessimisticArray);
            }

            long meanTime = performanceUtils.calculateMeanTime(repeatTimeSaver);
            pw.println(currentLength + " " + meanTime);
        }

        pw.close();
        assert true;
    }

}
