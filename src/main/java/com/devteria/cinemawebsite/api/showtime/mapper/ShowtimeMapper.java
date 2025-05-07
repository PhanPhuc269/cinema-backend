package com.devteria.cinemawebsite.api.showtime.mapper;


import com.devteria.cinemawebsite.api.showtime.dto.response.ShowtimeResponse;
import com.devteria.cinemawebsite.api.showtime.entity.Showtime;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShowtimeMapper {
    List<ShowtimeResponse> toShowtimeResponses(List<Showtime> showtime);
    ShowtimeResponse toShowtimeResponse(Showtime showtime);
}
