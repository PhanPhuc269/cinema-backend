package com.devteria.cinemawebsite.api.role.repository;

import com.devteria.cinemawebsite.api.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
}
