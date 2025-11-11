package com.iag.vip10booking.infraestructura.rest.mapper;

import com.iag.vip10booking.core.dominio.ReservaDomain;
import com.iag.vip10booking.infraestructura.rest.dto.ReservaDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservaDtoMapper {
    @Mapping(target = "estado", expression = "java(reservaDomain.getEstado().toString())")
    ReservaDto mapToReservaDto(ReservaDomain reservaDomain);
    ReservaDomain mapToReservaDomain(ReservaDto reservaDto);
}