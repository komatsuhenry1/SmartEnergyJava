package com.example.smartenergy.RegistrosMovimentos.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RequestCreateRegistroMovimentoDto(
        @NotNull
        Boolean detectado,


        String timestamp,

        @NotNull
        Integer sensor_id
) {
}