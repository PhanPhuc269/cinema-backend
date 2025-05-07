package com.devteria.cinemawebsite.api.user.dto.response;

import com.devteria.cinemawebsite.api.role.entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String username;
    String name;
    String email;
    String phone;
    LocalDate dob;
    Set<Role> roles;
}
