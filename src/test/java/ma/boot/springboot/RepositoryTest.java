package ma.boot.springboot;

import java.io.IOException;
import java.util.List;
import ma.boot.springboot.model.ReviewDto;
import ma.boot.springboot.model.User;
import ma.boot.springboot.repository.UserRepository;
import ma.boot.springboot.service.UserService;
import ma.boot.springboot.service.mapper.UserMapper;
import ma.boot.springboot.service.read.ReadingCsvFile;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.stereotype.Component;

@Component
public class RepositoryTest {
    private static final String CORRECT_CSV_FILE = "three_records.csv";
    private static final User USER_ONE = new User();
    private static List<ReviewDto> reviewList;
    private static UserMapper userMapper;
    private static UserService userService;
    private static UserRepository userRepository;
    @Autowired
    private JpaRepositoriesAutoConfiguration jpaRepositoriesAutoConfiguration;

    @BeforeClass
    public static void init() throws IOException {
        reviewList = ReadingCsvFile.readWithCsvBeanReader(CORRECT_CSV_FILE);
    }

    @Test
    public void saveAllUsersOk() {
        List<User> usersToAdd = userService.addAll(reviewList);
        List<User> usersAdded = userService.getAll();
        Assert.assertEquals(usersToAdd, usersAdded);
    }
}
