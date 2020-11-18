package ma.boot.springboot.model;

import lombok.Data;

@Data
public class ReviewDto {
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
