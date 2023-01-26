package com.example.security;

import com.example.user.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OwaspUserDetailService implements UserDetailsService {
    private UserService userService;
    public OwaspUserDetailService(UserService userService) {
        this.userService = userService;
    }
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.findUserByEmail(username)
                .orElseThrow(()
                        -> new UsernameNotFoundException("Username not found for '" + username + "'"));
    }
}
