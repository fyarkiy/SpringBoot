package ma.boot.springboot.controller;

import ma.boot.springboot.model.dto.UserRequestDto;
import ma.boot.springboot.security.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/registration")
    public void signIn(@RequestBody UserRequestDto userRequestDto) {
        authenticationService.signIn(userRequestDto);
    }
}
