package com.exam.examserver.service.impl;

import com.exam.examserver.auth.AuthenticationResponse;
import com.exam.examserver.configuration.JwtService;
import com.exam.examserver.entity.User;
import com.exam.examserver.entity.UserRole;
import com.exam.examserver.repo.RoleRepository;
import com.exam.examserver.repo.UserRepository;
import com.exam.examserver.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private  final RoleRepository roleRepository;
    private final JwtService jwtService;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.jwtService = jwtService;
    }
//creating user
    @Transactional
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
       User local= this.userRepository.findByUsername(user.getUsername());
       if(local!=null){
           System.out.println("there is already a name with this username");
           throw new Exception("user already exist");
       }else{
           for(UserRole ur:userRoles){
               roleRepository.save(ur.getRole());
           }

           user.getUserRoles().addAll(userRoles);
           local = this.userRepository.save(user);
           var jwtToken=jwtService.generateToken(user);
           AuthenticationResponse.builder()
                   .token(jwtToken)
                   .build();
           System.out.println(jwtToken);

       }
        return local;
    }

    @Override
    public User getUsername(String username) {

        return  this.userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long id) {
        this.userRepository.deleteById(id);
    }



}
