package com.iag.vip10booking.infraestructura.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservaDto {
    private static final String PENDIENTE = "PENDIENTE";
    private String idPasajero;
    private String idSala;
    private LocalDateTime fechaInicio;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String estado = PENDIENTE;
}
