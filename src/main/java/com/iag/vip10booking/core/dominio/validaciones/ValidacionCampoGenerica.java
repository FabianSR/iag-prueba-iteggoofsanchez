package com.iag.vip10booking.core.dominio.validaciones;

import com.iag.vip10booking.core.dominio.excepciones.DomainException;

public abstract class ValidacionCampoGenerica<T, E extends DomainException> {

    private final String name;

    protected ValidacionCampoGenerica(String name) {
        this.name = name;
    }

    public final T validate(final T field) {
        if (isNoValid(field)) {
            throw buildException(name);
        }
        return field;
    }

    protected abstract boolean isNoValid(T value);

    protected abstract E buildException(String name);
}
