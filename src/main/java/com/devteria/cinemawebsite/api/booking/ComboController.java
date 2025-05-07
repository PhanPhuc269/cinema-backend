package com.devteria.cinemawebsite.api.booking;

import com.devteria.cinemawebsite.api.booking.dto.response.ComboResponse;
import com.devteria.cinemawebsite.api.booking.service.ComboService;
import com.devteria.cinemawebsite.base.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/combo")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ComboController {
    ComboService comboService;

    @GetMapping
    ApiResponse<List<ComboResponse>> getCombos() {
        var result = comboService.getCombos();
        return ApiResponse.<List<ComboResponse>>builder()
                .result(result)
                .build();
    }

}
