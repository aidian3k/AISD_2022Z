package pl.edu.pw.ee;

import static java.lang.Math.min;
import java.util.ArrayList;
import static java.util.stream.Collectors.toList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import pl.edu.pw.ee.services.PatternSearch;

public class DeterministicFiniteAutomatonTextSearch implements PatternSearch {

    private class Key {

        private int state;
        private char sign;

        public Key(int state, char sign) {
            this.state = state;
            this.sign = sign;
        }

        @Override
        public boolean equals(Object comparingKey) {
            return comparingKey instanceof Key && ((Key) comparingKey).sign == this.sign
                    && ((Key) comparingKey).state == this.state;
        }

        @Override
        public int hashCode() {
            return Objects.hash(state, sign);
        }
    }

    private String pattern;
    private Map<Key, Integer> transMap;

    public DeterministicFiniteAutomatonTextSearch(String pattern) {
        validatePattern(pattern);

        this.pattern = pattern;
        buildTransitionMatrix();
    }

    @Override
    public int findFirst(String text) {
        validateInput(text);
        int n = text.length();
        int acceptedState = pattern.length();
        int result = -1;

        int state = 0;
        Integer newState;

        for (int i = 0; i < n; i++) {
            newState = transMap.get(new Key(state, text.charAt(i)));

            if (newState == null) {
                state = 0;
            } else {
                state = newState;
            }

            if (state == acceptedState) {
                result = i - acceptedState + 1;
                break;
            }
        }

        return result;
    }

    @Override
    public int[] findAll(String text) {
        validateInput(text);
        int n = text.length();
        int acceptedState = pattern.length();

        int state = 0;
        Integer newState;
        List<Integer> indexAnswerList = new ArrayList<>();

        for (int i = 0; i < n; ++i) {
            newState = transMap.get(new Key(state, text.charAt(i)));

            if (newState == null) {
                state = 0;
            } else {
                state = newState;
            }

            if (state == acceptedState) {
                indexAnswerList.add(i - acceptedState + 1);
            }
        }

        return mapListToArray(indexAnswerList);
    }

    private int[] mapListToArray(List<Integer> indexList) {
        if (indexList == null) {
            throw new IllegalArgumentException("List cannot be null!");
        }

        int listSize = indexList.size();
        int[] answerArray = new int[listSize];

        for (int i = 0; i < listSize; ++i) {
            answerArray[i] = indexList.get(i);
        }

        return answerArray;
    }

    private void validateInput(String txt) {
        if (txt == null) {
            throw new IllegalArgumentException("Input text cannot be null!");
        }
    }

    private void validatePattern(String pattern) {
        if (pattern == null) {
            throw new IllegalArgumentException("Input pattern cannot be null!");
        }

        if (pattern.equals("")) {
            throw new IllegalArgumentException("Input pattern cannot be empty!");
        }
    }

    private void buildTransitionMatrix() {
        transMap = new HashMap<>();

        int m = pattern.length();
        List<Character> alphabet = getAlphabetOfPattern();

        for (int q = 0; q <= m; q++) {
            for (char sign : alphabet) {

                int k = min(m + 1, q + 2);
                k--;

                while (k > 0 && !isSuffixOfPattern(k, q, sign)) {
                    k--;
                }

                transMap.put(new Key(q, sign), k);
            }
        }
    }

    private List<Character> getAlphabetOfPattern() {
        List<Character> signs = pattern.chars()
                .distinct()
                .mapToObj(c -> (char) c)
                .collect(toList());

        return signs;
    }

    private boolean isSuffixOfPattern(int kIndex, int qIndex, char sign) {
        boolean isSuffix = false;

        if (pattern.charAt(--kIndex) == sign) {
            isSuffix = true;

            while (kIndex > 0) {
                kIndex--;
                qIndex--;

                if (pattern.charAt(kIndex) != pattern.charAt(qIndex)) {
                    isSuffix = false;
                    break;
                }
            }
        }

        return isSuffix;
    }

}
