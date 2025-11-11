package com.iag.vip10booking.core.aplicacion.port.primaryport;

public interface PrimaryPort<I,O> {
    O execute(I input);
}