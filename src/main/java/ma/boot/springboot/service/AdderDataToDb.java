package ma.boot.springboot.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import ma.boot.springboot.model.Product;
import ma.boot.springboot.model.Review;
import ma.boot.springboot.model.ReviewDto;
import ma.boot.springboot.model.Role;
import ma.boot.springboot.model.RoleName;
import ma.boot.springboot.model.User;
import ma.boot.springboot.service.mapper.ProductMapper;
import ma.boot.springboot.service.mapper.ReviewMapper;
import ma.boot.springboot.service.mapper.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class AdderDataToDb {
    private final RoleService roleService;
    private final UserService userService;
    private final UserMapper userMapper;
    private final ProductMapper productMapper;
    private final ReviewMapper reviewMapper;
    private final ProductService productService;
    private final ReviewService reviewService;

    public AdderDataToDb(RoleService roleService, UserService userService,
                         UserMapper userMapper, ProductMapper productMapper,
                         ReviewMapper reviewMapper, ProductService productService,
                         ReviewService reviewService) {
        this.roleService = roleService;
        this.userService = userService;
        this.userMapper = userMapper;
        this.productMapper = productMapper;
        this.reviewMapper = reviewMapper;
        this.productService = productService;
        this.reviewService = reviewService;
    }

    public int addingDataToStorage(List<ReviewDto> dtos) {
        Set<User> userSetToLoad = new HashSet<>();
        Set<Product> productSetToLoad = new HashSet<>();
        Role userRole = roleService.getByName(RoleName.USER);
        for (ReviewDto dto : dtos) {
            User user = userMapper.mapReviewDtoToUser(dto);
            user.setRoles(Set.of(userRole));
            userSetToLoad.add(user);
            Product product = productMapper.mapReviewDtoToProduct(dto);
            productSetToLoad.add(product);
        }
        List<User> userLoaded = userService.addAll(userSetToLoad);
        List<Product> productLoaded = productService.addAll(productSetToLoad);

        Set<Review> reviewList = new HashSet<>();
        for (ReviewDto dto : dtos) {
            Review review = reviewMapper.mapReviewDtoToReview(dto);
            review.setUser(userLoaded.stream()
                    .filter(u -> u.getProfileName().equals(dto.getProfileName()))
                    .findFirst().get());
            review.setProduct(productLoaded.stream()
                    .filter(p -> p.getProductId().equals(dto.getProductId()))
                    .findFirst().get());
            reviewList.add(review);
        }
        return reviewService.addAll(reviewList).size();
    }
}
