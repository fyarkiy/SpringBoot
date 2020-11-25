package ma.boot.springboot.service;

import java.util.List;
import ma.boot.springboot.model.User;
import ma.boot.springboot.model.dto.UserResponseDto;

public interface UserService extends GenericService<User> {
    List<UserResponseDto> getTopUsers(int qty);
}
