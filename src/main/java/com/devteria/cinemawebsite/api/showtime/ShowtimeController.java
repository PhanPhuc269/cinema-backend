package com.devteria.cinemawebsite.api.showtime;


import com.devteria.cinemawebsite.api.showtime.dto.response.ShowtimeResponse;
import com.devteria.cinemawebsite.api.showtime.mapper.ShowtimeMapper;
import com.devteria.cinemawebsite.api.showtime.service.ShowtimeService;
import com.devteria.cinemawebsite.base.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/showtime")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ShowtimeController {
    ShowtimeService showtimeService;
    ShowtimeMapper showtimeMapper;

    @GetMapping
    public ApiResponse<List<ShowtimeResponse>> getShowtimesByMovieAndTheater(@RequestParam Long cinemaId, @RequestParam Long movieId){
        List<ShowtimeResponse> result = showtimeMapper.toShowtimeResponses(showtimeService.getShowtimesByMovieAndTheater(cinemaId, movieId));
        return ApiResponse.<List<ShowtimeResponse>>builder()
                .result(result)
                .build();
    }



    @GetMapping("/cinema/{cinemaId}")
    public ApiResponse<List<ShowtimeResponse>> getShowtimesByCinemaId(@PathVariable Long cinemaId){
        List<ShowtimeResponse> result = showtimeMapper.toShowtimeResponses(showtimeService.getShowtimesByCinemaId(cinemaId));
        return ApiResponse.<List<ShowtimeResponse>>builder()
                .result(result)
                .build();
    }

    @GetMapping("/movie/{movieId}")
    public ApiResponse<List<ShowtimeResponse>> getShowtimesByMovieId(@PathVariable Long movieId){
        List<ShowtimeResponse> result = showtimeMapper.toShowtimeResponses(showtimeService.getShowtimesByMovieId(movieId));
        return ApiResponse.<List<ShowtimeResponse>>builder()
                .result(result)
                .build();
    }

    @GetMapping("/{showtimeId}")
    public ApiResponse<ShowtimeResponse> getShowtimeById(@PathVariable String showtimeId){
        ShowtimeResponse result = showtimeMapper.toShowtimeResponse(showtimeService.getShowtimeById(showtimeId));
        return ApiResponse.<ShowtimeResponse>builder()
                .result(result)
                .build();
    }
    @GetMapping("/date/{date}")
    public ApiResponse<List<ShowtimeResponse>> getShowtimesByDate(@PathVariable LocalDate date){
        List<ShowtimeResponse> result = showtimeMapper.toShowtimeResponses(showtimeService.getShowtimesByDate(date));
        return ApiResponse.<List<ShowtimeResponse>>builder()
                .result(result)
                .build();
    }

}
