package com.devteria.cinemawebsite.api.booking.repository;

import com.devteria.cinemawebsite.api.booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {

    public List<Booking> findByUserId(String userId);
}
