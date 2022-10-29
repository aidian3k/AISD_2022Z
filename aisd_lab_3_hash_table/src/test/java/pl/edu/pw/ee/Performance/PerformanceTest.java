package pl.edu.pw.ee.Performance;

import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.HashListChaining;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static pl.edu.pw.ee.Performance.WriterMode.POWER2;
import static pl.edu.pw.ee.Performance.WriterMode.PRIMES;

public class PerformanceTest {
    private static final int TIMES_TO_REPEAT = 30;
    private static final int[] PERFORMANCE_PRIME_SIZES = {4093, 6427, 12289, 24593, 49157, 100459, 262147};
    private static final int[] PERFORMANCE_POWER2_SIZES = {4096, 8192, 16384, 32768, 65536, 131072, 262144};
    private String[] wordList;
    private FileHandler fileHandler;
    private HashListChaining<String> hashListChaining;
    private Map<Integer, Long> results;

    @Before
    public void setUp() throws IOException {
        this.fileHandler = new FileHandler();
        this.wordList = fileHandler.readWordListFromFile();
        this.results = new LinkedHashMap<>();
    }

    @Test
    public void primePerformanceTest() throws IOException {
        generateResults(PRIMES, PERFORMANCE_PRIME_SIZES);
    }

    @Test
    public void powerOfTwoPerformanceTest() throws IOException {
        generateResults(POWER2, PERFORMANCE_POWER2_SIZES);
    }

    private void generateResults(WriterMode mode, int[] sizesArray) throws IOException {
        ArrayList<Long> singleResults = new ArrayList<>();

        for (int performanceSize : sizesArray) {
            hashListChaining = new HashListChaining<>(performanceSize);
            addWordsToHashList();

            for (int repeat = 0; repeat < TIMES_TO_REPEAT; repeat++) {
                singleResults.add(calculateSingleTime());
            }

            addAverageTimeToResults(performanceSize, singleResults);
            singleResults.clear();
        }

        fileHandler.writeResultsToFile(results, mode);
    }

    private void addWordsToHashList() {
        for (String word : wordList) {
            hashListChaining.add(word);
        }
    }

    private void addAverageTimeToResults(int currentSize, List<Long> singleResults) {
        Collections.sort(singleResults);
        List<Long> wantedSublist = singleResults.subList(10, 20);
        Long calculatedAverageTime = calculateAverageValue(wantedSublist);

        results.put(currentSize, calculatedAverageTime);
    }

    private Long calculateSingleTime() {
        Long startingTime = System.currentTimeMillis();

        for (String word : wordList) {
            hashListChaining.get(word);
        }

        Long endingTime = System.currentTimeMillis();

        return endingTime - startingTime;
    }

    private Long calculateAverageValue(List<Long> values) {
        Long currentSum = 0L;
        int n = values.size();

        for (Long value : values) {
            currentSum += value;
        }

        return currentSum / n;
    }

}
