package ma.boot.springboot.service;

import java.util.List;
import java.util.Set;

public interface GenericService<T, U> {
    T add(U dto);

    T get(Long id);

    List<T> getAll();

    List<T> addAll(Set<T> set);
}
