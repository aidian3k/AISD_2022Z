package pl.edu.pw.ee.Performance;

import java.io.*;
import java.util.Map;

import static pl.edu.pw.ee.Performance.WriterMode.PRIMES;

public class FileHandler {
    private static final String WORDS_PATH_LIST = "src/test/java/pl/edu/pw/ee/Performance/wordlist.txt";
    private static final String PRIME_RESULTS = "src/test/java/pl/edu/pw/ee/Performance/Results/primeResults.txt";
    private static final String POWER2_RESULTS = "src/test/java/pl/edu/pw/ee/Performance/Results/powerResults.txt";

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

        if (mode.equals(PRIMES)) {
            writer = new PrintWriter(new FileWriter(PRIME_RESULTS));
        } else {
            writer = new PrintWriter(new FileWriter(POWER2_RESULTS));
        }

        for (Integer size : results.keySet()) {
            Long averageTime = results.get(size);
            writer.println(size + " : " + averageTime);
        }

        writer.close();
    }

}
