package com.paynet.quiz.demo.security;

import com.paynet.quiz.demo.service.ToDoUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;

public class ToDoAuthenticationManager implements AuthenticationManager {
    private ToDoUserDetailsService toDoUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal() + "";
        String password = authentication.getCredentials() + "";

        UserDetails user = this.toDoUserDetailsService.loadUserByUsername(username);
        if(user == null || !user.getPassword().equals(password)) {
            throw new BadCredentialsException("Invalid username/password");
        }
        return new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());
    }

    @Autowired
    public void setToDoUserDetailsService(ToDoUserDetailsService toDoUserDetailsService) {
        this.toDoUserDetailsService = toDoUserDetailsService;
    }
}
