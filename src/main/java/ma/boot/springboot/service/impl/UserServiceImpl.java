package ma.boot.springboot.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import ma.boot.springboot.model.ReviewDto;
import ma.boot.springboot.model.Role;
import ma.boot.springboot.model.RoleName;
import ma.boot.springboot.model.User;
import ma.boot.springboot.repository.UserRepository;
import ma.boot.springboot.service.UserService;
import ma.boot.springboot.service.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private static final String PASSWORD = "1111";
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User add(ReviewDto dto) {
        return userRepository.save(userMapper.mapReviewDtoToUser(dto));
    }

    @Override
    public List<User> addAll(List<ReviewDto> dtoList) {
        List<User> users = new ArrayList<>();
        for (ReviewDto dto : dtoList) {
            User user = userMapper.mapReviewDtoToUser(dto);
            user.setRoles(Set.of(new Role(RoleName.USER)));
            user.setPassword(PASSWORD);
            users.add(user);
        }
        userRepository.saveAll(users);
        return users;
    }

    @Override
    public User get(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
