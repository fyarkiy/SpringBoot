package ma.boot.springboot.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "review_id")
    private Long reviewId;
    @ManyToMany
    @Column(name = "product_id")
    private Long productId;
    @ManyToMany
    @Column(name = "user_id")
    private Long userId;
    private Integer numerator;
    private Integer denominator;
    private Integer score;
    @Column(name = "order_date")
    private LocalDateTime localDate;
    private String summary;
    private String text;

    public Review() {
    }

    public Review(Long reviewId, Integer numerator, Integer denominator,
                  Integer score, String summary, String text) {
        this.reviewId = reviewId;
        this.numerator = numerator;
        this.denominator = denominator;
        this.score = score;
        this.summary = summary;
        this.text = text;
    }
}
