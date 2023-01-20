package pl.edu.pw.ee.aisd_lab_8_surprise;

import java.util.HashMap;

public class Suprise {

    public static int findNumOfSequences(int[] tab) {
        validateInput(tab);

        if (tab.length < 3) {
            return 0;
        }

        HashMap<Integer, Integer> frequency = new HashMap<>();

        for (int currentNumber : tab) {
            if (!frequency.containsKey(currentNumber)) {
                frequency.put(currentNumber, 1);
            } else {
                int currentFreq = frequency.get(currentNumber);
                frequency.put(currentNumber, currentFreq + 1);
            }
        }

        int theSameSize = 0;

        for (int freq : frequency.values()) {
            if (freq != 1) {
                theSameSize += freq;
            }
        }

        if (theSameSize == 0) {
            return tab.length;
        } else {
            int pomSize = tab.length - theSameSize + 1;
            return ((pomSize + 1) * pomSize) / 2;
        }
    }

    private static int calculateAllPossible(int n) {
        return (n * (n - 1) * (n - 2)) / 6;
    }

    private static void validateInput(int[] arr) {
        if (arr == null || arr.length > 99_999) {
            throw new IllegalArgumentException("Array cannot be null and the size should be <= 99_999_");
        }
    }

    public static void main(String[] args) {
        int[] sampleArr = {0, 1, 2, 2};
        System.out.println(findNumOfSequences(sampleArr));
    }
}
