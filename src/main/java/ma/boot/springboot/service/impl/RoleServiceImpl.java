package ma.boot.springboot.service.impl;

import java.util.List;
import ma.boot.springboot.model.Role;
import ma.boot.springboot.model.RoleName;
import ma.boot.springboot.repository.RoleRepository;
import ma.boot.springboot.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> addAll(List<Role> roles) {
        return roleRepository.saveAll(roles);
    }

    @Override
    public Role getByName(RoleName name) {
        return roleRepository.getRoleByRoleName(name);
    }
}
