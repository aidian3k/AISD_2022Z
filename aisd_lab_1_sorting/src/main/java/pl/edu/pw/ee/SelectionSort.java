package pl.edu.pw.ee;

import pl.edu.pw.ee.heap.services.Sorting;

public class SelectionSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Nums array cannot be null");
        }

        int n = nums.length;

        for (int i = 0; i < n - 1; i++) {
            int currentMinId = i;

            for (int j = i + 1; j < n; j++) {
                if (nums[currentMinId] > nums[j]) {
                    currentMinId = j;
                }
            }

            swap(nums, i, currentMinId);
        }
    }

    private void swap(double[] nums, int firstId, int secondId) {
        if (firstId != secondId) {
            double firstValue = nums[firstId];
            nums[firstId] = nums[secondId];
            nums[secondId] = firstValue;
        }
    }

}
