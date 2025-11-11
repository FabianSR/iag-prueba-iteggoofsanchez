package com.iag.vip10booking.core.aplicacion.usecase.imp;

import com.iag.vip10booking.core.aplicacion.port.secondaryport.ReservaRepositoryPort;
import com.iag.vip10booking.core.dominio.ReservaDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static com.iag.vip10booking.util.ReservaDomainFixture.getReservaDomainActiva;
import static com.iag.vip10booking.util.ReservaDomainFixture.getReservaDomainCaducada;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ObtencionReservasCaducadasActivasUseCaseImpTest {
    @Mock
    private ReservaRepositoryPort reservaRepositoryPortMock;

    @InjectMocks
    ObtencionReservasCaducadasActivasUseCaseImp obtencionReservasCaducadasActivasUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void cuandoLaReservaEstaNoActivaLaRecupera() {
        //Given
        List<Integer> result = checkRepositorioEsLLamado(getReservaDomainCaducada());
        //Then
        assertThat(result.isEmpty(), is(not(true)));
    }

    @Test
    public void cuandoLaReservaAunEstaActivaNolaRecupera() {
        //Given
        List<Integer> result = checkRepositorioEsLLamado(getReservaDomainActiva());
        //Then
        assertThat(result.isEmpty(), is(true));
    }

    private List<Integer> checkRepositorioEsLLamado(final ReservaDomain reserva) {
        when(reservaRepositoryPortMock.obtenerIdentificadoresReservasPorEstado(any())).thenReturn(List.of(reserva));
        //When
        List<Integer> result = obtencionReservasCaducadasActivasUseCase.execute(null);
        //Then
        verify(reservaRepositoryPortMock, atLeastOnce()).obtenerIdentificadoresReservasPorEstado(any());
        assertThat(result, is(not(nullValue())));
        return result;
    }
}
