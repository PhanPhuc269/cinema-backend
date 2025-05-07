package com.devteria.cinemawebsite.api.booking.mapper;


import com.devteria.cinemawebsite.api.booking.dto.request.OrderItemRequest;
import com.devteria.cinemawebsite.api.booking.dto.response.OrderItemResponse;
import com.devteria.cinemawebsite.api.booking.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItem toOrderItem(OrderItemRequest request);

    @Mapping(target = "combo", source = "combo")
    List<OrderItem> toOrderItems(List<OrderItemRequest> request);

    @Mapping(target = "combo", source = "combo")
    OrderItemResponse toOrderItemResponse(OrderItem orderItem);

    void updateOrderItem(@MappingTarget OrderItem booking, OrderItemRequest request);
}
