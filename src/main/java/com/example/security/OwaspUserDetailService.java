package com.example.security;

import com.example.user.service.impl.UserServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OwaspUserDetailService implements UserDetailsService {
    private UserServiceImpl userService;
    public OwaspUserDetailService(UserServiceImpl userService) {
        this.userService = userService;
    }
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.findUserDetailByEmail(username)
                .orElseThrow(()
                        -> new UsernameNotFoundException("Username not found for '" + username + "'"));
    }
}
