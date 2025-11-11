package com.iag.vip10booking.infraestructura.persistencia.repository;

import com.iag.vip10booking.core.aplicacion.port.secondaryport.ReservaRepositoryPort;
import com.iag.vip10booking.core.dominio.ReservaDomain;
import com.iag.vip10booking.core.dominio.estado.EstadoReserva;
import com.iag.vip10booking.infraestructura.persistencia.mapper.ReservaEntityMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReservaRepository implements ReservaRepositoryPort {

    private final ReservaDao reservaDao;
    private final ReservaEntityMapper mapper;

    public ReservaRepository(ReservaDao reservaDao, ReservaEntityMapper mapper) {
        this.reservaDao = reservaDao;
        this.mapper = mapper;
    }

    @Override
    public ReservaDomain guardarReserva(final ReservaDomain reserva) {
        return mapper.mapToReservaDomain(reservaDao.save(mapper.mapToReservaEntity(reserva)));
    }

    public Integer plazasOcupadasPorSalaEstadoFecha(final Integer idSala, final EstadoReserva estado, final LocalDateTime fechaInicio) {
        return reservaDao.countByIdSalaAndEstadoAndFechaInicio(idSala, estado.name(), fechaInicio);
    }

    @Override
    public Integer obtenerReservaPorEstado(final Integer idPasajero, final EstadoReserva estado) {
        return reservaDao.countByIdPasajeroAndEstado(idPasajero, estado.name());
    }

    @Override
    public List<ReservaDomain> obtenerIdentificadoresReservasPorEstado(final EstadoReserva estado) {
        return reservaDao.findByEstado(estado.name()).stream().map(mapper::mapToReservaDomain).collect(Collectors.toList());
    }

    @Override
    public ReservaDomain obtenerReserva(Integer idReserva) {
        return reservaDao.findById(idReserva).map(mapper::mapToReservaDomain).orElse(null);
    }
}