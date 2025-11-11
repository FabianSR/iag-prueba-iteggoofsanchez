package com.iag.vip10booking.infraestructura.rest;

import com.iag.vip10booking.core.aplicacion.usecase.DisponibilidadSalaUseCase;
import com.iag.vip10booking.core.dominio.Par;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/sala")
public class ConsultaSalaController {

    private final DisponibilidadSalaUseCase disponibilidadSalaUseCase;

    public ConsultaSalaController(DisponibilidadSalaUseCase disponibilidadSalaUseCase) {
        this.disponibilidadSalaUseCase = disponibilidadSalaUseCase;
    }

    @PostMapping(value = "/{idSala}")
    public Integer disponibilidadSala(final @PathVariable Integer idSala, final @RequestBody LocalDateTime fecha) {
        return disponibilidadSalaUseCase.execute(Par.of(idSala, fecha));
    }

}