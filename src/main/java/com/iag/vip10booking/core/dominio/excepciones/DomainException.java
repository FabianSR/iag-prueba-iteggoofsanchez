package com.iag.vip10booking.core.dominio.excepciones;

public abstract class DomainException extends RuntimeException{
    public DomainException(final String name) {
        super(name);
    }
}
