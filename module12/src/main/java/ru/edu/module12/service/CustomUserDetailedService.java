package ru.edu.module12.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.edu.module12.model.entity.UserInfo;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class CustomUserDetailedService implements UserDetailsService {

    private UserService userService;

    public CustomUserDetailedService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userService.getUserByLogin(username);
        Collection<GrantedAuthority> authorities = new ArrayList<>();


        return new User(userInfo.getLogin(), userInfo.getPassword(), authorities);
    }
}
