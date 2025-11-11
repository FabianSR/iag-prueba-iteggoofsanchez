package com.iag.vip10booking.core.aplicacion.usecase.imp;

import com.iag.vip10booking.core.aplicacion.port.primaryport.AbstractUseCase;
import com.iag.vip10booking.core.aplicacion.port.secondaryport.ReservaRepositoryPort;
import com.iag.vip10booking.core.aplicacion.usecase.ObtencionReservasCaducadasActivasUseCase;
import com.iag.vip10booking.core.dominio.ReservaDomain;
import com.iag.vip10booking.core.dominio.estado.EstadoReserva;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ObtencionReservasCaducadasActivasUseCaseImp extends AbstractUseCase<Void, List<Integer>, ReservaRepositoryPort> implements ObtencionReservasCaducadasActivasUseCase {
    public ObtencionReservasCaducadasActivasUseCaseImp(ReservaRepositoryPort repository) {
        super(repository);
    }

    @Override
    public List<Integer> execute(final Void v) {
        final LocalDateTime currentDate = LocalDateTime.now().plusHours(1);
        return getRepository().obtenerIdentificadoresReservasPorEstado(EstadoReserva.PENDIENTE).stream()
                .filter(reserva -> currentDate.isAfter(reserva.getFechaInicio())).map(ReservaDomain::getIdReserva).collect(Collectors.toList());
    }
}