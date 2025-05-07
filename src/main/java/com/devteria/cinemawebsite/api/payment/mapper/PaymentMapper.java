package com.devteria.cinemawebsite.api.payment.mapper;


import com.devteria.cinemawebsite.api.payment.dto.response.PaymentResponse;
import com.devteria.cinemawebsite.api.payment.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(target="booking", source="booking")
    PaymentResponse toResponse(Payment payment);

}
