package com.aviation.core.dto;

import com.aviation.core.entity.FlexibleData;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FlexibleDataMapper {
    FlexibleDataMapper INSTANCE = Mappers.getMapper(FlexibleDataMapper.class);

    FlexibleDataDto toDto(FlexibleData flexibleData);

    FlexibleData toEntity(FlexibleDataDto flexibleDataDto);
}
