package com.iag.vip10booking.core.aplicacion.usecase.imp;

import com.iag.vip10booking.core.aplicacion.port.secondaryport.ReservaRepositoryPort;
import com.iag.vip10booking.core.dominio.ReservaDomain;
import com.iag.vip10booking.core.dominio.estado.EstadoReserva;
import com.iag.vip10booking.core.dominio.excepciones.TransicionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.iag.vip10booking.core.dominio.estado.EstadoReserva.EN_CURSO;
import static com.iag.vip10booking.util.ReservaDomainFixture.getReservaDomainActiva;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CancelarReservaUseCaseImpTest {

    @Mock
    private ReservaRepositoryPort reservaRepositoryPortMock;

    @InjectMocks
    CancelarReservaUseCaseImp cancelarReservaUseCaseImp;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void reservaEnEstadoNoTransitableACanceladoDeberiaLanzarExcepcion() {
        //Given
        final ReservaDomain reserva = getReservaDomainActiva();
        reserva.setEstado(EN_CURSO);
        when(reservaRepositoryPortMock.obtenerReserva(any())).thenReturn(reserva);
        //When Then
        assertThrows(TransicionException.class, () ->
                cancelarReservaUseCaseImp.execute(1));
    }

    @Test
    public void reservaEnEstadoNoFinalDeberiaCancelarse() {
        //Given
        ReservaDomain reservaActiva = getReservaDomainActiva();
        when(reservaRepositoryPortMock.obtenerReserva(any())).thenReturn(reservaActiva);
        when(reservaRepositoryPortMock.guardarReserva(any())).thenReturn(reservaActiva);
        //When
        ReservaDomain result = cancelarReservaUseCaseImp.execute(1);
        // Then
        assertThat(result, is(not(nullValue())));
        assertThat(result.getEstado(), is(EstadoReserva.CANCELADA));
    }

}