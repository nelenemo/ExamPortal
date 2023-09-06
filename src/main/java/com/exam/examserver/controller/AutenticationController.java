//package com.exam.examserver.controller;
//
//import com.exam.examserver.configuration.JwtService;
//import com.exam.examserver.service.impl.UserDetailsServiceImpl;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class AutenticationController {
//    private final AuthenticationManager authenticationManager;
//    private final UserDetailsServiceImpl userDetailsSericeImpl;
//    private final JwtService jwtService;
//
//    public AutenticationController(AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsSericeImpl, JwtService jwtService) {
//        this.authenticationManager = authenticationManager;
//        this.userDetailsSericeImpl = userDetailsSericeImpl;
//        this.jwtService = jwtService;
//    }
//
//
//    private void authenticate(String username, String password) throws Exception {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        } catch (DisabledException e) {
//            throw new Exception("USER DISABLE");
//        } catch (BadCredentialsException e) {
//            throw new Exception("INVALID CREDENTIALS" + e.getMessage());
//
//        }
//    }
//}
