package ma.boot.springboot.security;

import ma.boot.springboot.model.User;
import ma.boot.springboot.model.dto.UserRequestDto;

public interface AuthenticationService {
    User signIn(UserRequestDto dto);
}
