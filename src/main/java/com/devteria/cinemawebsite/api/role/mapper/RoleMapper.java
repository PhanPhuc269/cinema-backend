package com.devteria.cinemawebsite.api.role.mapper;

import com.devteria.cinemawebsite.api.role.dto.request.RoleRequest;
import com.devteria.cinemawebsite.api.role.dto.response.RoleResponse;
import com.devteria.cinemawebsite.api.role.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
