package com.devteria.cinemawebsite.api.cinema.repository;

import com.devteria.cinemawebsite.api.cinema.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, String> {
    public List<Room> findByCinemaId(Long cinemaId);

    List<Room> findByCinema_id(Long cinemaId);
}
