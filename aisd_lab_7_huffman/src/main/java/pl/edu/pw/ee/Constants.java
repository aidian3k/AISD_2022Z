package pl.edu.pw.ee;

public final class Constants {
    private static final String DECOMPRESSED_FILE_NAME = "decompressedFile.txt";
    private static final String COMPRESSED_FILE_NAME = "compressedFile.txt";
    private static final String KEYS_FILE_NAME = "keys.txt";
    public final int NUMBER_OF_BITS_IN_BYTE = 8;
    public final int BITS_TO_CODE_REMAINING_BITS = 3;
    private static Constants instance;

    private Constants() {
    }

    public static Constants getInstance() {
        if (instance == null) {
            instance = new Constants();
        }

        return instance;
    }

    public String getCompressedFilePath(String pathToRootDir) {
        return pathToRootDir + "/" + COMPRESSED_FILE_NAME;
    }

    public String getDecompressedFilePath(String pathToRootDir) {
        return pathToRootDir + "/" + DECOMPRESSED_FILE_NAME;
    }

    public String getKeysFilePath(String pathToRootDir) {
        return pathToRootDir + "/" + KEYS_FILE_NAME;
    }
}
