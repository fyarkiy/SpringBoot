package ma.boot.springboot;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import ma.boot.springboot.model.Product;
import ma.boot.springboot.model.Review;
import ma.boot.springboot.model.ReviewDto;
import ma.boot.springboot.model.User;
import ma.boot.springboot.service.mapper.ProductMapper;
import ma.boot.springboot.service.mapper.ReviewMapper;
import ma.boot.springboot.service.mapper.UserMapper;
import ma.boot.springboot.service.read.ReadingCsvFile;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ParseServiceTest {
    private static final String CORRECT_CSV_FILE = "three_records.csv";
    private static final String SECOND_USER = "A1D87F6ZCVE5NK";
    private static final String THIRD_PRODUCT = "B000LQOCH0";
    private static final Review FIRST_REVIEW = new Review(1L, 1,
            1, 5, "Good Quality Dog Food",
            "I have bought several of the Vitality canned dog food products and have found"
                    + " them all to be of good quality.");
    private static final LocalDateTime DATE_FIRST_COMMENT =
            LocalDateTime.of(2011, 04, 27, 03, 00, 00);
    private static List<ReviewDto> reviewList;
    private static UserMapper userMapper;
    private static ProductMapper productMapper;
    private static ReviewMapper reviewMapper;

    @BeforeClass
    public static void init() throws IOException {
        reviewList = ReadingCsvFile.readWithCsvBeanReader(CORRECT_CSV_FILE);
        userMapper = new UserMapper();
        productMapper = new ProductMapper();
        reviewMapper = new ReviewMapper();
    }

    @Test
    public void parseUserOk() throws IOException {

        List<User> users = new ArrayList<>();
        for (ReviewDto dto : reviewList) {
            users.add(userMapper.mapReviewDtoToUser(dto));
        }
        Assert.assertEquals(3, users.size());
        Assert.assertEquals(SECOND_USER, users.get(1).getUserId());
    }

    @Test
    public void parseProductOk() throws IOException {
        List<Product> products = new ArrayList<>();
        for (ReviewDto dto : reviewList) {
            products.add(productMapper.mapReviewDtoToProduct(dto));
        }
        Assert.assertEquals(3, products.size());
        Assert.assertEquals(THIRD_PRODUCT, products.get(2).getProductId());
    }

    @Test
    public void parseReviewOk() throws IOException {
        List<Review> reviews = new ArrayList<>();
        for (ReviewDto dto : reviewList) {
            reviews.add(reviewMapper.mapReviewDtoToReview(dto));
        }
        Assert.assertEquals(3, reviews.size());
        Assert.assertEquals(FIRST_REVIEW.getDenominator(), reviews.get(0).getDenominator());
        Assert.assertEquals(FIRST_REVIEW.getNumerator(), reviews.get(0).getNumerator());
        Assert.assertEquals(FIRST_REVIEW.getScore(), reviews.get(0).getScore());
        Assert.assertEquals(FIRST_REVIEW.getReviewId(), reviews.get(0).getReviewId());
        Assert.assertEquals(FIRST_REVIEW.getSummary(), reviews.get(0).getSummary());
        Assert.assertEquals(FIRST_REVIEW.getText(), reviews.get(0).getText());
        Assert.assertEquals(DATE_FIRST_COMMENT, reviews.get(0).getLocalDate());
    }
}
