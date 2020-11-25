package ma.boot.springboot.service.mapper;

import ma.boot.springboot.model.User;
import ma.boot.springboot.model.dto.UserRequestDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapDtoToUser(UserRequestDto dto) {
        User user = new User();
        user.setProfileName(dto.getProfileName());
        user.setUserId(dto.getUserId());
        user.setPassword(dto.getPassword());
        return user;
    }
}
