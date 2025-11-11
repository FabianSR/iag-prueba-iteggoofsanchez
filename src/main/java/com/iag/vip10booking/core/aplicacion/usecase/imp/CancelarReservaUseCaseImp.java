package com.iag.vip10booking.core.aplicacion.usecase.imp;

import com.iag.vip10booking.core.aplicacion.port.primaryport.AbstractUseCase;
import com.iag.vip10booking.core.aplicacion.port.secondaryport.ReservaRepositoryPort;
import com.iag.vip10booking.core.aplicacion.usecase.CancelarReservaUseCase;
import com.iag.vip10booking.core.dominio.ReservaDomain;
import com.iag.vip10booking.core.dominio.estado.EstadoReserva;

import java.util.Optional;

public class CancelarReservaUseCaseImp extends AbstractUseCase<Integer, ReservaDomain, ReservaRepositoryPort> implements CancelarReservaUseCase {
    public CancelarReservaUseCaseImp(ReservaRepositoryPort repository) {
        super(repository);
    }

    @Override
    public ReservaDomain execute(final Integer idReserva) {
        return Optional.ofNullable(getRepository().obtenerReserva(idReserva))
                .map(reserva -> {
                    reserva.setEstado(EstadoReserva.CANCELADA);
                    return getRepository().guardarReserva(reserva);
                }).orElse(null);
    }
}
