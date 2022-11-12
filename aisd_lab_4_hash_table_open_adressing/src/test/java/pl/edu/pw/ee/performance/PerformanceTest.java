package pl.edu.pw.ee.performance;

import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.HashDoubleHashing;
import pl.edu.pw.ee.HashLinearProbing;
import pl.edu.pw.ee.HashOpenAddressing;
import pl.edu.pw.ee.HashQuadraticProbing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static pl.edu.pw.ee.performance.WriterMode.*;

public class PerformanceTest {
    private static final int TIMES_TO_REPEAT = 30;
    private static final int[] PERFORMANCE_SIZES = {512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144};
    private String[] wordList;
    private FileHandler fileHandler;
    private HashOpenAddressing<String> hashOpenAddressing;
    private Map<Integer, Long> results;

    @Before
    public void setUp() throws IOException {
        this.fileHandler = new FileHandler();
        this.wordList = fileHandler.readWordListFromFile();
        this.results = new LinkedHashMap<>();
    }

    @Test
    public void linearPerformanceTest() throws IOException {
        generateResults(LINEAR);
    }

    @Test
    public void quadraticPerformanceTest() throws IOException {
        generateResults(QUADRATIC);
    }

    @Test
    public void doublePerformanceTest() throws IOException {
        generateResults(DOUBLE);
    }

    private void generateResults(WriterMode mode) throws IOException {
        ArrayList<Long> singleResults = new ArrayList<>();

        for (int performanceSize : PERFORMANCE_SIZES) {
            for (int repeat = 0; repeat < TIMES_TO_REPEAT; repeat++) {
                hashOpenAddressing = initializeHashList(mode, performanceSize);
                singleResults.add(calculateSingleTime());
            }

            addAverageTimeToResults(performanceSize, singleResults);
            singleResults.clear();
        }

        fileHandler.writeResultsToFile(results, mode);
    }

    private HashOpenAddressing<String> initializeHashList(WriterMode mode, int performanceSize) {
        if (mode.equals(LINEAR)) {
            return new HashLinearProbing<>(performanceSize);
        } else if (mode.equals(QUADRATIC)) {
            return new HashQuadraticProbing<>(performanceSize, 0.5, 0.5);
        } else {
            return new HashDoubleHashing<>(performanceSize);
        }
    }

    private void addAverageTimeToResults(int currentSize, List<Long> singleResults) {
        Collections.sort(singleResults);
        List<Long> wantedSublist = singleResults.subList(10, 20);
        Long calculatedAverageTime = calculateAverageValue(wantedSublist);

        results.put(currentSize, calculatedAverageTime);
    }

    private Long calculateSingleTime() {
        long startingTime = System.currentTimeMillis();

        for (String word : wordList) {
            hashOpenAddressing.put(word);
        }

        long endingTime = System.currentTimeMillis();

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
