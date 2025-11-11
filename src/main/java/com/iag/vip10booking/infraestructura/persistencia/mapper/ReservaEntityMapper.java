package com.iag.vip10booking.infraestructura.persistencia.mapper;

import com.iag.vip10booking.core.dominio.ReservaDomain;
import com.iag.vip10booking.infraestructura.persistencia.entity.Reserva;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservaEntityMapper {

    ReservaDomain mapToReservaDomain(Reserva entity);
    @Mapping(target = "estado", expression = "java(reservaDomain.getEstado().toString())")
    Reserva mapToReservaEntity(ReservaDomain reservaDomain);
}