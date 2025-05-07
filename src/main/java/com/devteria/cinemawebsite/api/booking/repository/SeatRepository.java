package com.devteria.cinemawebsite.api.booking.repository;

import com.devteria.cinemawebsite.api.booking.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, String> {
    public Optional<Seat> findBySeatNumberAndRoomId(String seatNumber, String screenId);

    List<Seat> findByRoom_id(String id);
}
