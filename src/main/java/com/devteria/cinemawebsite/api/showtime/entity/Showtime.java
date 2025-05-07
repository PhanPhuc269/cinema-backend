package com.devteria.cinemawebsite.api.showtime.entity;


import com.devteria.cinemawebsite.api.cinema.entity.Room;
import com.devteria.cinemawebsite.api.movie.entity.Movie;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Showtime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalTime startTime;
    private LocalDate date;

    @ManyToOne
    private Movie movie;
    @ManyToOne
    private Room room;
    private double price;
}
