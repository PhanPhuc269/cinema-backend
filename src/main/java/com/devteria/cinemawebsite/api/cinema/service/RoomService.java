package com.devteria.cinemawebsite.api.cinema.service;

import com.devteria.cinemawebsite.api.booking.mapper.RoomMapper;
import com.devteria.cinemawebsite.api.cinema.dto.response.RoomResponse;
import com.devteria.cinemawebsite.api.cinema.entity.Room;
import com.devteria.cinemawebsite.api.cinema.repository.RoomRepository;
import com.devteria.cinemawebsite.exception.AppException;
import com.devteria.cinemawebsite.exception.ErrorCode;
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
public class RoomService {
    RoomRepository roomRepository;
    RoomMapper roomMapper;

    public List<RoomResponse> getRoomByCinemaId(Long cinemaId) {
        return roomMapper.toRoomResponses(roomRepository.findByCinema_id(cinemaId));
    }

    public Room getRoomById(String id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ROOM_NOT_FOUND));
    }

    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room updateRoom(String id, Room roomDetails) {
        Room room = getRoomById(id);
        room.setName(roomDetails.getName());
        room.setCinema(roomDetails.getCinema());
        return roomRepository.save(room);
    }

    public void deleteRoom(String id) {
        Room room = getRoomById(id);
        roomRepository.delete(room);
    }

}
