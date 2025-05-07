package com.devteria.cinemawebsite.api.booking.repository;

import com.devteria.cinemawebsite.api.booking.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<Seat, String> {
}
