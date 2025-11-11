package com.iag.vip10booking.core.dominio.validaciones;

import com.iag.vip10booking.core.dominio.excepciones.NullValueException;

import java.util.Objects;

public class CampoNoNuloValidacion<T> extends ValidacionCampoGenerica<T, NullValueException> {

    public CampoNoNuloValidacion(final String name) {
        super(name);
    }

    @Override
    protected boolean isNoValid(T value) {
        return Objects.isNull(value);
    }

    @Override
    protected NullValueException buildException(String name) {
        return new NullValueException(name);
    }
}
