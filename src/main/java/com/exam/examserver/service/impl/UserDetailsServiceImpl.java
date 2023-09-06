package com.exam.examserver.service.impl;

import com.exam.examserver.entity.User;
import com.exam.examserver.repo.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
//    private final UserDetails userDetails;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);

        if(user==null){

            System.out.println("user with "+user.getId()+"Id not found");
            throw new UsernameNotFoundException("No user found with"+user.getId()+" Id");
        }

return user;
//        return new User(user.getUsername(),user.getPassword(),new ArrayList<>());
    }
}
