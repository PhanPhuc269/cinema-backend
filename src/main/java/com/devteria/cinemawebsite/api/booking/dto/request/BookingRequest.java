package com.devteria.cinemawebsite.api.booking.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingRequest {
    private String showtimeId;

    private List<TicketRequest> tickets;
    private List<OrderItemRequest> orderItems;

    private String promotionCode;
    private String note;
    private String paymentMethod;
}
