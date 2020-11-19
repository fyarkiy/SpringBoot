package ma.boot.springboot.service;

import ma.boot.springboot.model.ReviewDto;
import ma.boot.springboot.model.User;

import java.util.List;

public interface UserService {
    User add(ReviewDto dto);

    List<User> addAll(List<ReviewDto> dtoList);

    User get(Long id);

    List<User> getAll();
}
