package com.odazie.imagerepository.business.service;

import com.odazie.imagerepository.data.entity.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service(value = "userServiceImplementation")
public class UserDetailsServiceImplementation implements UserDetailsService {
    private final UserService userService;


    public UserDetailsServiceImplementation(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findUserByEmail(email);

        if (user == null){
            throw  new UsernameNotFoundException(email);
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(), getAuthority(user) );
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        return new HashSet<>();
    }
}

