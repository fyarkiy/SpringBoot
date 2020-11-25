package ma.boot.springboot.service.impl;

import java.util.List;
import java.util.Set;
import ma.boot.springboot.model.ReviewDto;
import ma.boot.springboot.model.User;
import ma.boot.springboot.model.dto.UserResponseDto;
import ma.boot.springboot.repository.RoleRepository;
import ma.boot.springboot.repository.UserRepository;
import ma.boot.springboot.service.UserService;
import ma.boot.springboot.service.mapper.UserMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper,
                           RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    public User add(ReviewDto dto) {
        return userRepository.save(userMapper.mapReviewDtoToUser(dto));
    }

    @Override
    public List<User> addAll(Set<User> userSet) {
        return userRepository.saveAll(userSet);
    }

    @Override
    public User get(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public List<UserResponseDto> getTopUsers(int qty) {

        return userRepository.findMostActiveUsers(PageRequest.of(0, qty));
    }
}
