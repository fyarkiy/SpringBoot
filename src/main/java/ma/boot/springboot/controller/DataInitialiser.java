package ma.boot.springboot.controller;

import java.util.List;
import javax.annotation.PostConstruct;
import ma.boot.springboot.model.Role;
import ma.boot.springboot.model.RoleName;
import ma.boot.springboot.service.RoleService;
import org.springframework.stereotype.Component;

@Component
public class DataInitialiser {
    private final RoleService roleService;

    public DataInitialiser(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostConstruct
    public void init() {
        addBasicRoles();
    }

    private void addBasicRoles() {
        roleService.addAll(List.of(new Role(RoleName.ADMIN), new Role(RoleName.USER)));
    }
}
