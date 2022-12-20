package pl.edu.pw.ee;

public final class LongestCommonSubsequence {

    private final String topStr;
    private final String leftStr;
    private int[][] characterMatrix;
    private char[][] directionMatrix;

    public LongestCommonSubsequence(String topStr, String leftStr) {
        validateInputData(topStr, leftStr);

        this.topStr = topStr;
        this.leftStr = leftStr;
        this.fillCharacterMatrix();
    }
    
    public void display() {
        //TODO
    }
    
    public String findLCS() {
        StringBuilder resultLongestCommonSubsequence = new StringBuilder();

        int topStringLength = topStr.length();
        int leftStringLength = leftStr.length();

        return recreateLongestCommonSubsequence(leftStringLength, topStringLength, resultLongestCommonSubsequence);
    }

    private String recreateLongestCommonSubsequence(int row, int column, StringBuilder result) {
        if (row == 0 || column == 0) {
            return result.reverse().toString();
        }

        if (characterMatrix[row][column] - 1 == characterMatrix[row - 1][column - 1]) {
            result.append(leftStr.charAt(row - 1));
            return recreateLongestCommonSubsequence(row - 1, column - 1, result);
        } else if (characterMatrix[row][column] == characterMatrix[row - 1][column]) {
            return recreateLongestCommonSubsequence(row - 1, column, result);
        } else {
            return recreateLongestCommonSubsequence(row, column - 1, result);
        }
    }

    public void fillCharacterMatrix() {
        int topStringLength = topStr.length();
        int leftStringLength = leftStr.length();

        this.characterMatrix = new int[leftStringLength + 1][topStringLength + 1];
        this.directionMatrix = new char[leftStringLength + 1][topStringLength + 1];

        for (int row = 1; row <= leftStringLength; ++row) {
            for (int column = 1; column <= topStringLength; ++column) {
                if (leftStr.charAt(row - 1) == topStr.charAt(column - 1)) {
                    characterMatrix[row][column] = characterMatrix[row - 1][column - 1] + 1;
                    directionMatrix[row][column] = '\\';
                } else if (characterMatrix[row - 1][column] >= characterMatrix[row][column - 1]) {
                    characterMatrix[row][column] = characterMatrix[row - 1][column];
                    directionMatrix[row][column] = '^';
                } else {
                    characterMatrix[row][column] = characterMatrix[row][column - 1];
                    directionMatrix[row][column] = '<';
                }
            }
        }
    }

    private void validateInputData(String topStr, String leftStr) {
        if (topStr == null || leftStr == null) {
            throw new IllegalArgumentException("Top and left string cannot be null values!");
        }
    }

}
