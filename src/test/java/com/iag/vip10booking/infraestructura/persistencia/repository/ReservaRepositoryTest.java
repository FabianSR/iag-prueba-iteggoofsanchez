package com.iag.vip10booking.infraestructura.persistencia.repository;

import com.iag.vip10booking.core.dominio.ReservaDomain;
import com.iag.vip10booking.core.dominio.estado.EstadoReserva;
import com.iag.vip10booking.infraestructura.persistencia.entity.Reserva;
import com.iag.vip10booking.infraestructura.persistencia.mapper.ReservaEntityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static com.iag.vip10booking.util.ReservaDomainFixture.getReservaDomainActiva;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

public class ReservaRepositoryTest {

    @Mock
    private ReservaDao reservaDaoMock;
    @Mock
    private ReservaEntityMapper mapperMock;

    @InjectMocks
    private ReservaRepository repository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCrearReserva() {
        //Given
        final ReservaDomain reservaDomain = getReservaDomainActiva();
        final Reserva reservaEntity = mock(Reserva.class);
        when(mapperMock.mapToReservaEntity(reservaDomain)).thenReturn(reservaEntity);
        when(reservaDaoMock.save(reservaEntity)).thenReturn(reservaEntity);
        when(mapperMock.mapToReservaDomain(reservaEntity)).thenReturn(reservaDomain);

        //When
        ReservaDomain result = repository.guardarReserva(reservaDomain);
        //Then
        assertThat(result, is(not(nullValue())));
        verify(mapperMock).mapToReservaEntity(reservaDomain);
        verify(reservaDaoMock).save(reservaEntity);
        verify(mapperMock).mapToReservaDomain(reservaEntity);
    }

    @Test
    public void testPlazasOcupadasPorSalaEstado() {
        //Given
        final Integer idSala = 1;
        final EstadoReserva estado = EstadoReserva.PENDIENTE;
        final LocalDateTime now = LocalDateTime.now();
        when(reservaDaoMock.countByIdSalaAndEstadoAndFechaInicio(any(), any(), any()))
                .thenReturn(1);
        // When
        final Integer result = repository.plazasOcupadasPorSalaEstadoFecha(idSala, estado, now);
        // Then
        assertThat(result, is(1));
        verify(reservaDaoMock).countByIdSalaAndEstadoAndFechaInicio(idSala, estado.name(), now);
    }

    @Test
    public void testObtenerReservaPorEstadoNoExiste() {
        //Given
        final Integer idPasajero = 123;
        final EstadoReserva estado = EstadoReserva.PENDIENTE;
        when(reservaDaoMock.countByIdPasajeroAndEstado(idPasajero, estado.name()))
                .thenReturn(0);
        //When
        final Integer result = repository.obtenerReservaPorEstado(idPasajero, estado);

        //Then
        assertThat(result, is(0));
        verify(reservaDaoMock).countByIdPasajeroAndEstado(idPasajero, estado.name());
        verifyNoInteractions(mapperMock);
    }

    @Test
    public void testObtenerIdentificadoresReservasPorEstado() {
        //Given
        final EstadoReserva estado = EstadoReserva.PENDIENTE;
        final Reserva primeraReservaEntity = mock(Reserva.class);
        final Reserva segundaReservaEntity = mock(Reserva.class);
        final ReservaDomain primerDominio = getReservaDomainActiva();
        final ReservaDomain segundoDominio = getReservaDomainActiva();
        when(reservaDaoMock.findByEstado(estado.name()))
                .thenReturn(List.of(primeraReservaEntity, segundaReservaEntity));
        when(mapperMock.mapToReservaDomain(primeraReservaEntity)).thenReturn(primerDominio);
        when(mapperMock.mapToReservaDomain(segundaReservaEntity)).thenReturn(segundoDominio);
        //When
        final List<ReservaDomain> result = repository.obtenerIdentificadoresReservasPorEstado(estado);
        //Then
        assertThat(result, is(not(nullValue())));
        assertThat(result.size(), is(2));
        assertThat(result.contains(primerDominio), is(true));
        assertThat(result.contains(primerDominio), is(true));
        verify(reservaDaoMock).findByEstado(estado.name());
        verify(mapperMock).mapToReservaDomain(primeraReservaEntity);
        verify(mapperMock).mapToReservaDomain(segundaReservaEntity);
    }
}