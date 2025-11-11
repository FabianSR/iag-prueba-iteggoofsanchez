package com.iag.vip10booking.util;

import com.iag.vip10booking.core.dominio.ReservaDomain;
import com.iag.vip10booking.core.dominio.estado.EstadoReserva;

import java.time.LocalDateTime;

public class ReservaDomainFixture {
    private ReservaDomainFixture() {
    }

    public static ReservaDomain getReservaDomainActiva() {
        return getReservaDomain(LocalDateTime.of(2200, 10, 2, 0, 0));
    }

    public static ReservaDomain getReservaDomainCaducada() {
        return getReservaDomain(LocalDateTime.of(2000, 10, 2, 0, 0));
    }

    private static ReservaDomain getReservaDomain(LocalDateTime fechaInicio) {
        return new ReservaDomain(
                1,
                1,
                1,
                fechaInicio,
                EstadoReserva.PENDIENTE);
    }


}
