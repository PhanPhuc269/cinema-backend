package com.devteria.cinemawebsite.api.payment.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentCancelResponse {
    boolean success;
    String message; // Optional: for error messages or additional info
}
