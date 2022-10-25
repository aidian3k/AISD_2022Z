package pl.edu.pw.ee.Performance;

import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.HashListChaining;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class PerformanceTest {

    private static final int MAXIMUM_POWER = 6;
    private static final int TIMES_TO_REPEAT = 30;
    private static final int BASE_HASH_SIZE = 4096;
    private String[] wordList;
    private FileHandler fileHandler;
    private HashListChaining<String> hashListChaining;
    private HashMap<Integer, Long> results;

    @Before
    public void setUp() throws IOException {
        this.fileHandler = new FileHandler();
        this.wordList = fileHandler.readWordListFromFile();
        this.results = new HashMap<>();
    }

    @Test
    public void wordPerformanceTest() throws IOException {
        int currentSize = BASE_HASH_SIZE;
        ArrayList<Long> singleResults = new ArrayList<>();

        for (int power = 0; power <= MAXIMUM_POWER; power++) {
            hashListChaining = new HashListChaining<>(currentSize);
            addWordsToHashList();

            for (int repeat = 0; repeat < TIMES_TO_REPEAT; repeat++) {
                singleResults.add(calculateSingleTime());
            }

            addAverageTimeToResults(currentSize, singleResults);
            currentSize *= 2;
            singleResults.clear();
        }

        fileHandler.writeResultsToFile(results);
    }

    private void addWordsToHashList() {
        for (String word : wordList) {
            hashListChaining.add(word);
        }
    }

    private void addAverageTimeToResults(int currentSize, List<Long> singleResults) {
        Collections.sort(singleResults);
        List<Long> wantedSublist = singleResults.subList(10, 21);
        Long calculatedAverageTime = calculateAverageValue(wantedSublist);
        results.put(currentSize, calculatedAverageTime);
    }

    private Long calculateSingleTime() {
        Long startingTime = System.nanoTime();

        for (String word : wordList) {
            hashListChaining.get(word);
        }

        Long endingTime = System.nanoTime();

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
