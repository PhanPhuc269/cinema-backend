package com.devteria.cinemawebsite.api.movie.service;

import com.devteria.cinemawebsite.api.movie.dto.response.MovieResponse;
import com.devteria.cinemawebsite.api.movie.entity.Movie;
import com.devteria.cinemawebsite.api.movie.mapper.MovieMapper;
import com.devteria.cinemawebsite.api.movie.repository.MovieRepository;
import com.devteria.cinemawebsite.exception.AppException;
import com.devteria.cinemawebsite.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MovieService {
    MovieRepository movieRepository;
    MovieMapper movieMapper;

    public MovieResponse getMovie(String movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new AppException(ErrorCode.MOVIE_NOT_EXISTED));
        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .genre(movie.getGenre())
                .language(movie.getLanguage())
                .director(movie.getDirector())
                .duration(movie.getDuration())
                .releaseDate(movie.getReleaseDate())
                .actors(movie.getActors())
                .posterUrl(movie.getPosterUrl())
                .trailerUrl(movie.getTrailerUrl())
                .build();
    }

    public List<MovieResponse> getAllMovie ()
    {
        List<Movie> movies = movieRepository.findAll();
        return movieMapper.toMovieResponses(movies);
    }
}
