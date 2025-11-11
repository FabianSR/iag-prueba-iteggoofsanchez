package com.iag.vip10booking.core.aplicacion.port.secondaryport;

import com.iag.vip10booking.core.dominio.ReservaDomain;
import com.iag.vip10booking.core.dominio.estado.EstadoReserva;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservaRepositoryPort extends SecondaryPort{
    ReservaDomain guardarReserva(ReservaDomain reserva);
    Integer plazasOcupadasPorSalaEstadoFecha(Integer idSala, EstadoReserva estadoReserva, LocalDateTime fechaInicio);
    Integer obtenerReservaPorEstado(Integer idPasajero, EstadoReserva estadoReserva);
    List<ReservaDomain> obtenerIdentificadoresReservasPorEstado(EstadoReserva estado);

    ReservaDomain obtenerReserva(Integer idReserva);

}
