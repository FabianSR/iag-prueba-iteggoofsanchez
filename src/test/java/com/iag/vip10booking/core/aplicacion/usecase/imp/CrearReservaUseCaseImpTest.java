package com.iag.vip10booking.core.aplicacion.usecase.imp;

import com.iag.vip10booking.core.aplicacion.port.secondaryport.ReservaRepositoryPort;
import com.iag.vip10booking.core.dominio.excepciones.NumberReservationsExceededException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.iag.vip10booking.util.ReservaDomainFixture.getReservaDomainActiva;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CrearReservaUseCaseImpTest {
    @Mock
    private ReservaRepositoryPort reservaRepositoryPortMock;

    @InjectMocks
    CrearReservaUseCaseImp crearReservaUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void numeroDeReservaExcedidoSalaDeberiaLanzarExcepcion() {
        //Given
        when(reservaRepositoryPortMock.plazasOcupadasPorSalaEstadoFecha(any(),any(),any())).thenReturn(10);
        //When, Then
        assertThrows(NumberReservationsExceededException.class, () ->
                crearReservaUseCase.execute(getReservaDomainActiva()));
    }

    @Test
    public void numeroDeReservaExcedidoPasajeroDeberiaLanzarExcepcion() {
        //Given
        when(reservaRepositoryPortMock.plazasOcupadasPorSalaEstadoFecha(any(),any(),any())).thenReturn(9);
        when(reservaRepositoryPortMock.obtenerReservaPorEstado(any(),any())).thenReturn(2);
        //When, Then
        assertThrows(NumberReservationsExceededException.class, () ->
                crearReservaUseCase.execute(getReservaDomainActiva()));
    }

    @Test
    public void numeroDeReservaNoExcedidoDeberiaCrearReserva() {
        //Given
        when(reservaRepositoryPortMock.plazasOcupadasPorSalaEstadoFecha(any(),any(),any())).thenReturn(9);

        //When, Then
        assertDoesNotThrow(() ->
                crearReservaUseCase.execute(getReservaDomainActiva()));
    }

}