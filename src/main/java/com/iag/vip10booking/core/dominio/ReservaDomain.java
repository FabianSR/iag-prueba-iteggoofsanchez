package com.iag.vip10booking.core.dominio;

import com.iag.vip10booking.core.dominio.estado.Estado;
import com.iag.vip10booking.core.dominio.estado.EstadoReserva;
import com.iag.vip10booking.core.dominio.estado.MaquinaEstadosReserva;
import com.iag.vip10booking.core.dominio.validaciones.CampoNoNuloValidacion;
import com.iag.vip10booking.core.dominio.validaciones.HoraEnPuntoValicacion;

import java.time.LocalDateTime;


public class ReservaDomain {

    private final Integer idReserva;
    private final Integer idPasajero;
    private final Integer idSala;
    private final LocalDateTime fechaInicio;
    private MaquinaEstadosReserva estados;

    public ReservaDomain(final Integer idReserva, final Integer idPasajero, final Integer idSala,
                         final LocalDateTime fechaInicio, final EstadoReserva estado) {
        this.idReserva = idReserva;
        this.idPasajero = new CampoNoNuloValidacion<Integer>("id pasajero").validate(idPasajero);
        this.idSala = new CampoNoNuloValidacion<Integer>("id sala").validate(idSala);
        this.fechaInicio = new HoraEnPuntoValicacion("fecha inicio").validate(new CampoNoNuloValidacion<LocalDateTime>("fecha inicio").validate(fechaInicio));
        this.estados = new MaquinaEstadosReserva(new CampoNoNuloValidacion<EstadoReserva>("estado reserva").validate(estado));
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public Integer getIdPasajero() {
        return idPasajero;
    }

    public Integer getIdSala() {
        return idSala;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public EstadoReserva getEstado() {
        return estados.getStatus();
    }

    public void setEstado(final Estado estado) {
        estados.next(estado);
    }
}

