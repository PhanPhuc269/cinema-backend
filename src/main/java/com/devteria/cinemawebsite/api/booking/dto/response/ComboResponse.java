package com.devteria.cinemawebsite.api.booking.dto.response;

import com.devteria.cinemawebsite.enums.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ComboResponse {
    Long id;
    String name;
    String description;
    double price;
    String image;
    Category category;
}
