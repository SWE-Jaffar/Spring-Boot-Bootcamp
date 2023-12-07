package com.ltp.contacts.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
         
        http
         .authorizeRequests()
         .anyRequest().authenticated()
         .and()
         .httpBasic()
         .and()
         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

         return http.build();
    }

    @Bean
    public UserDetailsService users() {
       UserDetails admin = User.builder()
       .roles("ADMIN")
       .username("admin")
       .password(bCryptPasswordEncoder.encode("admin"))
       .build();

        UserDetails user = User.builder()
        .roles("USER")
       .username("user")
       .password(bCryptPasswordEncoder.encode("user"))
       .build();

       return new InMemoryUserDetailsManager(admin,user);
    }

}
