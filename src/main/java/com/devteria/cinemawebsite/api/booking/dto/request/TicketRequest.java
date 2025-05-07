package com.devteria.cinemawebsite.api.booking.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketRequest {
    private String seatNumber;
    private double price;
    private String type;
}
