package com.iag.vip10booking.core.aplicacion.port.primaryport;

import com.iag.vip10booking.core.aplicacion.port.secondaryport.SecondaryPort;

public abstract class AbstractUseCase <I,O,T extends SecondaryPort> implements PrimaryPort<I,O> {

    private final T repository;

    public AbstractUseCase(final T repository) {
        this.repository = repository;
    }

    protected T getRepository() {
        return repository;
    }
}
