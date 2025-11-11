package com.iag.vip10booking.core.dominio.validaciones;

import com.iag.vip10booking.core.dominio.excepciones.NotOnTheHourException;

import java.time.LocalDateTime;

public class HoraEnPuntoValicacion extends ValidacionCampoGenerica<LocalDateTime, NotOnTheHourException> {
    public HoraEnPuntoValicacion(final String name) {
        super(name);
    }

    @Override
    protected boolean isNoValid(final LocalDateTime value) {
        return value.getMinute() != 0 || value.getSecond() != 0;
    }

    @Override
    protected NotOnTheHourException buildException(final String name) {
        return new NotOnTheHourException(name);
    }
}
