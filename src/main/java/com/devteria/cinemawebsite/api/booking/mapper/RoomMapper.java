package com.devteria.cinemawebsite.api.booking.mapper;


import com.devteria.cinemawebsite.api.cinema.dto.response.RoomResponse;
import com.devteria.cinemawebsite.api.cinema.entity.Room;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    RoomResponse toRoomResponse(Room room);


    List<RoomResponse> toRoomResponses(List<Room> byCinemaId);
}
