package ma.boot.springboot;

import ma.boot.springboot.service.UserService;
import ma.boot.springboot.service.impl.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }
}
