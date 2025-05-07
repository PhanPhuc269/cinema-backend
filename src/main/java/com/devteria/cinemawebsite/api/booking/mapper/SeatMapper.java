package com.devteria.cinemawebsite.api.booking.mapper;


import com.devteria.cinemawebsite.api.booking.dto.response.SeatResponse;
import com.devteria.cinemawebsite.api.booking.entity.Seat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SeatMapper {
    @Mapping(target = "name", source = "seatNumber")
    @Mapping(target = "isPresent", source = "present")
    SeatResponse toSeatResponse(Seat seat);
}
