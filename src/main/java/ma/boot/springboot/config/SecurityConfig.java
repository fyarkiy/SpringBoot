package ma.boot.springboot.config;

import ma.boot.springboot.security.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomUserDetailService customUserDetailService;
    private final PasswordEncoder encoder;

    public SecurityConfig(CustomUserDetailService customUserDetailService,
                          PasswordEncoder encoder) {
        this.customUserDetailService = customUserDetailService;
        this.encoder = encoder;
    }

    @Autowired
    public void confgureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserDetailService)
                .passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .and()
                .authorizeRequests()
                .antMatchers("/start/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/products/**",
                        "/words/**", "/users/**").hasRole("ADMIN")
                .antMatchers("/login/**", "/logout/**").permitAll()
                .antMatchers("/registration/**").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}
