package com.iag.vip10booking.core.dominio.estado;

public class MaquinaEstadosReserva extends MaquinaDeEstadosGenerica<EstadoReserva> {
    public MaquinaEstadosReserva(final EstadoReserva status) {
        super(
                status,
                Transicion.of(EstadoReserva.PENDIENTE, EstadoReserva.EN_CURSO),
                Transicion.of(EstadoReserva.EN_CURSO, EstadoReserva.FINALIZADA),
                Transicion.of(EstadoReserva.PENDIENTE, EstadoReserva.CANCELADA));
    }

    @Override
    final String getNombreMaquina() {
        return "Estados de la reserva";
    }
}
