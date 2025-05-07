package com.devteria.cinemawebsite.api.user.entity;

import com.devteria.cinemawebsite.api.role.entity.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String username;
    String password;
    String name;

    @Column(unique = true)
    String email;

    @Column(unique = true)
    String phone;
    LocalDate dob;
    @ManyToMany
    Set<Role> roles;
}
