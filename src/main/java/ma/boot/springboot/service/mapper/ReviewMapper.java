package ma.boot.springboot.service.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import ma.boot.springboot.model.Review;
import ma.boot.springboot.model.dto.ReviewRequestDto;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);

    public Review mapReviewDtoToReview(ReviewRequestDto dto) {
        Review review = new Review(dto.getId(), dto.getNumerator(),
                dto.getDenominator(), dto.getScore(),
                dto.getSummary(), dto.getText());
        review.setLocalDate(LocalDateTime.parse(dto.getDate(), DATE_TIME_FORMATTER));
        return review;
    }
}
