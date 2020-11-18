package ma.boot.springboot.service.read;

import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ma.boot.springboot.model.ReviewDto;
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
    private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";

    public static List<ReviewDto> readWithCsvBeanReader(String fileName) throws IOException {
        List<ReviewDto> reviews = new ArrayList<>();
        try (ICsvBeanReader beanReader = new CsvBeanReader(new FileReader(fileName),
                CsvPreference.STANDARD_PREFERENCE)) {
            final String[] header = new String[]{"id", "productId", "userId", "profileName",
                    "numerator", "denominator", "score", "date", "summary", "text"};
            beanReader.getHeader(true);
            final CellProcessor[] processors = getProcessors();
            ReviewDto reviewDto;
            while ((reviewDto = beanReader.read(ReviewDto.class, header, processors)) != null) {
                reviews.add(reviewDto);
            }
        } catch (IOException | SuperCsvException e) {
            logger.info("line # " + reviews.size() + " incorrect");
            throw new RuntimeException("incorrect file", e);
        }
        return reviews;
    }

    private static CellProcessor[] getProcessors() {
        final CellProcessor parseDate = new CellProcessorAdaptor() {
            @Override
            public Object execute(Object o, CsvContext csvContext) {
                Date date = new Date(Long.parseLong(o.toString()) * 1000L);
                SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
                return dateFormat.format(date);
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
