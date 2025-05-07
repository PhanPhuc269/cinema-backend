package com.devteria.cinemawebsite.api.booking.repository;

import com.devteria.cinemawebsite.api.booking.entity.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketTypeRepository extends JpaRepository<TicketType, String> {
}
