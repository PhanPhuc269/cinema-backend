package com.devteria.cinemawebsite.api.booking.service;

import com.devteria.cinemawebsite.api.booking.entity.Seat;
import com.devteria.cinemawebsite.api.booking.repository.SeatRepository;
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
public class SeatService {
    private final SeatRepository seatRepository;

    public Seat getSeatById(String id) {
        return seatRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SEAT_NOT_EXISTED));
    }

    public Seat createSeat(Seat seat) {
        return seatRepository.save(seat);
    }

    public Seat updateSeat(String id, Seat seatDetails) {
        Seat seat = getSeatById(id);
        seat.setSeatNumber(seat.getSeatNumber());
        seat.setType(seatDetails.getType());
        seat.setRoom(seatDetails.getRoom());
        return seatRepository.save(seat);
    }

    public void deleteSeat(String id) {
        Seat seat = getSeatById(id);
        seatRepository.delete(seat);
    }

    public void bulkCreateSeats(List<Seat> seats) {
        seatRepository.saveAll(seats);
    }
}
