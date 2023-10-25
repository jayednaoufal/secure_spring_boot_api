package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.dao.UserRepositoryCustom;
import com.luv2code.springboot.cruddemo.service.UserService;

@Configuration
public class DemoSecurityConfig {
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
    //authenticationProvider bean definition
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserRepositoryCustom userRepositoryCustom) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userRepositoryCustom); //set the custom user details service
        auth.setPasswordEncoder(bCryptPasswordEncoder()); //set the password encoder - bcrypt
        return auth;
    }
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(configurer -> 
				configurer
						  .requestMatchers(HttpMethod.GET, "/api/users").hasRole("ADMIN")
						  .requestMatchers(HttpMethod.GET, "/api/users/**").hasRole("ADMIN")
						  .requestMatchers(HttpMethod.POST, "/api/users").hasRole("ADMIN")
						  .requestMatchers(HttpMethod.PUT, "/api/users/**").hasRole("ADMIN")
						  .requestMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("ADMIN")
				);
		
		http.httpBasic();
		
		http.csrf().disable();
		
		return http.build();
	}
	
	
	
	
}
