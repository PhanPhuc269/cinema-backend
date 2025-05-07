package com.devteria.cinemawebsite.api.booking.dto.response;

import com.devteria.cinemawebsite.enums.SeatType;
import com.devteria.cinemawebsite.enums.StatusSeat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeatResponse {
    Long id;
    String name;

    char seatRow;
    int seatColumn;
    boolean isPresent;

    @Enumerated(EnumType.STRING)
    SeatType type;

    StatusSeat status;
}
