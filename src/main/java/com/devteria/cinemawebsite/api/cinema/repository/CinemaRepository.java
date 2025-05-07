package com.devteria.cinemawebsite.api.cinema.repository;

import com.devteria.cinemawebsite.api.cinema.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, String> {
    List<Cinema> findByCity(String cityName);
}
