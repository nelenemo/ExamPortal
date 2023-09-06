package com.exam.examserver.controller;

import com.exam.examserver.entity.Role;
import com.exam.examserver.entity.User;
import com.exam.examserver.entity.UserRole;
import com.exam.examserver.service.UserService;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    //create user


    @GetMapping("/getUsername/{username}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User getUsername(@PathVariable ("username") String username){
        return this.userService.getUsername(username);//
    }

    @DeleteMapping("/delete/{id}")
//    @PreAuthorize("hasRole('NORMAL')")
    public String deleteUser(@PathVariable ("id") Long id){
        this.userService.deleteUser(id);
        return "user has been deleted!!!";
    }
}
