package com.devteria.cinemawebsite.api.movie.mapper;


import com.devteria.cinemawebsite.api.movie.dto.response.MovieResponse;
import com.devteria.cinemawebsite.api.movie.entity.Movie;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieResponse toMovieResponse(Movie movie);
    List<MovieResponse> toMovieResponses(List<Movie> movies);
}
