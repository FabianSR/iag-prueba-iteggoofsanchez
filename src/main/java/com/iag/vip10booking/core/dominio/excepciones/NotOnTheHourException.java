package com.iag.vip10booking.core.dominio.excepciones;

public class NotOnTheHourException extends DomainException{
    public NotOnTheHourException(final String fecha) {
        super(String.format("la fecha %s debe ser una hora en punto", fecha));
    }
}
