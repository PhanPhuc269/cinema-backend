package com.devteria.cinemawebsite.api.payment.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentResult {
    boolean success;
    String reason;

    public boolean isSuccess() {
        return success;
    }

    public String getReason() {
        return reason;
    }
}