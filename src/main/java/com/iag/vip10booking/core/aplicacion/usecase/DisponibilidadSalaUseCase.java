package com.iag.vip10booking.core.aplicacion.usecase;

import com.iag.vip10booking.core.aplicacion.port.primaryport.PrimaryPort;
import com.iag.vip10booking.core.dominio.Par;

import java.time.LocalDateTime;

public interface DisponibilidadSalaUseCase extends PrimaryPort<Par<Integer, LocalDateTime>, Integer> {
}
