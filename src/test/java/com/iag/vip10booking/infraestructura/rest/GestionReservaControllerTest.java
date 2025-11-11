package com.iag.vip10booking.infraestructura.rest;

import com.iag.vip10booking.core.aplicacion.usecase.CancelarReservaUseCase;
import com.iag.vip10booking.core.aplicacion.usecase.CrearReservaUseCase;
import com.iag.vip10booking.infraestructura.rest.mapper.ReservaDtoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

public class GestionReservaControllerTest {
    @Mock
    private CrearReservaUseCase crearReservaUseCaseMock;
    @Mock
    private CancelarReservaUseCase cancelarReservaUseCaseMock;
    @Mock
    private ReservaDtoMapper mapperMock;

    @InjectMocks
    private GestionReservaController gestionReservaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void gestionReservaShouldCallToCrearReservaUseCase() {
        //Given
        //When
        gestionReservaController.generarReserva(null);
        //Then
        verify(mapperMock, atLeastOnce()).mapToReservaDto(any());
        verify(mapperMock, atLeastOnce()).mapToReservaDomain(any());
        verify(crearReservaUseCaseMock, atLeastOnce()).execute(any());
    }

    @Test
    public void gestionReservaShouldCallToCancelarReservaUseCase() {
        //Given
        //When
        gestionReservaController.cancelarReserva(null);
        //Then
        verify(mapperMock, atLeastOnce()).mapToReservaDto(any());
        verify(cancelarReservaUseCaseMock, atLeastOnce()).execute(any());
    }
}
