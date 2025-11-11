package com.iag.vip10booking.core.dominio.excepciones;

public class NumberReservationsExceededException extends DomainException{
    public NumberReservationsExceededException() {
        super("número de reservas excedido");
    }
}
