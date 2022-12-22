package pl.edu.pw.ee.PerformanceTests;

import pl.edu.pw.ee.heap.services.Sorting;

import java.util.Arrays;
import java.util.Random;

public class PerformanceUtils {

    public double[] createRandomArray(int size, Random rnd) {
        double[] nums = new double[size];

        for (int i = 0; i < size; i++) {
            nums[i] = rnd.nextDouble();
        }

        return nums;
    }

    public long calculateSortingTime(Sorting sortingMethod, double[] nums) {
        long startTime = System.currentTimeMillis();
        sortingMethod.sort(nums);
        long endTime = System.currentTimeMillis();

        return (endTime - startTime);
    }

    public void reverseArray(double[] nums) {
        int n = nums.length;

        for (int i = 0; i < n / 2; i++) {
            double tempValue = nums[i];
            nums[i] = nums[n - 1 - i];
            nums[n - 1 - i] = tempValue;
        }
    }

    public long calculateMeanTime(long[] repeatTimeSaver) {
        long currentSum = 0;
        int n = repeatTimeSaver.length;

        for (long number : repeatTimeSaver) {
            currentSum += number;
        }

        return currentSum / n;
    }

    public void generateQuickSortBestCase(double[] nums) {
        int currentId = 0;
        int n = nums.length;

        if(n < 3) {
            return;
        }

        double[] sortedNums = new double[n];
        System.arraycopy(nums, 0, sortedNums, 0, n);

        Arrays.sort(sortedNums);

        while(nums[currentId] != sortedNums[n/2]) {
            currentId++;
        }

        swap(nums, currentId, 0);
    }

    private void swap(double[] nums, int firstId, int secondId) {
        if (firstId != secondId) {
            double firstValue = nums[firstId];
            nums[firstId] = nums[secondId];
            nums[secondId] = firstValue;
        }
    }
}
