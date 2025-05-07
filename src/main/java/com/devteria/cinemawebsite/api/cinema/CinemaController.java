package com.devteria.cinemawebsite.api.cinema;

import com.devteria.cinemawebsite.api.cinema.dto.response.CinemaResponse;
import com.devteria.cinemawebsite.api.cinema.dto.response.RoomResponse;
import com.devteria.cinemawebsite.api.cinema.entity.Room;
import com.devteria.cinemawebsite.api.cinema.service.CinemaService;
import com.devteria.cinemawebsite.api.cinema.service.RoomService;
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
@RequestMapping("/cinema")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CinemaController {
    CinemaService cinemaService;
    RoomService roomService;

    @GetMapping
    ApiResponse<List<CinemaResponse>> getAllCinemas() {
        var result = cinemaService.getAllCinemas();
        return ApiResponse.<List<CinemaResponse>>builder()
                .result(result)
                .build();
    }

    @GetMapping("/{cinemaId}/room/{roomId}")
    ApiResponse<Room> getRoomById(@PathVariable Long cinemaId, @PathVariable String roomId) {
        var result = roomService.getRoomById(roomId);
        return ApiResponse.<Room>builder()
                .result(result)
                .build();
    }

    @GetMapping("/{cinemaId}/room")
    ApiResponse<List<RoomResponse>> getAllRoomsByCinemaId(@PathVariable Long cinemaId) {
        var result = roomService.getRoomByCinemaId(cinemaId);
        return ApiResponse.<List<RoomResponse>>builder()
                .result(result)
                .build();
    }

    @GetMapping("/{cinemaId}")
    ApiResponse<CinemaResponse> getCinemaById(@PathVariable String cinemaId) {
        var result = cinemaService.getCinemaById(cinemaId);
        return ApiResponse.<CinemaResponse>builder()
                .result(result)
                .build();
    }

    @GetMapping("/city/{cityName}")
    ApiResponse<List<CinemaResponse>> getCinemasByCity(@PathVariable String cityName) {
        var result = cinemaService.getCinemasByCity(cityName);
        return ApiResponse.<List<CinemaResponse>>builder()
                .result(result)
                .build();
    }
}
