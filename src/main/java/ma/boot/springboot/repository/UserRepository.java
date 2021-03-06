package ma.boot.springboot.repository;

import java.util.List;
import java.util.Optional;
import ma.boot.springboot.model.User;
import ma.boot.springboot.model.dto.UserResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT "
            + "new ma.boot.springboot.model.dto.UserResponseDto(u.profileName) "
            + " from Review r, User u "
            + "where r.user = u.id GROUP BY r.user "
            + "ORDER BY COUNT(r.user) DESC, u.profileName ASC")
    List<UserResponseDto> findMostActiveUsers(Pageable pageable);

    Optional<User> findUserByProfileName(@RequestParam String profileName);
}
