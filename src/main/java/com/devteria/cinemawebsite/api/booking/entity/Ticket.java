package com.devteria.cinemawebsite.api.booking.entity;

import com.devteria.cinemawebsite.api.showtime.entity.Showtime;
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
@Table(name = "ticket", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"seat_id", "showtime_id"})
})
public class Ticket {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private Booking booking;

    @ManyToOne
    private Showtime showtime;

    @ManyToOne
    private Seat seat;

    private double price;
}
