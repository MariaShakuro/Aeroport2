package com.aviation.core.dto;

import com.aviation.core.entity.TicketEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);

    TicketDto toDto(TicketEntity ticket);

    TicketEntity toEntity(TicketDto ticketDto);
}
