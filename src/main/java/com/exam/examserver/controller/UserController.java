package com.exam.examserver.controller;

import com.exam.examserver.entity.Role;
import com.exam.examserver.entity.User;
import com.exam.examserver.entity.UserRole;
import com.exam.examserver.repo.UserRepository;
import com.exam.examserver.service.UserService;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);


//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    //create user
    @PostMapping("/create")
//    @Operation(
//            description = "Register User",
//            responses ={
//                    @ApiResponse(responseCode = "200", ref = "successfulResponseAPI"),
//                    @ApiResponse(responseCode = "400", ref = "badRequestAPI"),
//                    @ApiResponse(responseCode = "500", ref = "internalServerErrorAPI"),

//                    @ApiResponse(
//                            responseCode = "200",
//                            description = "Successfully user registered",
//                            content=@Content(
//                                    mediaType = "application/json",
//                                    examples = {
//                                            @ExampleObject(
//                                                  value=  "{\"username\": \"nilima\"}"
//
//                                            )
//                                    }
//                            )
//                    ),
//                    @ApiResponse(
//                            responseCode = "400",
//                            description = "Bad Request",
//                            content=@Content(
//                                    mediaType = "application/json"
//
//
//                            )
//                    ),



//            }
//    )
    @PermitAll
    public User createUser(
//            @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(
//                    mediaType = "application/json",
//                    examples = {
//                            @ExampleObject(
//                                    value = "{\"username\" : \"nilimamaka\", \"password\" : \"nilima\"}"
//                            ),
//                    }
//            ))
            @RequestBody User user) throws Exception {
        user.setProfile("defult.png");
        //encoding password with Bcryptpasswordencoder
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        Set<UserRole> roles=new HashSet<>();
        Role role=new Role();
//        role.setRoleId(2L);
        role.setRoleName("NORMAL");

        UserRole userRole=new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        roles.add(userRole);
logger.info("inside user controller calling");
        return this.userService.createUser(user,roles);


    }

    @GetMapping("/getUsername/{username}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User getUsername(@PathVariable ("username") String username){

        return this.userService.getUsername(username);

    }


    @GetMapping ("getAllUsers")
    public ResponseEntity<?> getalluser(){
        List<User> allUsers = userRepository.findAll();
        if (allUsers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        logger.info("The data of user" + allUsers);

        return ResponseEntity.ok(allUsers);
    }



    @DeleteMapping("/delete/{id}")
//    @PreAuthorize("hasRole('NORMAL')")
    public String deleteUser(@PathVariable ("id") Long id){
        this.userService.deleteUser(id);
        return "user has been deleted!!!";
    }
}
