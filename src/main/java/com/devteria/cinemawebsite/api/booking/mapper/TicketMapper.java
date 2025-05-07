package com.devteria.cinemawebsite.api.booking.mapper;


import com.devteria.cinemawebsite.api.booking.dto.request.TicketRequest;
import com.devteria.cinemawebsite.api.booking.dto.response.TicketResponse;
import com.devteria.cinemawebsite.api.booking.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    List<Ticket> toTickets(List<TicketRequest> ticketRequests);

    @Mapping(target = "seat", source = "seat")
    TicketResponse toTicketResponse(Ticket ticket);
}
