package com.devteria.cinemawebsite.api.auth.repository;

import com.devteria.cinemawebsite.api.auth.entity.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {
}
