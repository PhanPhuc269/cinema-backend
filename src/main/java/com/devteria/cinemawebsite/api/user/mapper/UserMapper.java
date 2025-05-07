package com.devteria.cinemawebsite.api.user.mapper;

import com.devteria.cinemawebsite.api.user.dto.request.UserCreationRequest;
import com.devteria.cinemawebsite.api.user.dto.request.UserUpdateRequest;
import com.devteria.cinemawebsite.api.user.dto.response.UserResponse;
import com.devteria.cinemawebsite.api.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "name", source = "name")
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
