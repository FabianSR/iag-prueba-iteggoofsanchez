package com.iag.vip10booking.infraestructura.consolidacion;

import com.iag.vip10booking.core.aplicacion.usecase.CancelarReservaUseCase;
import com.iag.vip10booking.core.aplicacion.usecase.ObtencionReservasCaducadasActivasUseCase;
import com.iag.vip10booking.core.dominio.ReservaDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static com.iag.vip10booking.util.ReservaDomainFixture.getReservaDomainActiva;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ServicioConsolidacionReservasTest {
    @Mock
    private CancelarReservaUseCase cancelarReservaUseCaseMock;
    @Mock
    private ObtencionReservasCaducadasActivasUseCase obtenerReservasCaducadasActivasUseCaseMock;

    @InjectMocks
    private ServicioConsolidacionReservas servicioConsolidacionReservas;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void deberiaNoConsolidarReservasCuandoNoEncuentraNinguna() {
        testNoCOnsolidacion(List.of());
    }

    @Test
    public void deberiaNoConsolidarReservasCuandoEncuentraReservaAunActiva() {
        testNoCOnsolidacion(List.of(getReservaDomainActiva()));
    }

    @Test
    public void deberiaConsolidarReservasCuandoEncuentraEnEstadoPendiente() {
        //Given
        when(obtenerReservasCaducadasActivasUseCaseMock.execute(any())).thenReturn(List.of(1));
        //When
        servicioConsolidacionReservas.finalizarReservasVencidas();
        //Then
        verify(cancelarReservaUseCaseMock, atLeastOnce()).execute(any());
        verify(obtenerReservasCaducadasActivasUseCaseMock, atLeastOnce()).execute(any());
    }


    private void testNoCOnsolidacion(final List<ReservaDomain> reservasDomainActiva) {
        //Given
        when(obtenerReservasCaducadasActivasUseCaseMock.execute(any())).thenReturn(List.of());
        //When
        servicioConsolidacionReservas.finalizarReservasVencidas();
        //Then
        verify(cancelarReservaUseCaseMock, never()).execute(any());
        verify(obtenerReservasCaducadasActivasUseCaseMock, atLeastOnce()).execute(any());
    }
}

