package com.example.user.entity;

import com.example.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity extends BaseEntity {
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    @ElementCollection(fetch = EAGER)
    private List<String> roles = new ArrayList<>();
}
