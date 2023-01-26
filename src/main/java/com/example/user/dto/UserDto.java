package com.example.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private List<String> roles;
}
