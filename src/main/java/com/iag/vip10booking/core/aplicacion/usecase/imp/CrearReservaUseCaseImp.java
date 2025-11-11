package com.iag.vip10booking.core.aplicacion.usecase.imp;

import com.iag.vip10booking.core.aplicacion.port.primaryport.AbstractUseCase;
import com.iag.vip10booking.core.aplicacion.port.secondaryport.ReservaRepositoryPort;
import com.iag.vip10booking.core.aplicacion.usecase.CrearReservaUseCase;
import com.iag.vip10booking.core.dominio.ReservaDomain;
import com.iag.vip10booking.core.dominio.estado.EstadoReserva;
import com.iag.vip10booking.core.dominio.excepciones.DateBeforeReferenceDateException;
import com.iag.vip10booking.core.dominio.excepciones.NumberReservationsExceededException;

import java.time.LocalDateTime;

import static com.iag.vip10booking.core.aplicacion.usecase.imp.DisponibilidadSalaUseCaseImp.MAX_PLAZAS;

public class CrearReservaUseCaseImp extends AbstractUseCase<ReservaDomain, ReservaDomain, ReservaRepositoryPort> implements CrearReservaUseCase {

    private static final Integer MAX_RESERVAS = 2;

    public CrearReservaUseCaseImp(ReservaRepositoryPort repositoryPort) {
        super(repositoryPort);
    }

    @Override
    public ReservaDomain execute(final ReservaDomain reserva) {
        comprobarFechaInicio(reserva);
        comprobarDisponibilidad(reserva);
        return getRepository().guardarReserva(reserva);
    }

    private void comprobarDisponibilidad(ReservaDomain reserva) {
        if (!(getRepository().plazasOcupadasPorSalaEstadoFecha(reserva.getIdSala(), EstadoReserva.PENDIENTE, reserva.getFechaInicio()) < MAX_PLAZAS)
                || (MAX_RESERVAS <= getRepository().obtenerReservaPorEstado(reserva.getIdPasajero(), EstadoReserva.PENDIENTE))) {
            throw new NumberReservationsExceededException();
        }
    }

    private void comprobarFechaInicio(final ReservaDomain reserva) {
        if (reserva.getFechaInicio().isBefore(LocalDateTime.now())) {
            throw new DateBeforeReferenceDateException("fecha de incio", "fecha actual");
        }
    }
}
