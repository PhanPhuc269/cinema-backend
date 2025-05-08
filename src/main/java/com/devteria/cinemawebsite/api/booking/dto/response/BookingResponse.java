package com.devteria.cinemawebsite.api.booking.dto.response;

import com.devteria.cinemawebsite.api.showtime.dto.response.ShowtimeResponse;
import com.devteria.cinemawebsite.api.user.dto.response.UserResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingResponse {
    private String id;
    private UserResponse user;
    private ShowtimeResponse showtime;
    private List<TicketResponse> tickets;
    private List<OrderItemResponse> orderItems;
    private LocalDateTime bookingTime;
    private double totalAmount;
    private String status;
    private String promotionCode;
    private String note;
    String bookingCode;
}
