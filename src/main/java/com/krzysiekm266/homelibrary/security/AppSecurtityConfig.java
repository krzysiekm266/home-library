package com.krzysiekm266.homelibrary.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AppSecurtityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/", "index","/css/*","/js/*").permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic();
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.builder()
            .username("testuser")
            .password("password1")
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }
}
