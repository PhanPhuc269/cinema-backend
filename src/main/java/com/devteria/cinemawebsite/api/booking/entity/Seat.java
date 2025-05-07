package com.devteria.cinemawebsite.api.booking.entity;

import com.devteria.cinemawebsite.api.cinema.entity.Room;
import com.devteria.cinemawebsite.enums.SeatType;
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
public class Seat {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String seatNumber;

    char seatRow;
    int seatColumn;


    boolean isPresent = true;

    @Enumerated(EnumType.STRING)
    SeatType type = SeatType.NORMAL;

    @ManyToOne
    Room room;
}
