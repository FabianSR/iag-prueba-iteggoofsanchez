package com.iag.vip10booking.infraestructura.persistencia.repository;

import com.iag.vip10booking.infraestructura.persistencia.entity.Reserva;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class ReservaDaoIntegrationTest {

    private static final String PENDIENTE = "PENDIENTE";
    private static final String CANCELADA = "CANCELADA";

    @Autowired
    private ReservaDao reservaDao;

    private Reserva primeraReserva, segundaReserva, terceraReseva;

    @BeforeEach
    void setUp() {
        reservaDao.deleteAll();
        primeraReserva = new Reserva(1, 1, 1, LocalDateTime.of(2025, 11, 4, 14, 0), "PENDIENTE");
        segundaReserva = new Reserva(2, 2, 1, LocalDateTime.of(2025, 11, 5, 15, 0), "CANCELADA");
        terceraReseva = new Reserva(3, 1, 2, LocalDateTime.of(2025, 11, 6, 16, 0), "CONFIRMADA");
        reservaDao.saveAll(Arrays.asList(primeraReserva, segundaReserva, terceraReseva));
    }

    @Test
    void testFindAllByIdSalaAndEstadoNotIn() {
        //Given
        //When
        Integer reservas = reservaDao.countByIdSalaAndEstadoAndFechaInicio(1, PENDIENTE, LocalDateTime.of(2025, 11, 4, 14, 0));
        //Then
        assertThat(reservas).isEqualTo(1);
    }

    @Test
    void testFindByIdPasajeroAndEstado() {
        //Given, When
        final Integer reservas = reservaDao.countByIdPasajeroAndEstado(1, PENDIENTE);
        //Then
        assertThat(reservas).isEqualTo(1);
    }

    @Test
    void testNoReservaWhenEstadoNoCoincide() {
        //Given, When
        final Integer reservas = reservaDao.countByIdPasajeroAndEstado(1, CANCELADA);
        //Then
        assertThat(reservas).isEqualTo(0);
    }
}
