package com.devteria.cinemawebsite.api.payment.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentProcessRequest {
    String paymentId; // ID of the booking
}
