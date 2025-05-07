package com.devteria.cinemawebsite.api.payment.entity;

import com.devteria.cinemawebsite.api.booking.entity.Booking;
import com.devteria.cinemawebsite.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @ManyToOne
    Booking booking;

    double amount;

    String paymentMethod; // e.g., "CREDIT_CARD", "PAYPAL", etc.

    @Enumerated(EnumType.STRING)
    PaymentStatus status = PaymentStatus.PENDING;

    LocalDateTime paymentTime = LocalDateTime.now();

    String transactionId; // Optional: for tracking payment transactions
}
