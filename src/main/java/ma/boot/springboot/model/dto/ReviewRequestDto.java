package ma.boot.springboot.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewRequestDto {
    private Long id;
    private String productId;
    private String userId;
    private String profileName;
    private Integer numerator;
    private Integer denominator;
    private Integer score;
    private String date;
    private String summary;
    private String text;
}
