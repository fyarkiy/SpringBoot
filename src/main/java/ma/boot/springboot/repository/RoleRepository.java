package ma.boot.springboot.repository;

import ma.boot.springboot.model.Role;
import ma.boot.springboot.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getRoleByRoleName(RoleName name);
}
