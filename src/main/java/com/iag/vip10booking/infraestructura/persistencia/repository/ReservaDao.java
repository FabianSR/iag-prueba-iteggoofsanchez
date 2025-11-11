package com.iag.vip10booking.infraestructura.persistencia.repository;

import com.iag.vip10booking.infraestructura.persistencia.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservaDao extends JpaRepository<Reserva, Integer> {
    int countByIdSalaAndEstadoAndFechaInicio(Integer idSala, String estado, LocalDateTime fechaInicio);
    int countByIdPasajeroAndEstado(Integer idPasajero, String estado);
    List<Reserva> findByEstado(String estado);
}