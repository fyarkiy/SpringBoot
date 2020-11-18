package ma.boot.springboot.service.mapper;

import java.util.Set;
import ma.boot.springboot.model.ReviewDto;
import ma.boot.springboot.model.Role;
import ma.boot.springboot.model.RoleName;
import ma.boot.springboot.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static User mapReviewDtoToUser(ReviewDto dto) {
        User user = new User();
        user.setProfileName(dto.getProfileName());
        user.setUserId(dto.getUserId());
        user.setRoles(Set.of(new Role(RoleName.USER)));
        return user;
    }
}
