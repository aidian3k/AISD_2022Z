package pl.edu.pw.ee.Performance;

import java.io.*;
import java.util.HashMap;

public class FileHandler {
    private static final String WORDS_PATH_LIST = "src/test/java/pl/edu/pw/ee/Performance/wordlist.txt";
    private static final String END_PATH = "src/test/java/pl/edu/pw/ee/Performance/results.txt";

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

    public void writeResultsToFile(HashMap<Integer, Long> results) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(END_PATH));

        for(Integer size : results.keySet()) {
            Long averageTime = results.get(size);
            writer.println(size + " : " + averageTime);
        }

        writer.close();
    }

}
