package ma.boot.springboot.service.mapper;

import ma.boot.springboot.model.ReviewDto;
import ma.boot.springboot.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapReviewDtoToUser(ReviewDto dto) {
        User user = new User();
        user.setProfileName(dto.getProfileName());
        user.setUserId(dto.getUserId());
        return user;
    }
}
