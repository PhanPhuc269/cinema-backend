package com.devteria.cinemawebsite.api.booking;

import com.devteria.cinemawebsite.api.booking.dto.request.BookingRequest;
import com.devteria.cinemawebsite.api.booking.dto.response.BookingResponse;
import com.devteria.cinemawebsite.api.booking.dto.response.SeatResponse;
import com.devteria.cinemawebsite.api.booking.dto.response.TicketTypeResponse;
import com.devteria.cinemawebsite.api.booking.service.BookingService;
import com.devteria.cinemawebsite.api.booking.service.TicketTypeService;
import com.devteria.cinemawebsite.base.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BookingController {
    BookingService bookingService;
    TicketTypeService  ticketTypeService;

    @PostMapping
    BookingResponse createBooking(@RequestBody BookingRequest bookingRequest) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = jwt.getClaimAsString("userId"); // nếu token dùng "sub" để chứa user ID

        return bookingService.createBooking(userId, bookingRequest);
    }

    @GetMapping("/ticket-type")
    ApiResponse<List<TicketTypeResponse>> getTicketTypes() {
        var result = ticketTypeService.getTicketTypes();
        return ApiResponse.<List<TicketTypeResponse>>builder()
                .result(result)
                .build();
    }

    @GetMapping("/seats/{showtimeId}")
    ApiResponse<List<SeatResponse>> getSeats(@PathVariable Long showtimeId) {
        var result = bookingService.getSeats(showtimeId);
        return ApiResponse.<List<SeatResponse>>builder()
                .result(result)
                .build();
    }

}
