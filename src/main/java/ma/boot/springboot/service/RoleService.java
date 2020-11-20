package ma.boot.springboot.service;

import java.util.List;
import ma.boot.springboot.model.Role;
import ma.boot.springboot.model.RoleName;

public interface RoleService {
    List<Role> addAll(List<Role> roles);

    Role getByName(RoleName name);
}
