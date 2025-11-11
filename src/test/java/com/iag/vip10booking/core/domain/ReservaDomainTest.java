package com.iag.vip10booking.core.domain;

import com.iag.vip10booking.core.dominio.ReservaDomain;
import com.iag.vip10booking.core.dominio.estado.EstadoReserva;
import com.iag.vip10booking.core.dominio.excepciones.NotOnTheHourException;
import com.iag.vip10booking.core.dominio.excepciones.NullValueException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReservaDomainTest {

    @Test
    public void reservaSinIdPasajeroDeberiaLanzarExcepcion() {
        //Given
        final Integer idPAsajero = null;
        //When, Then
        assertThrows(NullValueException.class, () ->
                new ReservaDomain(
                        1,
                        idPAsajero,
                        1,
                        LocalDateTime.of(2123, 10, 2, 15, 30),
                        EstadoReserva.EN_CURSO)
        );
    }

    @Test
    public void reservaSinIdSalaDeberiaLanzarExcepcion() {
        //Given
        final Integer idSala = null;
        //When, Then
        assertThrows(NullValueException.class, () ->
                new ReservaDomain(
                        1,
                        1,
                        idSala,
                        LocalDateTime.of(2123, 10, 2, 15, 30),
                        EstadoReserva.EN_CURSO)
        );
    }

    @Test
    public void reservaSinFechaInicioLanzarExcepcion() {
        //Given
        final LocalDateTime fechaInicio = null;
        //When, Then
        assertThrows(NullValueException.class, () ->
                new ReservaDomain(
                        1,
                        1,
                        1,
                        fechaInicio,
                        EstadoReserva.EN_CURSO)
        );
    }

    @Test
    public void reservaConFechaInicioNoExactaLanzarExcepcion() {
        //Given
        final LocalDateTime fechaNoExacta = LocalDateTime.of(2100, 10, 2, 15, 30);
        //When, Then
        assertThrows(NotOnTheHourException.class, () ->
                new ReservaDomain(
                        1,
                        1,
                        1,
                        fechaNoExacta,
                        EstadoReserva.EN_CURSO)
        );
    }
}