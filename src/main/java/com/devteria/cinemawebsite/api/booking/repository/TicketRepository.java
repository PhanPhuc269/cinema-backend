package com.devteria.cinemawebsite.api.booking.repository;

import com.devteria.cinemawebsite.api.booking.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {
    boolean existsBySeatIdAndBooking_Showtime_Id(Long seatId, Long showtimeId);

    List<Ticket> findByShowtime_Id(Long showtimeId);
}
