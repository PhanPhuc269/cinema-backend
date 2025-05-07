package com.devteria.cinemawebsite.api.cinema.mapper;


import com.devteria.cinemawebsite.api.cinema.dto.response.CinemaResponse;
import com.devteria.cinemawebsite.api.cinema.entity.Cinema;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CinemaMapper {
    List<CinemaResponse> toCinemaResponses(List<Cinema> cinema);
    CinemaResponse toCinemaResponse(Cinema cinema);
}
