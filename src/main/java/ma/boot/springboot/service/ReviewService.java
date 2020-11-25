package ma.boot.springboot.service;

import ma.boot.springboot.model.Review;
import ma.boot.springboot.model.ReviewDto;

public interface ReviewService extends GenericService<Review> {
    Review add(ReviewDto dto);

}
