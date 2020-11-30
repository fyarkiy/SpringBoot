package ma.boot.springboot.security;

import ma.boot.springboot.model.User;
import ma.boot.springboot.service.UserService;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserService userService;

    public CustomUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String profileName) {
        User user = userService.findUserByProfileName(profileName)
                .orElseThrow(() -> new UsernameNotFoundException("User with profile name "
                        + profileName + "not found"));
        UserBuilder builder = org.springframework.security.core
                .userdetails.User.withUsername(profileName);
        builder.password(user.getPassword());
        builder.roles(user.getRoles().stream()
                .map(r -> r.getRoleName().toString())
                .toArray(String[]::new));
        return builder.build();
    }
}
