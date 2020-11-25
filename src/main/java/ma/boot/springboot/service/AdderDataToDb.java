package ma.boot.springboot.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import ma.boot.springboot.model.Product;
import ma.boot.springboot.model.Review;
import ma.boot.springboot.model.ReviewDto;
import ma.boot.springboot.model.Role;
import ma.boot.springboot.model.RoleName;
import ma.boot.springboot.model.User;
import ma.boot.springboot.model.Word;
import ma.boot.springboot.service.mapper.ProductMapper;
import ma.boot.springboot.service.mapper.ReviewMapper;
import ma.boot.springboot.service.mapper.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class AdderDataToDb {
    private static final int MIN_WORD_LENGTH = 2;
    private static final String WORDS_SPLITERATOR = "[!._,'@? ]";
    private final RoleService roleService;
    private final UserService userService;
    private final UserMapper userMapper;
    private final ProductMapper productMapper;
    private final ReviewMapper reviewMapper;
    private final ProductService productService;
    private final ReviewService reviewService;
    private final WordService wordService;

    public AdderDataToDb(RoleService roleService, UserService userService,
                         UserMapper userMapper, ProductMapper productMapper,
                         ReviewMapper reviewMapper, ProductService productService,
                         ReviewService reviewService, WordService wordService) {
        this.roleService = roleService;
        this.userService = userService;
        this.userMapper = userMapper;
        this.productMapper = productMapper;
        this.reviewMapper = reviewMapper;
        this.productService = productService;
        this.reviewService = reviewService;
        this.wordService = wordService;
    }

    public int addingDataToStorage(List<ReviewDto> dtos) {
        List<Word> wordsLoaded = loadWordsToDb(dtos);
        List<Product> productsLoaded = loadProductsToDb(dtos);
        List<User> usersLoaded = loadUsersToDb(dtos);

        Set<Review> reviewList = new HashSet<>();
        for (ReviewDto dto : dtos) {
            Review review = reviewMapper.mapReviewDtoToReview(dto);
            review.setUser(usersLoaded.stream()
                    .filter(u -> u.getProfileName().equals(dto.getProfileName()))
                    .findFirst().get());
            review.setProduct(productsLoaded.stream()
                    .filter(p -> p.getProductId().equals(dto.getProductId()))
                    .findFirst().get());
            reviewList.add(review);
        }
        return reviewService.addAll(reviewList).size();
    }

    private List<User> loadUsersToDb(List<ReviewDto> dtos) {
        Set<User> userSetToLoad = new HashSet<>();
        Role userRole = roleService.getByName(RoleName.USER);
        for (ReviewDto dto : dtos) {
            User user = userMapper.mapReviewDtoToUser(dto);
            user.setRoles(Set.of(userRole));
            userSetToLoad.add(user);
        }
        return userService.addAll(userSetToLoad);
    }

    private List<Product> loadProductsToDb(List<ReviewDto> dtos) {
        Set<Product> productSetToLoad = new HashSet<>();
        for (ReviewDto dto : dtos) {
            Product product = productMapper.mapReviewDtoToProduct(dto);
            productSetToLoad.add(product);
        }
        return productService.addAll(productSetToLoad);
    }

    private List<Word> loadWordsToDb(List<ReviewDto> dtos) {
        List<Word> wordSetToLoad = new ArrayList<>();
        for (ReviewDto dto : dtos) {
            for (String s : dto.getText().split(WORDS_SPLITERATOR)) {
                if (s.length() >= MIN_WORD_LENGTH) {
                    wordSetToLoad.add(new Word(s.toLowerCase()));
                }
            }
        }
        return wordService.addAll(wordSetToLoad);
    }
}
