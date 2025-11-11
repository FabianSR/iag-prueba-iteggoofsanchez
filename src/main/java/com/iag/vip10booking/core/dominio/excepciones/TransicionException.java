package com.iag.vip10booking.core.dominio.excepciones;

public class TransicionException extends DomainException{
    public TransicionException() {
        this("transición de estado no permitida");
    }
    public TransicionException(final String mensaje){
        super(mensaje);
    }
}
