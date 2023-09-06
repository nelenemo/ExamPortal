package com.exam.examserver.auth;

import com.exam.examserver.configuration.JwtService;
import com.exam.examserver.entity.User;
import com.exam.examserver.repo.UserRepository;
//import com.example.springsecurity_jwt.config.JwtService;
//import com.example.springsecurity_jwt.enums.Role;
//import com.example.springsecurity_jwt.model.User;
//import com.example.springsecurity_jwt.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(User request) {
        var user= User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .userRoles(new HashSet<>())
                .build();
        userRepo.save(user);
        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()
                )
        );
        var user=userRepo.findByUsername(request.getUsername());//may be changed here
        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
