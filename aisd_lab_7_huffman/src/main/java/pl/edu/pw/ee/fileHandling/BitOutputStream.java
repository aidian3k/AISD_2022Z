package pl.edu.pw.ee.fileHandling;


import java.io.IOException;
import java.io.OutputStream;

public class BitOutputStream {

    private final OutputStream outputStream;
    private byte currentByte;
    private int filledBits;

    public BitOutputStream(OutputStream outputStream) {
        validateConstructorInput(outputStream);
        this.outputStream = outputStream;
    }

    public void write(int b) throws IOException {
        if (b != 0 && b != 1) {
            throw new IllegalArgumentException("Argument must be 0 or 1");
        }

        currentByte = (byte) ((currentByte << 1) | b);
        filledBits++;

        if (filledBits == 8) {
            outputStream.write(currentByte);
            currentByte = 0;
            filledBits = 0;
        }
    }

    public void fillRemainingBits() throws IOException {
        while (filledBits != 0) {
            this.write(0);
        }
    }

    private void validateConstructorInput(OutputStream outputStream) {
        if (outputStream == null) {
            throw new IllegalArgumentException("Output stream cannot be null!");
        }
    }

    public void close() throws IOException {
        fillRemainingBits();
        outputStream.close();
    }

}
