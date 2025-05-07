package com.devteria.cinemawebsite.api.booking.entity;

import com.devteria.cinemawebsite.enums.Category;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import static java.lang.Boolean.TRUE;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Combo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String description;
    double price;
    boolean available = TRUE;
    String image;

    @Enumerated(EnumType.STRING)
    Category category;

}
