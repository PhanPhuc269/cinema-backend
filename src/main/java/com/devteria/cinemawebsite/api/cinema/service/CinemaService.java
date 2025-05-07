package com.devteria.cinemawebsite.api.cinema.service;

import com.devteria.cinemawebsite.api.cinema.dto.response.CinemaResponse;
import com.devteria.cinemawebsite.api.cinema.mapper.CinemaMapper;
import com.devteria.cinemawebsite.api.cinema.repository.CinemaRepository;
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
public class CinemaService {
    CinemaRepository cinemaRepository;
    CinemaMapper cinemaMapper;

    public List<CinemaResponse> getAllCinemas() {
        return cinemaRepository.findAll()
                .stream()
                .map(cinema -> CinemaResponse.builder()
                        .id(cinema.getId())
                        .name(cinema.getName())
                        .address(cinema.getAddress())
                        .phone(cinema.getPhone())
                        .city(cinema.getCity())
                        .imageUrl(cinema.getImageUrl())
                        .build())
                .toList();
    }

    public CinemaResponse getCinemaById(String cinemaId) {
        var cinema = cinemaRepository.findById(cinemaId)
                .orElseThrow(() -> new RuntimeException("Cinema not found"));
        return CinemaResponse.builder()
                .id(cinema.getId())
                .name(cinema.getName())
                .address(cinema.getAddress())
                .phone(cinema.getPhone())
                .city(cinema.getCity())
                .imageUrl(cinema.getImageUrl())
                .build();
    }

    public List<CinemaResponse> getCinemasByCity(String cityName) {
        return cinemaMapper.toCinemaResponses(cinemaRepository.findByCity(cityName));
    }
}
