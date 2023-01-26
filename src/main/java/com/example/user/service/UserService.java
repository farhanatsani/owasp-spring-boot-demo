package com.example.user.service;

import com.example.security.User;
import com.example.user.entity.UserEntity;
import com.example.user.repository.UserEntityRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserEntityRepository userEntityRepository;
    public UserService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }
    public Optional<User> findUserByEmail(String email) {
        return userEntityRepository.findUserByEmail(email).map(this::toUser);
    }
    private User toUser(UserEntity u) {
        return new User(
                u.getId(), u.getFirstName(), u.getLastName(),
                u.getPassword(), u.getEmail(), u.getRoles());
    }
}
