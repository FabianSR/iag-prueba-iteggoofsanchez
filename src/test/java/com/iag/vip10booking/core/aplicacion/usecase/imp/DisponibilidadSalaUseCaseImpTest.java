package com.iag.vip10booking.core.aplicacion.usecase.imp;

import com.iag.vip10booking.core.aplicacion.port.secondaryport.ReservaRepositoryPort;
import com.iag.vip10booking.core.dominio.Par;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DisponibilidadSalaUseCaseImpTest {
    @Mock
    private ReservaRepositoryPort reservaRepositoryPortMock;

    @InjectMocks
    DisponibilidadSalaUseCaseImp disponibilidadSalaUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void numeroDeReservaPermitidoSala() {
        //Given
        when(reservaRepositoryPortMock.plazasOcupadasPorSalaEstadoFecha(any(), any(), any())).thenReturn(7);
        //When
        Integer result = disponibilidadSalaUseCase.execute(Par.of(null, LocalDateTime.of(2200, 10, 2, 0, 0)));
        //Then
        assertThat(result, is(3));
    }
}