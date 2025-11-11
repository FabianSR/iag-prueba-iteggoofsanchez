package com.iag.vip10booking.core.dominio.excepciones;

public class NullValueException extends DomainException{
    public NullValueException(final String name) {
        super(String.format("el campo %s no puede ser nulo", name));
    }
}
