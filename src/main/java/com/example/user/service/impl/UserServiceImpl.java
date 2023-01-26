package com.example.user.service.impl;

import com.example.base.constants.RspMsgConstants;
import com.example.security.User;
import com.example.user.dto.UserDto;
import com.example.user.entity.UserEntity;
import com.example.user.repository.UserEntityRepository;
import com.example.user.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserEntityRepository userEntityRepository;
    private final PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserEntityRepository userEntityRepository, PasswordEncoder passwordEncoder) {
        this.userEntityRepository = userEntityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        UserEntity userSave = userEntityRepository.saveAndFlush(toUserEntity(userDto));
        return toUserDto(userSave);
    }

    @PreAuthorize("isAuthenticated()")
    @Override
    public void changePassword(UUID id, String oldPassword, String newPassword) {
        Optional<UserEntity> user = userEntityRepository.findById(id);
        if(user.isEmpty()) {
            throw new IllegalArgumentException(RspMsgConstants.DATA_NOT_AVAILABLE);
        }
        user.map(userEntity -> {
           if(!passwordEncoder.matches(oldPassword, userEntity.getPassword())) {
               throw new IllegalArgumentException(RspMsgConstants.USER_PASSWORD_NOT_VALID);
           }
           userEntity.setPassword(passwordEncoder.encode(newPassword));
           userEntityRepository.save(userEntity);
           return userEntity;
        });
    }

    @Override
    public List<UserDto> findAll() {
        return userEntityRepository.findAll().stream().map(this::toUserDto).collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> findUserById(UUID id) {
        return userEntityRepository.findById(id).map(this::toUserDto);
    }

    public Optional<User> findUserDetailByEmail(String email) {
        return userEntityRepository.findUserByEmail(email).map(this::toUser);
    }
    private User toUser(UserEntity u) {
        return new User(
                u.getId(), u.getFirstName(), u.getLastName(),
                u.getPassword(), u.getEmail(), u.getRoles());
    }

    private UserDto toUserDto(UserEntity u) {
        return UserDto.builder()
                .id(u.getId())
                .firstName(u.getFirstName())
                .lastName(u.getLastName())
                .email(u.getEmail())
                .roles(u.getRoles())
                .build();
    }

    private UserEntity toUserEntity(UserDto dto) {
        return UserEntity.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .roles(dto.getRoles())
                .build();
    }
}
