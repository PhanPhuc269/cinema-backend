package com.devteria.cinemawebsite.api.booking.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketResponse {
    private String id;
    private SeatResponse seat;
    private double price;
}
