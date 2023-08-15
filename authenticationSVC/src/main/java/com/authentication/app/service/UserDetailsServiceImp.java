package com.authentication.app.service;

import com.authentication.app.entity.UserEntity;
import com.authentication.app.model.UserDetailsImp;
import com.authentication.app.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImp implements UserDetailsService {

    UserRepository userRepository;

    UserDetailsServiceImp(UserRepository userRepository){
        this.userRepository=userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user =userRepository.findByUsername(username).
                orElseThrow(()-> new UsernameNotFoundException("Username Not found"));

        return UserDetailsImp.builder()
                .username(user.getUsername())
                .id(user.getId())
                .roles(user.getRoles())
                .password(user.getPassword())
                .build();
    }
}
