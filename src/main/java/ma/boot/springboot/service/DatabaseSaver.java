package ma.boot.springboot.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import ma.boot.springboot.model.Product;
import ma.boot.springboot.model.Review;
import ma.boot.springboot.model.Role;
import ma.boot.springboot.model.RoleName;
import ma.boot.springboot.model.User;
import ma.boot.springboot.model.Word;
import ma.boot.springboot.model.dto.ProductRequestDto;
import ma.boot.springboot.model.dto.ReviewRequestDto;
import ma.boot.springboot.model.dto.UserRequestDto;
import ma.boot.springboot.service.mapper.ProductMapper;
import ma.boot.springboot.service.mapper.ReviewMapper;
import ma.boot.springboot.service.mapper.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSaver {
    private static final int MIN_WORD_LENGTH = 2;
    private static final String WORDS_SPLITERATOR = "[!._,'@? ]";
    private static final String DEFAULT_PASSWORD = "1111";
    private final RoleService roleService;
    private final UserService userService;
    private final UserMapper userMapper;
    private final ProductMapper productMapper;
    private final ReviewMapper reviewMapper;
    private final ProductService productService;
    private final ReviewService reviewService;
    private final WordService wordService;
    private final PasswordEncoder encoder;

    public DatabaseSaver(RoleService roleService, UserService userService,
                         UserMapper userMapper, ProductMapper productMapper,
                         ReviewMapper reviewMapper, ProductService productService,
                         ReviewService reviewService, WordService wordService,
                         PasswordEncoder encoder) {
        this.roleService = roleService;
        this.userService = userService;
        this.userMapper = userMapper;
        this.productMapper = productMapper;
        this.reviewMapper = reviewMapper;
        this.productService = productService;
        this.reviewService = reviewService;
        this.wordService = wordService;
        this.encoder = encoder;
    }

    public int addDataToStorage(List<ReviewRequestDto> dtos) {
        List<Word> wordsLoaded = loadWordsToDb(dtos);
        List<Product> productsLoaded = loadProductsToDb(dtos);
        List<User> usersLoaded = loadUsersToDb(dtos);

        Set<Review> reviewList = new HashSet<>();
        for (ReviewRequestDto dto : dtos) {
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

    private List<User> loadUsersToDb(List<ReviewRequestDto> dtos) {
        Set<User> userSetToLoad = new HashSet<>();
        Role userRole = roleService.getByName(RoleName.USER);

        for (ReviewRequestDto dto : dtos) {
            User user = userMapper.mapDtoToUser(new UserRequestDto(dto.getUserId(),
                    dto.getProfileName()));
            user.setRoles(Set.of(userRole));
            user.setPassword(encoder.encode(DEFAULT_PASSWORD));
            userSetToLoad.add(user);
        }
        return userService.addAll(userSetToLoad);
    }

    private List<Product> loadProductsToDb(List<ReviewRequestDto> dtos) {
        Set<Product> productSetToLoad = new HashSet<>();
        for (ReviewRequestDto dto : dtos) {
            productSetToLoad.add(productMapper
                    .mapDtoToProduct(new ProductRequestDto(dto.getProductId())));
        }
        return productService.addAll(productSetToLoad);
    }

    private List<Word> loadWordsToDb(List<ReviewRequestDto> dtos) {
        List<Word> wordListToLoad = new ArrayList<>();
        Map<String, Long> wordMapFromDto = new HashMap<>();
        wordMapFromDto = dtos.stream()
                .map(m -> m.getText().toLowerCase())
                .flatMap(Pattern.compile(WORDS_SPLITERATOR)::splitAsStream)
                .filter(w -> w.length() > MIN_WORD_LENGTH)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        for (Map.Entry<String, Long> entry : wordMapFromDto.entrySet()) {
            wordListToLoad.add(new Word(entry.getKey(), entry.getValue()));
        }
        return wordService.addAll(wordListToLoad);
    }
}
