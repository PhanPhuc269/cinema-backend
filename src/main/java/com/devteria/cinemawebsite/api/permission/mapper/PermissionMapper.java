package com.devteria.cinemawebsite.api.permission.mapper;

import com.devteria.cinemawebsite.api.permission.dto.request.PermissionRequest;
import com.devteria.cinemawebsite.api.permission.dto.response.PermissionResponse;
import com.devteria.cinemawebsite.api.permission.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}
