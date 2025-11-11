package com.iag.vip10booking.core.dominio.excepciones;

public class DateBeforeReferenceDateException extends DomainException{
    public DateBeforeReferenceDateException(String dateName, String referenceDateName) {
        super(String.format("%s no puede ser anterior a %s", dateName, referenceDateName));
    }
}