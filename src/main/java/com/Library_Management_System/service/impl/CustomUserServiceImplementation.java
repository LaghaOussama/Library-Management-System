package com.Library_Management_System.service.impl;

import com.Library_Management_System.modal.User;
import com.Library_Management_System.repository.UserRespository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Service
public class CustomUserServiceImplementation implements UserDetailsService {

    private final UserRespository userRespository;

    public CustomUserServiceImplementation(UserRespository userRespository) {
        this.userRespository = userRespository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user=userRespository.findByEmail(username);

        if(user==null){
            throw new UsernameNotFoundException("user not exist with username "+username);
        }

        GrantedAuthority authority=new SimpleGrantedAuthority(user.getRole().toString());

        Collection<? extends GrantedAuthority> authorities=
                Collections.singletonList(authority);

        return new org.springframework.security.core.userdetails.User(user.getEmail()
                ,user.getPassword(),authorities);
    }
}
