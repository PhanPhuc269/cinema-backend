package com.devteria.cinemawebsite.api.payment.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentRequest {
    String bookingId; // ID of the booking
    double amount; // Payment amount
    String paymentMethod; // e.g., "CREDIT_CARD", "PAYPAL", etc.
    String QrCodeURL;
}
