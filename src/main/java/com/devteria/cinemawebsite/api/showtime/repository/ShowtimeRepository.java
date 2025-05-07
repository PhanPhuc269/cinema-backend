package com.devteria.cinemawebsite.api.showtime.repository;

import com.devteria.cinemawebsite.api.showtime.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, String> {
    List<Showtime> findByRoom_Cinema_id(Long cinemaId);

    List<Showtime> findByMovie_id(Long movieId);

    List<Showtime> findByRoom_Cinema_idAndMovie_id(Long cinemaId, Long movieId);

    List<Showtime> findByDate(LocalDate date);

    Optional<Showtime> findById(Long showtimeId);
}
