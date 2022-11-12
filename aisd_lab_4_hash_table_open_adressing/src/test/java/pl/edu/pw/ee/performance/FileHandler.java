package pl.edu.pw.ee.performance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import static pl.edu.pw.ee.performance.WriterMode.DOUBLE;
import static pl.edu.pw.ee.performance.WriterMode.LINEAR;


public class FileHandler {
    private static final String WORDS_PATH_LIST = "src/main/resources/wordlist_100_000.txt";
    private static final String DOUBLE_RESULTS = "src/test/java/pl/edu/pw/ee/performance/results/doubleResults.txt";
    private static final String LINEAR_RESULTS = "src/test/java/pl/edu/pw/ee/performance/results/linearResults.txt";
    private static final String QUADRATIC_RESULTS = "src/test/java/pl/edu/pw/ee/performance/results/quadraticResults.txt";

    public String[] readWordListFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(WORDS_PATH_LIST));
        String[] wordList = new String[100_000];
        int wordCounter = 0;
        String line;

        while ((line = reader.readLine()) != null) {
            wordList[wordCounter] = line;
            wordCounter++;
        }

        reader.close();
        return wordList;
    }

    public void writeResultsToFile(Map<Integer, Long> results, WriterMode mode) throws IOException {
        PrintWriter writer;

        if (mode.equals(DOUBLE)) {
            writer = new PrintWriter(new FileWriter(DOUBLE_RESULTS));
        } else if(mode.equals(LINEAR)) {
            writer = new PrintWriter(new FileWriter(LINEAR_RESULTS));
        } else {
            writer = new PrintWriter(new FileWriter(QUADRATIC_RESULTS));
        }

        for (Integer size : results.keySet()) {
            Long averageTime = results.get(size);
            writer.println(size + " : " + averageTime);
        }

        writer.close();
    }

}
