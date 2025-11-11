package com.iag.vip10booking.infraestructura.rest;

import com.iag.vip10booking.core.aplicacion.usecase.CancelarReservaUseCase;
import com.iag.vip10booking.core.aplicacion.usecase.CrearReservaUseCase;
import com.iag.vip10booking.infraestructura.rest.dto.ReservaDto;
import com.iag.vip10booking.infraestructura.rest.mapper.ReservaDtoMapper;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reserva")
public class GestionReservaController {

    private final CrearReservaUseCase crearReservaUseCase;
    private final CancelarReservaUseCase cancelarReservaUseCase;
    private final ReservaDtoMapper mapper;

    public GestionReservaController(final CrearReservaUseCase useCase, final CancelarReservaUseCase cancelarReservaUseCase, final ReservaDtoMapper mapper) {
        this.cancelarReservaUseCase = cancelarReservaUseCase;
        this.mapper = mapper;
        this.crearReservaUseCase = useCase;
    }

    @PostMapping("/")
    public ReservaDto generarReserva(final @RequestBody ReservaDto reservaDto) {
        return mapper.mapToReservaDto(crearReservaUseCase.execute(mapper.mapToReservaDomain(reservaDto)));
    }

    @PatchMapping("/{idReserva}")
    public ReservaDto cancelarReserva(final @PathVariable Integer idReserva) {
        return mapper.mapToReservaDto(cancelarReservaUseCase.execute(idReserva));
    }

}