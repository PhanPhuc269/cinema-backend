package com.devteria.cinemawebsite.api.cinema.entity;

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
public class Room {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private int totalSeats;

    @ManyToOne
    private Cinema cinema;
}
