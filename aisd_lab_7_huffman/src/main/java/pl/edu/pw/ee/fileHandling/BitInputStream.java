package pl.edu.pw.ee.fileHandling;

import java.io.IOException;
import java.io.InputStream;

public class BitInputStream {
    private final InputStream inputStream;
    private int currentByte;
    private int numberOfRemainingBits;

    public BitInputStream(InputStream inputStream) {
        validateInStream(inputStream);
        this.inputStream = inputStream;
    }

    public int read() throws IOException {
        if (currentByte == -1) {
            return -1;
        }

        if (numberOfRemainingBits == 0) {
            currentByte = inputStream.read();
            numberOfRemainingBits = 8;
        }


        numberOfRemainingBits--;
        return (currentByte >> numberOfRemainingBits) & 1;
    }

    public void close() throws IOException {
        inputStream.close();

        currentByte = -1;
        numberOfRemainingBits = 0;
    }

    private void validateInStream(InputStream inputStream) {
        if (inputStream == null) {
            throw new IllegalArgumentException("It cannot be null!");
        }
    }

}
