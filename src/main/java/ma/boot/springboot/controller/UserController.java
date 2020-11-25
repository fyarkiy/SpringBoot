package ma.boot.springboot.controller;

import java.util.List;
import ma.boot.springboot.model.dto.UserResponseDto;
import ma.boot.springboot.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final String QUANTITY = "1000";
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/top")
    public List<UserResponseDto> findMostActiveUsers(@RequestParam(defaultValue = QUANTITY)
                                                                 int quantity) {
        return userService.findMostActiveUsers(quantity);
    }
}
