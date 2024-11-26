package com.employeeTracking.OAuth2Demo.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.employeeTracking.OAuth2Demo.filter.JWTFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private JWTFilter jwtFilter;

    public SecurityConfig(JWTFilter jwtFilter){
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(5);
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                        .requestMatchers("/employee/register").permitAll()
                        .requestMatchers("/employee/signin").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/employee/").hasAnyRole( "ADMIN","MANAGER")
                        .requestMatchers("/employee/{id}").hasAnyRole("ADMIN", "MANAGER","EMPLOYEE")
                        .requestMatchers("/department/**").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers("/projects/**").hasAnyRole("ADMIN", "MANAGER")
                        .anyRequest().authenticated()
        ).addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
        .oauth2Login(Customizer.withDefaults());
        return http.build();
    }

    
}
