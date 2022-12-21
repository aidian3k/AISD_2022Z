package pl.edu.pw.ee;

public class LongestCommonSubsequence {
    private final String topStr;
    private final String leftStr;
    private int[][] characterMatrix;
    private char[][] displayMatrix;

    public LongestCommonSubsequence(String topStr, String leftStr) {
        validateInputData(topStr, leftStr);

        this.topStr = topStr;
        this.leftStr = leftStr;
        this.fillCharacterMatrix();
        this.prepareDisplayMatrix();
    }

    public void display() {
        for (int row = 0; row < displayMatrix.length; ++row) {
            for (int column = 0; column < displayMatrix[row].length; ++column) {
                char singleCharacter = displayMatrix[row][column];
                System.out.print(singleCharacter);
            }

            System.out.print("\n");
        }
    }

    public String findLCS() {
        StringBuilder resultLongestCommonSubsequence = new StringBuilder();

        return recreateLongestCommonSubsequence(resultLongestCommonSubsequence);
    }

    public char[][] getDisplayMatrix() {
        return this.displayMatrix;
    }

    private String recreateLongestCommonSubsequence(StringBuilder result) {
        int currentColumn = topStr.length();
        int currentRow = leftStr.length();

        while (currentColumn > 0 && currentRow > 0) {
            if (topStr.charAt(currentColumn - 1) == leftStr.charAt(currentRow - 1)) {
                char singleCharacter = topStr.charAt(currentColumn - 1);
                result.append(singleCharacter);

                currentRow--;
                currentColumn--;
            } else if (characterMatrix[currentRow - 1][currentColumn] >= characterMatrix[currentRow][currentColumn - 1]) {
                currentRow--;
            } else {
                currentColumn--;
            }
        }

        return result.reverse().toString();
    }

    private void fillCharacterMatrix() {
        int topStringLength = topStr.length();
        int leftStringLength = leftStr.length();

        this.characterMatrix = new int[leftStringLength + 1][topStringLength + 1];

        for (int row = 1; row <= leftStringLength; ++row) {
            for (int column = 1; column <= topStringLength; ++column) {
                if (leftStr.charAt(row - 1) == topStr.charAt(column - 1)) {
                    characterMatrix[row][column] = characterMatrix[row - 1][column - 1] + 1;
                } else {
                    characterMatrix[row][column] = Math.max(characterMatrix[row - 1][column], characterMatrix[row][column - 1]);
                }
            }
        }
    }

    private void prepareDisplayMatrix() {
        int topStrLength = topStr.length();
        int leftStrLength = leftStr.length();
        int matrixHeight = leftStrLength * 4 + 9;
        int matrixWidth = topStrLength * 8 + 17;

        this.displayMatrix = new char[matrixHeight][matrixWidth];

        setMatrixShape(matrixHeight);
        fillMatrixWithStringCharacters(matrixWidth, matrixHeight);
        setMatrixNumbers(matrixWidth, matrixHeight);
        setMatrixPath();
    }

    private void setMatrixPath() {
        int currentNumberMatrixRow = leftStr.length();
        int currentNumberMatrixColumn = topStr.length();

        while (currentNumberMatrixRow > 0 && currentNumberMatrixColumn > 0) {
            int correspondingRowPosition = 10 + (currentNumberMatrixRow - 1) * 4;
            int correspondingColumnPosition = 18 + (currentNumberMatrixColumn - 1) * 8;
            char direction;

            if (leftStr.charAt(currentNumberMatrixRow - 1) == topStr.charAt(currentNumberMatrixColumn - 1)) {
                direction = '\\';
                displayMatrix[correspondingRowPosition - 1][correspondingColumnPosition] = direction;

                currentNumberMatrixRow--;
                currentNumberMatrixColumn--;
            } else if (characterMatrix[currentNumberMatrixRow - 1][currentNumberMatrixColumn] >= characterMatrix[currentNumberMatrixRow][currentNumberMatrixColumn - 1]) {
                direction = '^';
                displayMatrix[correspondingRowPosition - 1][correspondingColumnPosition + 2] = direction;

                currentNumberMatrixRow--;
            } else {
                direction = '<';
                displayMatrix[correspondingRowPosition][correspondingColumnPosition] = direction;

                currentNumberMatrixColumn--;
            }
        }
    }

    private void setMatrixNumbers(int matrixWidth, int matrixHeight) {
        int numberMatrixRow = 0;
        int numberMatrixColumn = 0;

        for (int row = 6; row < matrixHeight; row += 4) {
            for (int column = 9; column < matrixWidth; column += 8) {
                char currentNumber = (char) (characterMatrix[numberMatrixRow][numberMatrixColumn] + '0');
                displayMatrix[row][column + 3] = currentNumber;
                numberMatrixColumn++;
            }

            numberMatrixColumn = 0;
            numberMatrixRow++;
        }
    }

    private void fillMatrixWithStringCharacters(int matrixWidth, int matrixHeight) {
        int stringIterator = 0;
        char singleCharacter;

        for (int column = 17; column < matrixWidth; column += 8) {
            singleCharacter = topStr.charAt(stringIterator);

            if (!setSpecialCharacter(2, column + 3, singleCharacter)) {
                displayMatrix[2][column + 3] = singleCharacter;
            }

            stringIterator++;
        }

        stringIterator = 0;

        for (int row = 10; row < matrixHeight; row += 4) {
            singleCharacter = leftStr.charAt(stringIterator);

            if (!setSpecialCharacter(row, 4, singleCharacter)) {
                displayMatrix[row][4] = singleCharacter;
            }

            stringIterator++;
        }
    }

    private boolean setSpecialCharacter(int row, int column, char sign) {
        if (sign == '\n') {
            displayMatrix[row][column - 1] = '\\';
            displayMatrix[row][column] = 'n';

            return true;
        } else if (sign == '\r') {
            displayMatrix[row][column - 1] = '\\';
            displayMatrix[row][column] = 'r';

            return true;
        } else if (sign == '\t') {
            displayMatrix[row][column - 1] = '\\';
            displayMatrix[row][column] = 't';

            return true;
        } else if (sign == ' ') {
            displayMatrix[row][column - 1] = '\\';
            displayMatrix[row][column] = 's';

            return true;
        } else {
            return false;
        }
    }

    private void setMatrixShape(int matrixHeight) {
        for (int row = 0; row < matrixHeight; ++row) {
            if (row % 4 == 0) {
                makeSingleRowSpacing(row, "---------");
            } else {
                makeSingleRowSpacing(row, "|       ");
            }
        }
    }

    private void makeSingleRowSpacing(int row, String rowSpacing) {
        int textIterator = 0;

        for (int column = 0; column < displayMatrix[row].length; ++column) {
            displayMatrix[row][column] = rowSpacing.charAt(textIterator);
            textIterator++;

            if (textIterator >= rowSpacing.length()) {
                textIterator = 0;
            }
        }
    }

    private void validateInputData(String topStr, String leftStr) {
        if (topStr == null || leftStr == null) {
            throw new IllegalArgumentException("Top and left string cannot be null values!");
        }
    }

}
