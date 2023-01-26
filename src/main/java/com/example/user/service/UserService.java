package com.example.user.service;

import com.example.security.User;
import com.example.user.dto.UserDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    UserDto saveUser(UserDto userDto);
    void changePassword(UUID id, String oldPassword, String newPassword);
    List<UserDto> findAll();
    Optional<UserDto> findUserById(UUID id);
    Optional<User> findUserDetailByEmail(String email);
}
