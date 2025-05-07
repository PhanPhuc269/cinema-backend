package com.devteria.cinemawebsite.api.booking.service;

import com.devteria.cinemawebsite.api.booking.dto.response.ComboResponse;
import com.devteria.cinemawebsite.api.booking.mapper.ComboMapper;
import com.devteria.cinemawebsite.api.booking.repository.ComboRepository;
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
public class ComboService {
    ComboMapper comboMapper;
    ComboRepository comboRepository;

    public List<ComboResponse> getCombos() {
        return comboMapper.toComboResponses(comboRepository.findAll());
    }
}
