package com.example.user.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private List<String> roles = new ArrayList<>();
}
