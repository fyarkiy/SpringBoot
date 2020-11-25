package ma.boot.springboot.service;

import java.util.List;
import java.util.Set;
import ma.boot.springboot.model.ReviewDto;

public interface GenericService<T> {
    T add(ReviewDto dto);

    T get(Long id);

    List<T> getAll();

    List<T> addAll(Set<T> set);
}
