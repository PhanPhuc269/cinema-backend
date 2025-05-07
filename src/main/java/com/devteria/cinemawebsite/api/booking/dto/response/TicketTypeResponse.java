package com.devteria.cinemawebsite.api.booking.dto.response;

import com.devteria.cinemawebsite.enums.TicketTypeEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketTypeResponse {
    private Long id;
    String name;
    String description;
    double price;

    @Enumerated(EnumType.STRING)
    TicketTypeEnum type;
}
