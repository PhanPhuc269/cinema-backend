package com.devteria.cinemawebsite.api.booking.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CinemaResponse {
    private Long id;
    private String name;
    private String address;
    private String city;
    private String phone;
    private String imageUrl;
}
