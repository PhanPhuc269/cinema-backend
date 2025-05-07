package com.devteria.cinemawebsite.api.movie.repository;

import com.devteria.cinemawebsite.api.movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
}
