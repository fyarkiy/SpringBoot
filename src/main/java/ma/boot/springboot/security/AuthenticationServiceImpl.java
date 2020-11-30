package ma.boot.springboot.security;

import java.util.Set;
import ma.boot.springboot.model.RoleName;
import ma.boot.springboot.model.User;
import ma.boot.springboot.model.dto.UserRequestDto;
import ma.boot.springboot.repository.UserRepository;
import ma.boot.springboot.service.RoleService;
import ma.boot.springboot.service.mapper.UserMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Logger logger = Logger.getLogger(AuthenticationServiceImpl.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleService roleService;

    public AuthenticationServiceImpl(UserRepository userRepository,
                                     UserMapper userMapper, RoleService roleService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleService = roleService;
    }

    @Override
    public User signIn(UserRequestDto dto) {
        User user = userMapper.mapDtoToUser(dto);
        user.setRoles((Set.of(roleService.getByName(RoleName.USER))));
        return userRepository.save(user);
    }
}
