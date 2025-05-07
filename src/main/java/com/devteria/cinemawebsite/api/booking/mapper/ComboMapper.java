package com.devteria.cinemawebsite.api.booking.mapper;


import com.devteria.cinemawebsite.api.booking.dto.response.ComboResponse;
import com.devteria.cinemawebsite.api.booking.entity.Combo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ComboMapper {
    ComboResponse toComboResponse(Combo combo);

    List<ComboResponse> toComboResponses(List<Combo> combos);
}
