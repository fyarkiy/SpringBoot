package ma.boot.springboot;

import java.io.IOException;
import ma.boot.springboot.service.read.ReadingCsvFile;
import org.junit.Assert;
import org.junit.Test;

public class ReadingCsvFileTests {
    private static final String CORRECT_CSV_FILE = "three_records.csv";
    private static final String NOT_EXISTED_FILE = "file123.csv";
    private static final String WRONG_DATA = "incorrect_data.csv";
    private static final String EMPTY_FILE = "empty_file.csv";
    private static final int NUMBER_OF_RECORDS = 3;

    @Test
    public void loadCorrectFile() throws Exception {
        int result = ReadingCsvFile.readWithCsvBeanReader(CORRECT_CSV_FILE).size();
        Assert.assertEquals(NUMBER_OF_RECORDS, result);
    }

    @Test
    public void loadEmptyFile() throws Exception {
        int result = ReadingCsvFile.readWithCsvBeanReader(EMPTY_FILE).size();
        Assert.assertEquals(0, result);
    }

    @Test(expected = RuntimeException.class)
    public void loadNonExistingFile() throws IOException {
        ReadingCsvFile.readWithCsvBeanReader(NOT_EXISTED_FILE);
    }

    @Test(expected = RuntimeException.class)
    public void loadFileWithIncorrectFields() throws IOException {
        ReadingCsvFile.readWithCsvBeanReader(WRONG_DATA);
    }
}
