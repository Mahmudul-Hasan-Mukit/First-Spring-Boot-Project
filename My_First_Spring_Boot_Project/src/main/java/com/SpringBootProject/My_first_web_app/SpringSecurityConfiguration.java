package com.SpringBootProject.My_first_web_app;
import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager createInMemoryUserDetailsManager() {
        UserDetails userDetails1 = createNewUser("Mukit", "123");
        UserDetails userDetails2 = createNewUser("Moli", "456");
        return new InMemoryUserDetailsManager(userDetails1, userDetails2);
    }

    private UserDetails createNewUser(String username, String rawPassword) {
        String encodedPassword = passwordEncoder().encode(rawPassword);
        return User.withUsername(username)
                .password(encodedPassword)
                .roles("USER", "ADMIN")
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChai(HttpSecurity http) throws Exception {
    	http.authorizeHttpRequests(auth->auth.anyRequest().authenticated());
    	http.formLogin(withDefaults());
    	http.csrf().disable();
    	http.headers().frameOptions().disable();
    	return http.build();
    			
    	
    }
}
