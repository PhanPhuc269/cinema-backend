package com.devteria.cinemawebsite.api.booking.service;

import com.devteria.cinemawebsite.api.booking.dto.response.TicketTypeResponse;
import com.devteria.cinemawebsite.api.booking.mapper.TicketTypeMapper;
import com.devteria.cinemawebsite.api.booking.repository.TicketTypeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TicketTypeService {
    TicketTypeMapper ticketTypeMapper;
    TicketTypeRepository ticketTypeRepository;

    public List<TicketTypeResponse> getTicketTypes (){
        return ticketTypeMapper.toTicketTypeResponses(ticketTypeRepository.findAll());
    }
}
