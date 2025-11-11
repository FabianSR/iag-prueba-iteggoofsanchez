package com.iag.vip10booking.infraestructura;

import com.iag.vip10booking.core.aplicacion.port.secondaryport.ReservaRepositoryPort;
import com.iag.vip10booking.core.aplicacion.usecase.imp.CancelarReservaUseCaseImp;
import com.iag.vip10booking.core.aplicacion.usecase.imp.CrearReservaUseCaseImp;
import com.iag.vip10booking.core.aplicacion.usecase.imp.DisponibilidadSalaUseCaseImp;
import com.iag.vip10booking.core.aplicacion.usecase.imp.ObtencionReservasCaducadasActivasUseCaseImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguracionAplicacion {
    @Bean
    public CrearReservaUseCaseImp crearReservaUseCase(final ReservaRepositoryPort reservaRepositoryPort) {
        return new CrearReservaUseCaseImp(reservaRepositoryPort);
    }

    @Bean
    public CancelarReservaUseCaseImp crearCancelarReservaUseCase(final ReservaRepositoryPort reservaRepositoryPort) {
        return new CancelarReservaUseCaseImp(reservaRepositoryPort);
    }

    @Bean
    public DisponibilidadSalaUseCaseImp crearDisponibilidadSalaUseCase(final ReservaRepositoryPort reservaRepositoryPort){
        return new DisponibilidadSalaUseCaseImp(reservaRepositoryPort);
    }

    @Bean
    public ObtencionReservasCaducadasActivasUseCaseImp crearObtenerReservasCaducadasUseCase(final ReservaRepositoryPort reservaRepositoryPort){
        return new ObtencionReservasCaducadasActivasUseCaseImp(reservaRepositoryPort);
    }
}
