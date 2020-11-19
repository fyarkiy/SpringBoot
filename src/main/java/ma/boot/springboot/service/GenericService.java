package ma.boot.springboot.service;

import java.util.List;
import ma.boot.springboot.model.ReviewDto;

public interface GenericService<T> {
    T add(ReviewDto dto);

    List<T> addAll(List<ReviewDto> dtoList);

    T get(Long id);

    List<T> getAll();
}
