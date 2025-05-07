package com.devteria.cinemawebsite.api.showtime.service;

import com.devteria.cinemawebsite.api.showtime.entity.Showtime;
import com.devteria.cinemawebsite.api.showtime.repository.ShowtimeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShowtimeService {
    ShowtimeRepository showtimeRepository;

    public List<Showtime> getShowtimesByCinemaId(Long cinemaId) {
        return showtimeRepository.findByRoom_Cinema_id(cinemaId);
    }

    public  List<Showtime> getShowtimesByMovieId(Long movieId) {
        return showtimeRepository.findByMovie_id(movieId);
    }

    public List<Showtime> getShowtimesByMovieAndTheater(Long cinemaId, Long movieId) {
        return showtimeRepository.findByRoom_Cinema_idAndMovie_id(cinemaId, movieId);
    }

    public Showtime getShowtimeById(String showtimeId) {
        return showtimeRepository.findById(showtimeId)
                .orElseThrow(() -> new RuntimeException("Showtime not found"));
    }

    public List<Showtime> getShowtimesByDate(LocalDate date) {
        return showtimeRepository.findByDate(date);
    }

}
