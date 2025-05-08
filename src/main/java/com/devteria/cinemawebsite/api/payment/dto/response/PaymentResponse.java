package com.devteria.cinemawebsite.api.payment.dto.response;

import com.devteria.cinemawebsite.api.booking.dto.response.BookingResponse;
import com.devteria.cinemawebsite.enums.PaymentStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentResponse {
    String id;

    BookingResponse booking;
    double amount;
    String paymentMethod; // e.g., "CREDIT_CARD", "PAYPAL", etc.

    @Enumerated(EnumType.STRING)
    PaymentStatus status;

    LocalDateTime paymentTime;

    String transactionId; // Optional: for tracking payment transactions

    String qrcode;
}
