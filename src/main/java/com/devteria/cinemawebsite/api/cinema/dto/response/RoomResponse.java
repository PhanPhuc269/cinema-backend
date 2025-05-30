package com.devteria.cinemawebsite.api.cinema.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomResponse {
    private String id;
    private String name;
    private CinemaResponse cinema;
}
