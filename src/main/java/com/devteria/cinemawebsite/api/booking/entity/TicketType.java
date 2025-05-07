package com.devteria.cinemawebsite.api.booking.entity;

import com.devteria.cinemawebsite.enums.TicketTypeEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class TicketType {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String name;
    String description;
    double price;

    @Enumerated(EnumType.STRING)
    TicketTypeEnum type;

}
