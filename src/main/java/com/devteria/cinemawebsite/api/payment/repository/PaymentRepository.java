package com.devteria.cinemawebsite.api.payment.repository;

import com.devteria.cinemawebsite.api.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {
    List<Payment> findByBooking_User_id(String userId);
}
