package com.devteria.cinemawebsite.api.showtime.dto.response;

import com.devteria.cinemawebsite.api.cinema.dto.response.RoomResponse;
import com.devteria.cinemawebsite.api.movie.dto.response.MovieResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShowtimeResponse {
    private Long id;
    private LocalTime startTime;
    private LocalDate date;
    private MovieResponse movie;
    private RoomResponse room;
}
