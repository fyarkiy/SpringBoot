package ma.boot.springboot.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import ma.boot.springboot.model.User;
import ma.boot.springboot.model.dto.UserRequestDto;
import ma.boot.springboot.model.dto.UserResponseDto;
import ma.boot.springboot.repository.UserRepository;
import ma.boot.springboot.service.UserService;
import ma.boot.springboot.service.mapper.UserMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper,
                           PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.encoder = encoder;
    }

    @Override
    public User add(UserRequestDto dto) {
        User user = userMapper.mapDtoToUser(dto);
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
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
    public List<UserResponseDto> findMostActiveUsers(int quantity) {
        return userRepository.findMostActiveUsers(PageRequest.of(0, quantity));
    }

    @Override
    public Optional<User> findUserByProfileName(String profileName) {
        return userRepository.findUserByProfileName(profileName);
    }
}
