package com.iag.vip10booking.infraestructura.consolidacion;

import com.iag.vip10booking.core.aplicacion.usecase.CancelarReservaUseCase;
import com.iag.vip10booking.core.aplicacion.usecase.ObtencionReservasCaducadasActivasUseCase;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioConsolidacionReservas {
    private final CancelarReservaUseCase cancelarReservaUseCase;
    private final ObtencionReservasCaducadasActivasUseCase obtenerReservasCaducadasActivasUseCase;

    public ServicioConsolidacionReservas(final CancelarReservaUseCase cancelarReservaUseCase, ObtencionReservasCaducadasActivasUseCase obtenerReservasCaducadasActivasUseCase) {
        this.cancelarReservaUseCase = cancelarReservaUseCase;
        this.obtenerReservasCaducadasActivasUseCase = obtenerReservasCaducadasActivasUseCase;
    }

    @Scheduled(cron = "0 *0 * * * *")
    @Transactional
    public void finalizarReservasVencidas() {
        obtenerReservasCaducadasActivasUseCase.execute(null).stream().forEach(cancelarReservaUseCase::execute);
    }
}
