package com.aviation.core.dto;

import com.aviation.core.entity.FlightEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FlightMapper {
    FlightMapper INSTANCE = Mappers.getMapper(FlightMapper.class);

    FlightDto toDto(FlightEntity flight);

    FlightEntity toEntity(FlightDto flightDto);
}
