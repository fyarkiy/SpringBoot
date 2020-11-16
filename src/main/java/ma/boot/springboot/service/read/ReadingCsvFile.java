package ma.boot.springboot.service.read;

import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import ma.boot.springboot.model.Review;
import org.apache.log4j.Logger;
import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ParseLong;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.exception.SuperCsvException;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;
import org.supercsv.util.CsvContext;

public class ReadingCsvFile {
    private static final Logger logger = Logger.getLogger(ReadingCsvFile.class);

    public static int readWithCsvBeanReader(String fileName) throws IOException {
        int records = 0;

        try (ICsvBeanReader beanReader = new CsvBeanReader(new FileReader(fileName),
                CsvPreference.STANDARD_PREFERENCE)) {
            final String[] header = new String[]{"id", "productId", "userId", "profileName",
                    "numerator", "denominator", "score", "date", "summary", "text"};
            beanReader.getHeader(true);
            final CellProcessor[] processors = getProcessors();
            Review review;
            while ((review = beanReader.read(Review.class, header, processors)) != null) {
                records++;
            }
        } catch (IOException | SuperCsvException e) {
            logger.info("line # " + (records + 1) + " incorrect");
            throw new RuntimeException("incorrect file", e);
        }
        return records;
    }

    private static CellProcessor[] getProcessors() {
        final CellProcessor parseDate = new CellProcessorAdaptor() {
            @Override
            public Object execute(Object o, CsvContext csvContext) {
                Date date = new Date(Long.parseLong(o.toString()) * 1000L);
                SimpleDateFormat jdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                return jdf.format(date);
            }
        };
        final CellProcessor[] processors = new CellProcessor[]{
                new ParseLong(), // id
                new NotNull(), // productId
                new NotNull(), // UserId
                new NotNull(), // ProfileName
                new ParseInt(), // HelpfulnessNumerator
                new ParseInt(), // HelpfulnessDenominator
                new ParseInt(), // Score
                new NotNull(parseDate), // Date
                new NotNull(), // Summary
                new NotNull(), // Text
        };
        return processors;
    }
}
