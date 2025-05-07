package com.devteria.cinemawebsite.api.movie;

import com.devteria.cinemawebsite.api.movie.dto.response.MovieResponse;
import com.devteria.cinemawebsite.api.movie.service.MovieService;
import com.devteria.cinemawebsite.base.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MovieController {
    MovieService movieService;

    @GetMapping("/{movieId}")
    ApiResponse<MovieResponse> getMovie(@PathVariable String movieId) {
        var result = movieService.getMovie(movieId);
        return ApiResponse.<MovieResponse>builder()
                .result(result)
                .build();
    }

    @GetMapping("/all")
    ApiResponse<List<MovieResponse>> getAllMovies(){
        List<MovieResponse> result = movieService.getAllMovie();
        return ApiResponse.<List<MovieResponse>>builder()
                .result(result)
                .build();
    }
}
