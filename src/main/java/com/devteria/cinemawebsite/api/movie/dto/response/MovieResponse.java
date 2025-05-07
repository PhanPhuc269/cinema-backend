package com.devteria.cinemawebsite.api.movie.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieResponse {
    private Long id;
    private String title;
    private String description;
    private String genre;
    private int duration;
    private String director;
    private String actors;
    private String language;
    private String trailerUrl;
    private String posterUrl;
    private LocalDate releaseDate;
}
