package com.iag.vip10booking.core.aplicacion.usecase.imp;

import com.iag.vip10booking.core.aplicacion.port.primaryport.AbstractUseCase;
import com.iag.vip10booking.core.aplicacion.port.secondaryport.ReservaRepositoryPort;
import com.iag.vip10booking.core.aplicacion.usecase.DisponibilidadSalaUseCase;
import com.iag.vip10booking.core.dominio.Par;
import com.iag.vip10booking.core.dominio.estado.EstadoReserva;
import com.iag.vip10booking.core.dominio.validaciones.HoraEnPuntoValicacion;

import java.time.LocalDateTime;

public class DisponibilidadSalaUseCaseImp extends AbstractUseCase<Par<Integer, LocalDateTime>, Integer, ReservaRepositoryPort> implements DisponibilidadSalaUseCase {

    static final int MAX_PLAZAS = 10;

    public DisponibilidadSalaUseCaseImp(ReservaRepositoryPort repository) {
        super(repository);
    }

    @Override
    public Integer execute(Par<Integer, LocalDateTime> parIsSalaFecha) {
        return MAX_PLAZAS - getRepository().plazasOcupadasPorSalaEstadoFecha(parIsSalaFecha.getLeft(), EstadoReserva.PENDIENTE, new HoraEnPuntoValicacion("fecha inicio").validate(parIsSalaFecha.getRight()));
    }
}
