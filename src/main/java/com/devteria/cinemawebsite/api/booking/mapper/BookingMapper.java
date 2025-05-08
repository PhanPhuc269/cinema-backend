package com.devteria.cinemawebsite.api.booking.mapper;


import com.devteria.cinemawebsite.api.booking.dto.request.BookingRequest;
import com.devteria.cinemawebsite.api.booking.dto.response.BookingResponse;
import com.devteria.cinemawebsite.api.booking.entity.Booking;
import  org.mapstruct.Mapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    Booking toBooking(BookingRequest request);

    @Mapping(target = "user", source = "user") // Sử dụng UserMapper
    @Mapping(target = "showtime", source = "showtime") // Sử dụng ShowtimeMapper
    @Mapping(target = "tickets", source = "tickets") // Sử dụng TicketMapper
    @Mapping(target = "orderItems", source = "orderItems") // Sử dụng OrderItemMapper
    @Mapping(target = "bookingCode", source = "bookingCode") // Sử dụng OrderItemMapper
    BookingResponse toBookingResponse(Booking booking);

    void updateBooking(@MappingTarget Booking booking, BookingRequest request);
}
