package com.exam.examserver;

import com.exam.examserver.entity.Role;
import com.exam.examserver.entity.User;
import com.exam.examserver.entity.UserRole;
import com.exam.examserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableCaching
public class ExamServerApplication implements CommandLineRunner {

    @Autowired
    UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public static void main(String[] args) {
        SpringApplication.run(ExamServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        System.out.println("Project is running");
//
//        User user=new User();
//
//        user.setFirstname("nilima");
//        user.setLastname("Maka");
//        user.setEmail("nilima@gmail.com");
//        user.setPassword("nilima");
//        user.setUsername("nilimamaka");
//        user.setProfile("default.png");
//        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
//
//        Role role1=new Role();
//        role1.setRoleId(1L);
//        role1.setRoleName("ADMIN");
//
//        Set<UserRole> userRoleSet=new HashSet<>();
//
//        UserRole userRole = new UserRole();
//
//        userRole.setRole(role1);
//        userRole.setUser(user);
//        userRoleSet.add(userRole);
//
//        User user1 = this.userService.createUser(user, userRoleSet);
//        System.out.println(user1.getUsername());

    }
}
