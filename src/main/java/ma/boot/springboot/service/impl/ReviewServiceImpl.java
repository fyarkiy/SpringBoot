package ma.boot.springboot.service.impl;

import java.util.List;
import java.util.Set;
import ma.boot.springboot.model.Review;
import ma.boot.springboot.model.ReviewDto;
import ma.boot.springboot.repository.ReviewRepository;
import ma.boot.springboot.service.ReviewService;
import ma.boot.springboot.service.mapper.ReviewMapper;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewMapper reviewMapper;
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewMapper reviewMapper, ReviewRepository reviewRepository) {
        this.reviewMapper = reviewMapper;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review add(ReviewDto dto) {
        return reviewRepository.save(reviewMapper.mapReviewDtoToReview(dto));
    }

    @Override
    public List<Review> addAll(Set<Review> reviews) {
        return reviewRepository.saveAll(reviews);
    }

    @Override
    public Review get(Long id) {
        return reviewRepository.getOne(id);
    }

    @Override
    public List<Review> getAll() {
        return reviewRepository.findAll();
    }
}
