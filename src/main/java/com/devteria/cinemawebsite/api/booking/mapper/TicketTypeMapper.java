package com.devteria.cinemawebsite.api.booking.mapper;


import com.devteria.cinemawebsite.api.booking.dto.response.TicketTypeResponse;
import com.devteria.cinemawebsite.api.booking.entity.TicketType;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketTypeMapper {
    TicketTypeResponse toTicketTypeResponse(TicketType ticketType);

    List<TicketTypeResponse> toTicketTypeResponses(List<TicketType> all);
}
