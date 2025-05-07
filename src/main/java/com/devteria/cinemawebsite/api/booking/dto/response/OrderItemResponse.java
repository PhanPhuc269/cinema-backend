package com.devteria.cinemawebsite.api.booking.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemResponse {
    private Long id;
    private ComboResponse combo;
    private int quantity;
}
