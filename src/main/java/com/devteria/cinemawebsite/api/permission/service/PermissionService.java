package com.devteria.cinemawebsite.api.permission.service;

import com.devteria.cinemawebsite.api.permission.dto.request.PermissionRequest;
import com.devteria.cinemawebsite.api.permission.dto.response.PermissionResponse;
import com.devteria.cinemawebsite.api.permission.entity.Permission;
import com.devteria.cinemawebsite.api.permission.mapper.PermissionMapper;
import com.devteria.cinemawebsite.api.permission.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionResponse create(PermissionRequest request){
        Permission permission = permissionMapper.toPermission(request);
        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    public List<PermissionResponse> getAll(){
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    public void delete(String permission){
        permissionRepository.deleteById(permission);
    }
}
