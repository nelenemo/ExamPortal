package com.exam.examserver.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {
    private final JwtAuthenticationEntryPoint unauthorizedHandler;

    //we have created the filter, implemented the user Details Service
    //but we need to bind because the created filter is not yet used
    //so we need to use it so this class is made to bind it.

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;




    //at startup spring will look for a bean of SecurityFilterChain

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/generate-token","/user/create")      //white listing: no need for authentication , HttpMethod.OPTIONS.toString()
                .permitAll()
                .requestMatchers("/user/delete") // APIs accessible only to users with "ROLE_ADMIN"
                .hasAnyRole("NORMAL","ADMIN")
                .anyRequest()
                .authenticated()
                .and()
//                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
//                .and()
                .sessionManagement()//we are using  session management so that  every request could be authenticated
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
