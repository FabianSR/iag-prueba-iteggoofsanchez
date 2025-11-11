package com.iag.vip10booking.infraestructura.rest;

import com.iag.vip10booking.core.aplicacion.usecase.DisponibilidadSalaUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ConsultaSalaControllerTest {
    @Mock
    private DisponibilidadSalaUseCase disponibilidadSalaUseCase;


    @InjectMocks
    private ConsultaSalaController consultaSalaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void consultaSalaShouldCallToDisponibilidadSalaUseCase() {
        //Given
        final LocalDateTime now = LocalDateTime.now();
        when(disponibilidadSalaUseCase.execute(any())).thenReturn(3);
        //When
        Integer result = consultaSalaController.disponibilidadSala(123, now);
        //Then
        assertThat(result, is(not(nullValue())));
        assertThat(result, is(3));
    }
}