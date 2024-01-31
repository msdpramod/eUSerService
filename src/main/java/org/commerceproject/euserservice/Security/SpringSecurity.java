package org.commerceproject.euserservice.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurity {
    // SecurityFilterChain is a filter chain that is responsible for processing a single security filter.
    @Bean @Order(1) // @Order annotation is used to sort the filter chain.
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors().disable()
                .csrf().disable()
                .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Set the session creation policy
                .maximumSessions(1); // Set the maximum number of sessions
        //        http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/auth/*").permitAll());
//        http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/order/*").authenticated());

        return httpSecurity.build();
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
